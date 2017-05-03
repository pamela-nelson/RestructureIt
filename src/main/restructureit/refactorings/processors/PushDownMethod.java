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
 * Performs the refactoring Push down method. It moves a method down to the subclasses
 * that use it.
 * 
 * Reference: https://www.refactoring.com/catalog/pushDownMethod.html
 *
 */
public class PushDownMethod extends AbstractProcessor<CtMethod<?>> {

	/**
	 * Total number of times the push down refactoring was performed.
	 */
	private static int timesApplied = 0;
	
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
	private double toleranceLevel = 0.4;
	
	/**
	 * Determines if this method should be pushed down.
	 * @param candidateMethod method to be checked
	 * @return if method should be pushed down
	 */
	public boolean isToBeProcessed(final CtMethod<?> candidateMethod) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		
		if (enclosingClass == null) {
			return false;
		}
		
		List<CtClass<?>> subClasses = RefactoringHelperUtils.getAllSubClasses(enclosingClass);

		//Check method is not private
		if (candidateMethod.getModifiers().contains(ModifierKind.PRIVATE)) {

			return false;
		}

		//Check enclosing class has children to move method down to
		if (subClasses.size() == 0) {
			return false;
		}

		//Check parent classes do not have the same method
		if (enclosingClass.getSuperclass() != null) {
			CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
			do {
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

				if (parentClass.getSuperclass() != null) {
					parentClass = (CtClass<?>) parentClass.getSuperclass().getDeclaration();
				} else {
					break;
				}
			}
			while (true);
		}

		//check if private fields in enclosing class are used in method
		List<CtFieldAccess<?>> fieldAccesses = candidateMethod.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
		List<CtField<?>> fieldsAccessed = RefactoringHelperUtils.getFieldsFromFieldAccesses(fieldAccesses);
		
		Collection<CtFieldReference<?>> enclosingClassFieldReferences = enclosingClass.getAllFields();
		Collection<CtField<?>> enclosingClassFields = new ArrayList<>();
		
		for (CtFieldReference<?> fieldReference : enclosingClassFieldReferences) {
			enclosingClassFields.add(fieldReference.getFieldDeclaration());
		}
		
		enclosingClassFields.retainAll(fieldsAccessed);
		
		for (CtField<?> enclosingClassField : enclosingClassFields) {
			if (enclosingClassField.getModifiers().contains(ModifierKind.PRIVATE)) {
				return false;
			}
		}

		//Check method is not used in current class
		CtPackage rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);
		List<CtInvocation<?>> allMethodInvocations = rootPackage.filterChildren(new TypeFilter(CtInvocation.class)).list();
		
		//check for invocations to method inside class
		List<CtInvocation<?>> methodInvocations = enclosingClass.filterChildren(new TypeFilter(CtInvocation.class)).list();
		
		for (CtInvocation<?> methodInvocation : methodInvocations) {
			if (methodInvocation.getExecutable().getActualMethod() != null 
				&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
				return false;
			}
		}
		
		//Check for invocation of the method outside class
		for (CtInvocation<?> methodInvocation : allMethodInvocations) {
			if (methodInvocation.getTarget() != null
				&& methodInvocation.getExecutable().getActualMethod() != null 
				&& methodInvocation.getTarget().getType().getSimpleName().equals(enclosingClass.getSimpleName())
				&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
				
				return false;
			}
		}
		
		//Check if percentage of subclasses that use the method is sufficiently small to warrant moving the method down
		//Must be less than specified tolerance. (40%)	
		int numSubClassesMethodReferences = 0;
		
		for (CtClass<?> subClass : subClasses) {
			//Subclass calls method itself
			methodInvocations = subClass.filterChildren(new TypeFilter(CtInvocation.class)).list();
			
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
		
		if (numSubClassesMethodReferences > Math.round((double) subClasses.size() * toleranceLevel)) {
			classesUsingMethod.clear();
			return false;
		}

		return true;
	}
	
	/**
	 * Moves the candidate method down to the subclasses that use it.
	 * @param candidateMethod method to move down.
	 */
	public void process(final CtMethod<?> candidateMethod) {
		timesApplied++;
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		
		List<CtClass<?>> highestSubclasses = RefactoringHelperUtils.getHighestSubClasses(classesUsingMethod);
		
		for (CtClass<?> subClass : highestSubclasses) {
			Set<CtMethod<?>> classMethods = subClass.getMethods();
			
			boolean containsMethod = false;
			
			for (CtMethod<?> classMethod : classMethods) {
				if (classMethod.getSignature().equals(candidateMethod.getSignature())
					&& classMethod.getBody().getStatements().size() == (candidateMethod.getBody().getStatements().size())) {
					containsMethod = true;
					break;
				}
			}
			
			if (!containsMethod) {
				subClass.addMethod(candidateMethod);
			}
		}
		
		enclosingClass.removeMethod(candidateMethod);
		
		classesUsingMethod.clear();
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
