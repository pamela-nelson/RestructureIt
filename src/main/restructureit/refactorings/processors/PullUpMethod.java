package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Pull up method. It moves a method up to its parent class if 
 * two or more subclasses declare the same method with the same implementation.
 * 
 * Reference: https://www.refactoring.com/catalog/pullUpMethod.html
 *
 */
public class PullUpMethod extends AbstractProcessor<CtMethod<?>> {

	/**
	 * Total number of times the pull up refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * List to hold all classes that contain the method to be pulled up.
	 */
	private List<CtClass<?>> classesContainingMethod = new ArrayList<CtClass<?>>();
	
	/**
	 * List containing all classes that use this particular method.
	 */
	private List<CtClass<?>> classesUsingMethod = new ArrayList<CtClass<?>>();
	
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
	 * Tolerance level which indicates what percentage of subclasses should use the method for it to be considered
	 * for moving up.
	 */
	private double toleranceLevel = (double) 2 / 3;

	/**
	 * Determines if this method should be pulled up.
	 * @param candidateMethod method to be checked
	 * @return if method should be pulled up
	 */
	public boolean isToBeProcessed(final CtMethod<?> candidateMethod) {
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		
		if (enclosingClass == null) {
			return false;
		}
		
		//Super class so cannot pull up method further
		if (enclosingClass.getSuperclass() == null
			|| enclosingClass.getSuperclass().getDeclaration() == null) {
			return false;
		}
		
		CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
		
		//Check parent class does not already contain method
			Set<CtMethod<?>> classMethods = parentClass.getMethods();
			
			for (CtMethod<?> classMethod : classMethods) {
				if (classMethod.getSignature().equals(candidateMethod.getSignature())
					&& classMethod.getBody().getStatements().size() == (candidateMethod.getBody().getStatements().size())) {
					
					boolean statementsMatch = true;
					
					List<CtStatement> classMethodStatements = classMethod.getBody().getStatements();
					List<CtStatement> candidateMethodStatements = candidateMethod.getBody().getStatements();
					
					for (int i = 0; i < classMethodStatements.size(); i++) {
						if (!classMethodStatements.get(i).toString().equals(candidateMethodStatements.get(i).toString())) {
							statementsMatch = false;
						}
					}
					
					if (statementsMatch) {
						return false;
					}
				}
			}
		
		
		//Check that superclass has all fields referenced by candidate method
		List<CtFieldAccess<?>> fieldAccesses = candidateMethod.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
		List<CtField<?>> fieldsAccessed = RefactoringHelperUtils.getFieldsFromFieldAccesses(fieldAccesses);
		
		Collection<CtFieldReference<?>> enclosingClassFieldReferences = enclosingClass.getAllFields();
		Collection<CtField<?>> enclosingClassFields = new ArrayList<>();
		
		for (CtFieldReference<?> fieldReference : enclosingClassFieldReferences) {
			enclosingClassFields.add(fieldReference.getFieldDeclaration());
		}
		
		enclosingClassFields.retainAll(fieldsAccessed);
		
		Collection<CtFieldReference<?>> parentClassFieldReferences = parentClass.getAllFields();
		Collection<CtField<?>> parentClassFields = new ArrayList<>();
		
		for (CtFieldReference<?> fieldReference : parentClassFieldReferences) {
			parentClassFields.add(fieldReference.getFieldDeclaration());
		}
		
		if (!parentClassFields.containsAll(enclosingClassFields)) {
			return false;
		}
		
		//Check there is at least 2 children of the candidate parent class that have identical declared methods
		List<CtClass<?>> parentSubClasses = RefactoringHelperUtils.getAllSubClasses(parentClass);
		
		int methodMatchCount = 0;
		
		for (CtClass<?> subClass : parentSubClasses) {
			classMethods = subClass.getMethods();
			
			for (CtMethod<?> classMethod : classMethods) {
				if (classMethod.getSignature().equals(candidateMethod.getSignature())
					&& classMethod.getBody().getStatements().size() == (candidateMethod.getBody().getStatements().size())) {
					
					boolean statementsMatch = true;
					
					List<CtStatement> classMethodStatements = classMethod.getBody().getStatements();
					List<CtStatement> candidateMethodStatements = candidateMethod.getBody().getStatements();
					
					for (int i = 0; i < classMethodStatements.size(); i++) {
						if (!classMethodStatements.get(i).toString().equals(candidateMethodStatements.get(i).toString())) {
							statementsMatch = false;
						}
					}
					
					if (!statementsMatch) {
						break;
					}
					
					methodMatchCount++;
					classesContainingMethod.add(subClass);
				}
			}
		}
		
		if (methodMatchCount < 2) {
			classesContainingMethod.clear();
			return false;
		}
		
		//Check if percentage of subclasses that use the method is sufficient to warrant moving the method up
		//Must be more than specified tolerance.
		int numSubClassesMethodReferences = 0;
		
		CtPackage rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);
		
		List<CtInvocation<?>> allMethodInvocations = rootPackage.filterChildren(new TypeFilter(CtInvocation.class)).list();
		
		for (CtClass<?> subClass : parentSubClasses) {
			//Subclass calls method itself
			List<CtInvocation<?>> methodInvocations = subClass.filterChildren(new TypeFilter(CtInvocation.class)).list();
			
			for (CtInvocation<?> methodInvocation : methodInvocations) {
				if (methodInvocation.getExecutable().getActualMethod() != null 
						&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
					if (!classesUsingMethod.contains(subClass)) {
						numSubClassesMethodReferences++;
						classesUsingMethod.add(subClass);
						
						numSubClassesMethodReferences += checkMethodInvocations(subClass, methodInvocation);
					}
					break;
				}
			}
			
			//subclass method is called
			if (!classesUsingMethod.contains(subClass)) {
				for (CtInvocation<?> methodInvocation : allMethodInvocations) {
					if (methodInvocation.getTarget() != null
						&& methodInvocation.getExecutable().getActualMethod() != null 
						&& methodInvocation.getTarget().getType().getSimpleName().equals(subClass.getSimpleName())
						&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
						
						numSubClassesMethodReferences++;
						classesUsingMethod.add(subClass);
						break;
					}
				}
			}	
		}
		
		if (numSubClassesMethodReferences < Math.round((double) parentSubClasses.size() * toleranceLevel)) {
			classesUsingMethod.clear();
			classesContainingMethod.clear();
			return false;
		}
		
		classesUsingMethod.clear();
		
		return true;
	}
	
	/**
	 * Moves the candidate method up to its class's parent class.
	 * @param candidateMethod method to move up.
	 */
	public void process(final CtMethod<?> candidateMethod) {
		timesApplied++;
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
		
		//If method is private set to protected
		if ((candidateMethod.hasModifier(ModifierKind.PRIVATE)) 
			|| (!candidateMethod.hasModifier(ModifierKind.PROTECTED) 
				&& !candidateMethod.hasModifier(ModifierKind.PUBLIC))) {
			Set<ModifierKind> modifiers = candidateMethod.getModifiers();
			modifiers.remove(ModifierKind.PRIVATE);
			candidateMethod.setModifiers(modifiers);
			candidateMethod.addModifier(ModifierKind.PROTECTED);
		}
		
		for (CtClass<?> subClass : classesContainingMethod) {
			CtMethod<?> classMethod = subClass.filterChildren((CtMethod method) -> method.getSimpleName().equals(candidateMethod.getSimpleName())).list().get(0);
			subClass.removeMethod(classMethod);
		}
		
		parentClass.addMethod(candidateMethod);
		
		classesContainingMethod.clear();
		
	}
	
	/**
	 * Checks if subclasses inherited method containing a field access.
	 * @param parentClass class
	 * @param methodInvocation method to check
	 * @return count of matching classes
	 */
	private int checkMethodInvocations(final CtClass<?> parentClass, final CtInvocation<?> methodInvocation) {
		List<CtClass<?>> subClasses = RefactoringHelperUtils.getAllSubClasses(parentClass);
		
		int countMatchingMethodInovocations = 0;
		
		for (CtClass<?> subClass : subClasses) {
			Set<CtMethod<?>> declaredMethods = subClass.getMethods();
			Set<CtMethod<?>> allMethods = subClass.getAllMethods();
			Set<CtMethod<?>> inheritedMethods = new HashSet<CtMethod<?>>();
			
			for (CtMethod<?> classMethod : allMethods) {
				if (!declaredMethods.contains(classMethod)) {
					inheritedMethods.add(classMethod);
				}
			}
			
			for (CtMethod<?> inheritedMethod : inheritedMethods) {
				if (inheritedMethod.getSignature().equals(RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation))) {
					if (!classesUsingMethod.contains(subClass)) {
						countMatchingMethodInovocations++;
						classesUsingMethod.add(subClass);
						break;
					}
				}
			}
		}
		return countMatchingMethodInovocations;
	}
}
