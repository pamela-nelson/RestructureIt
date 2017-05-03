package restructureit.refactorings.processors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Hide method. It makes a method private if only
 * its enclosing class is using it.
 * 
 * Reference:  https://www.refactoring.com/catalog/hideMethod.html
 *
 */
public class HideMethod extends AbstractProcessor<CtMethod<?>> {
	/**
	 * Total number of times the hide method refactoring was performed.
	 */
	private static int timesApplied = 0;
	
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
	 * Projects root package.
	 */
	CtPackage rootPackage;
	
	/**
	 * Determines if this method should be hidden.
	 * @param candidateMethod method to be checked
	 * @return if method should be hidden
	 */
	public boolean isToBeProcessed(final CtMethod<?> candidateMethod) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		if (rootPackage == null) {
			rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);
		}
		
		if (enclosingClass == null) {
			return false;
		}
		
		List<CtClass<?>> subClasses = RefactoringHelperUtils.getAllSubClasses(enclosingClass);
		
		//Check method is visible (Not already private)
		if (candidateMethod.getModifiers().contains(ModifierKind.PRIVATE)) {
			return false;
		}
		
		//Check if outside classes or subclasses use method
		List<CtInvocation<?>> allMethodInvocations = rootPackage.filterChildren(new TypeFilter(CtInvocation.class)).list();
		
		for (CtInvocation<?> methodInvocation : allMethodInvocations) {
			if (methodInvocation.getTarget() != null
				&& methodInvocation.getExecutable().getActualMethod() != null 
				&& methodInvocation.getTarget().getType().getSimpleName().equals(enclosingClass.getSimpleName())
				&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
				
				return false;
			}
		}
		
		for (CtClass<?> subClass : subClasses) {
			//Subclass calls method itself
			List<CtInvocation<?>> methodInvocations = subClass.filterChildren(new TypeFilter(CtInvocation.class)).list();
			
			for (CtInvocation<?> methodInvocation : methodInvocations) {
				if (methodInvocation.getExecutable().getActualMethod() != null 
						&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {
					return false;
				}
			}
			
			//subclass method is called
			for (CtInvocation<?> methodInvocation : allMethodInvocations) {
				if (methodInvocation.getTarget() != null
						&& methodInvocation.getExecutable().getActualMethod() != null 
						&& methodInvocation.getTarget().getType().getSimpleName().equals(subClass.getSimpleName())
						&& RefactoringHelperUtils.getMethodInvocationSignature(methodInvocation).equals(candidateMethod.getSignature())) {

					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Hides the candidate method.
	 * @param candidateMethod method to hide.
	 */
	public void process(final CtMethod<?> candidateMethod) {
		timesApplied++;
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateMethod.getParent(CtClass.class);
		
		CtMethod<?> method = candidateMethod;
		Set<ModifierKind> methodModifiers = new HashSet<ModifierKind>();
		methodModifiers.add(ModifierKind.PRIVATE);
		
		for (ModifierKind modifier : candidateMethod.getModifiers()) {
			if (modifier != ModifierKind.PUBLIC && modifier != ModifierKind.PROTECTED) {
				methodModifiers.add(modifier);
			} 
		}
		
		candidateMethod.setModifiers(methodModifiers);
	}
}
