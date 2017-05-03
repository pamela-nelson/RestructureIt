package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtSuperAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.TypeFactory;
import spoon.reflect.reference.CtTypeReference;

/**
 * Performs the refactoring Collapse Hierarchy. If a superclass and subclass are 
 * nearly the same then merge them together.
 * 
 * Reference: https://refactoring.com/catalog/collapseHierarchy.html
 */
public class CollapseHierarchy extends AbstractProcessor<CtClass<?>> {
	
	/**
	 * Total number of times the encapsulateField refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * How many extra fields can subclass have before it is
	 * no longer considered similar to parent class.
	 */
	private int fieldDifferenceTolerance = 2;
	
	/**
	 * How many extra methods (not including getters/setters) can subclass have 
	 * before it is no longer considered similar to parent class.
	 */
	private int methodDifferenceTolerance = 2;
	
	/**
	 * Root Package of the project.
	 */
	private CtPackage rootPackage;
	
	/**
	 * @return the timesApplied
	 */
	public static int getTimesApplied() {
		return timesApplied;
	}
	
	/**
	 * Resets times applied back to zero.
	 */
	public static void resetTimesApplied() {
		timesApplied = 0;
	}
		
	/**
	 * Determines whether a candidate class should be merged with its parent.
	 * @param candidateClass  class to check
	 * @return class should be refactored
	 */
	public boolean isToBeProcessed(final CtClass<?> candidateClass) {
		
		
		//Check if candidate class has a parent class to merge with
		if (candidateClass.getSuperclass() == null
				|| candidateClass.getSuperclass().getDeclaration() == null) {
			return false;
		}
		
		CtClass<?> candidateParentClass = (CtClass<?>) candidateClass.getSuperclass().getDeclaration();
		
		//Check if parent is abstract
		if (candidateParentClass.hasModifier(ModifierKind.ABSTRACT)) {
			return false;
		}
		
		//Check if candidate class and parent use default constructor
		Set<?> classConstructors = candidateClass.getConstructors();

		for (CtConstructor<?> constructor : (Set<CtConstructor<?>>) classConstructors) {
			if (classConstructors.size() > 1 || constructor.getParameters().size() > 0
					|| constructor.getBody().getStatements().size() > 1
					|| !constructor.getBody().getStatement(0).toString().equals("super()")) {
				return false;
			}
		}
		
		//Check Parent class only has one direct subclass (candidate class).
		//
		//Prevents problems merging incompatible classes
		//eg. Parent class : Transport, Subclasses: Car, Plane. Merging car with transport causes problems
		//as car does not have similarities with a plane
		
		if (RefactoringHelperUtils.getSubClasses(candidateParentClass).size() > 1) {
			return false;
		}	
		
		//Check if candidate class is similar enough to merge
		if (!shouldMergeClasses(candidateClass)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Merges candidateClass with its parent class.
	 * @param candidateClass class to merge
	 */
	public void process(final CtClass<?> candidateClass) {
		
		timesApplied++;
		
		if (rootPackage == null) {
			rootPackage = RefactoringHelperUtils.getRootPackage(candidateClass);	
		}
		
		CtClass<?> candidateParentClass = (CtClass<?>) candidateClass.getSuperclass().getDeclaration();
		
		List<CtField<?>> subClassFields = candidateClass.getFields();
		
		//Add fields if not in parent class
		for (CtField<?> field : subClassFields) {
			if (candidateParentClass.getField(field.getSimpleName()) == null) {
				candidateParentClass.addField(field);
			}
		}
		
		Set<CtMethod<?>> subClassMethods = candidateClass.getMethods();
		
		//Add methods if not in parent class
		for (CtMethod<?> method : subClassMethods) {
			List<CtMethod<?>> matchingMethods = candidateParentClass.getMethodsByName(method.getSimpleName());
			
			if (matchingMethods.size() == 0) {
				candidateParentClass.addMethod(method);
			}
			
			boolean uniqueMethod = true;
			
			for (CtMethod<?> matchingMethod : matchingMethods) {
				if (matchingMethod.getSignature().equals(method.getSignature())) {
					
					boolean statementsMatch = true;
					
					List<CtStatement> classMethodStatements = matchingMethod.getBody().getStatements();
					List<CtStatement> candidateMethodStatements = method.getBody().getStatements();
					
					for (int i = 0; i < classMethodStatements.size(); i++) {
						if (!classMethodStatements.get(i).toString().equals(candidateMethodStatements.get(i).toString())) {
							statementsMatch = false;
						}
					}
					
					if (!statementsMatch) {
						candidateParentClass.removeMethod(matchingMethod);
						break;
					} else {
						uniqueMethod = false;
						break;
					}
				}
			}
			
			if (uniqueMethod) {
				candidateParentClass.addMethod(method);
			}
		}
		
		//add interfaces implemented by subclass
		Set<CtTypeReference<?>> subClassInterfaces = candidateClass.getSuperInterfaces();
		
		for (CtTypeReference<?> subClassInterface : subClassInterfaces) {
			candidateParentClass.addSuperInterface(subClassInterface);
		}
		
		//Update subclass references
		List<CtField<?>> allSubClassTypeFields = rootPackage.filterChildren((CtField<?> field) -> field.getType().equals(candidateClass.getReference())) 
														.list();
		//update declared fields
		for (CtField field : allSubClassTypeFields) {
			field.setType(candidateParentClass.getReference());
		}
		
		List<CtConstructor<?>> parentConstructor = new ArrayList<CtConstructor<?>>(candidateParentClass.getConstructors());
		
		CtConstructorCall<?> parentConstructorCall = getFactory().createConstructorCall(candidateParentClass.getReference());
		
		//change constructor calls
		List<CtConstructorCall<?>> allSubClassTypeConstructorCalls = rootPackage.filterChildren((CtConstructorCall<?> constructorCall) -> constructorCall.getType().equals(candidateClass.getReference())) 
				.list();
		
		for (CtConstructorCall<?> constructorCall : allSubClassTypeConstructorCalls) {
			constructorCall.replace(parentConstructorCall);
		}
		
		//remove any super calls to parent
		List<CtSuperAccess<?>> allSubClassSuperAccesses = rootPackage.filterChildren((CtSuperAccess<?> superAccess) -> superAccess.getParent(CtClass.class).equals(candidateParentClass)) 
														  .list();
		
		for (CtSuperAccess<?> superAccess : allSubClassSuperAccesses) {
			if (superAccess.getParent() instanceof CtFieldAccess<?>) {
				CtFieldAccess<?> superFieldAccess = (CtFieldAccess<?>) superAccess.getParent();
				superFieldAccess.setTarget(getFactory().createThisAccess(candidateParentClass.getReference()));
				superAccess.setParent(superFieldAccess);
			} else if (superAccess.getParent() instanceof CtInvocation<?>) {
				CtInvocation<?> superMethodInvocation = (CtInvocation<?>) superAccess.getParent();
				superMethodInvocation.setTarget(getFactory().createThisAccess(candidateParentClass.getReference()));
				superMethodInvocation.getTarget().setImplicit(true);
				superAccess.setParent(superMethodInvocation);
			}
			
		}
		
		//Update any subclass type casts to parent class
		List<CtExpression<?>> allSubClassCasts = rootPackage.filterChildren((CtExpression<?> expression) -> expression.getTypeCasts().contains(candidateClass.getReference())) 
				  .list();
		
		for (CtExpression<?> cast : allSubClassCasts) {
			cast.getTypeCasts().remove(candidateClass.getReference());
			cast.addTypeCast(candidateClass.getReference());
		}
		
		//update subclasses children to new superclass
		List<CtClass<?>> candidateClassSubClasses = RefactoringHelperUtils.getSubClasses(candidateClass);

		for (CtClass<?> subClass : candidateClassSubClasses) {
			subClass.setSuperclass(candidateParentClass.getReference());
		}
		
		//remove candidate class
		candidateClass.delete();
	}
	
	/**
	 * Determines if the subclass is similar enough to the parent class to refactor.
	 * @param subClass subclass to check
	 * @return if should be merged
	 */
	private boolean shouldMergeClasses(final CtClass<?> subClass) {
		List<CtField<?>> subClassFields = subClass.getFields();
		
		if (subClassFields.size() > fieldDifferenceTolerance) {
			return false;
		}
		
		Set<CtMethod<?>> subClassMethods = subClass.getMethods();
		Set<CtMethod<?>> getterSetterMethods = new HashSet<CtMethod<?>>();
		
		for (CtField<?> field : subClassFields) {
			String fieldName = field.getSimpleName().substring(0, 1).toUpperCase()
							   + field.getSimpleName().substring(1, field.getSimpleName().length());
			
			List<CtMethod<?>> getter = subClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("get" + fieldName)
																&& method.getType().equals(field.getType()))
																.list();
			
			if (getter.size() == 1) {
				getterSetterMethods.add(getter.get(0));
			}
			
			List<CtMethod<?>> setter = subClass.filterChildren((CtMethod<?> method) -> method.getSimpleName().equals("set" + fieldName)
					&& method.getType().equals(new TypeFactory().VOID_PRIMITIVE))
					.list();
			
			if (setter.size() == 1) {
				getterSetterMethods.add(setter.get(0));
			}
		}
		
		subClassMethods.removeAll(getterSetterMethods);
		
		//check if method makes call to super class method
		for (CtMethod<?> method : subClassMethods) {
			List<CtStatement> methodStatements = method.getBody().getStatements();
			
			for (CtStatement statement : methodStatements) {
				if (statement.toString().contains("super." + method.getSimpleName())) {
					return false;
				}
			}
		}
		
		if (subClassMethods.size() > methodDifferenceTolerance) {
			return false;
		}
		
		return true;
		
	}	
}
