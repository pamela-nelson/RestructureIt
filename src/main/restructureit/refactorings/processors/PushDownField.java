package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Push down field. It moves a field down to the subclasses classes 
 * that use it.
 * 
 * Reference: https://www.refactoring.com/catalog/pushDownField.html
 *
 */
public class PushDownField extends AbstractProcessor<CtField<?>> {
	
	/**
	 * Total number of times the pull up refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * List to hold all classes that use field in read or write operations.
	 */
	private List<CtClass<?>> classesUsingField = new ArrayList<CtClass<?>>();
	
	/**
	 * Tolerance level which indicates the maxiumum percentage of subclasses should use the field for it to be considered
	 * for moving down.
	 */
	private double toleranceLevel = 0.4;
	
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
	 * Determines if this field should be pushed down.
	 * @param candidateField filed to be checked
	 * @return if field should be pulled up
	 */
	public boolean isToBeProcessed(final CtField<?> candidateField) {
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		if (enclosingClass == null) {
			return false;
		}
		
		List<CtClass<?>> subClasses = RefactoringHelperUtils.getAllSubClasses(enclosingClass);
		
		//Check field is not private
		if (candidateField.getModifiers().contains(ModifierKind.PRIVATE)) {
			return false;
		}
		
		//Check enclosing class has children to move field down to
		if (subClasses.size() == 0) {
			return false;
		}
		
		//Check parent classes do not have the same field
		if (enclosingClass.getSuperclass() != null
			&& enclosingClass.getSuperclass().getDeclaration() != null) {
			CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
			do {
				Collection<CtFieldReference<?>> parentClassFieldReferences = parentClass.getDeclaredFields();
				
				for (CtFieldReference<?> fieldReference : parentClassFieldReferences) {
					if (fieldReference.getDeclaration().equals(candidateField)) {
						return false;
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
		
		//Check this field is not used in current class
		CtPackage rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);
		
		//References inside class
		List<CtFieldAccess> classFieldAccesses = enclosingClass.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
		List<CtFieldAccess> allFieldAccesses = rootPackage.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
		
		for (CtFieldAccess<?> fieldAccess : classFieldAccesses) {
			if ((fieldAccess.getVariable().getDeclaration() != null
				&& fieldAccess.getVariable().getDeclaration().equals(candidateField))
				|| (fieldAccess.getVariable().getSimpleName().equals(candidateField.getSimpleName())
				&& fieldAccess.getVariable().getType().getSimpleName().equals(candidateField.getType().getSimpleName()))) {
					return false;
				}
		}
		
		//field is accessed using outside class
		if (!candidateField.getModifiers().contains(ModifierKind.PRIVATE)) {
			
			for (CtFieldAccess<?> fieldAccess : allFieldAccesses) {
				if (fieldAccess.getTarget() != null
					&& fieldAccess.getTarget().getType().getSimpleName().equals(enclosingClass.getSimpleName())
					&& fieldAccess.getVariable().getDeclaringType() != null
					&& fieldAccess.getVariable().getDeclaringType().getSimpleName().equals(candidateField.getDeclaringType().getSimpleName())
					&& fieldAccess.getVariable().getSimpleName().equals(candidateField.getSimpleName())
					&& fieldAccess.getVariable().getType().getSimpleName().equals(candidateField.getType().getSimpleName())) {
					
					return false;
				}
			}
		}
	
		//Check if percentage of subclasses that use the field is sufficiently small to warrant moving the field down
		//Must be less than specified tolerance. (40%)
		
		int numSubClassesFieldReferences = 0;
		
		for (CtClass<?> subClass : subClasses) {
			//Subclass uses field itself
			classFieldAccesses = subClass.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
			
			for (CtFieldAccess<?> fieldAccess : classFieldAccesses) {
				if ((fieldAccess.getVariable().getDeclaringType() != null
					&& fieldAccess.getVariable().equals(candidateField.getReference())
					&& fieldAccess.getVariable().getDeclaringType().getSimpleName().equals(candidateField.getDeclaringType().getSimpleName())
					&& fieldAccess.getVariable().getSimpleName().equals(candidateField.getSimpleName())
					&& fieldAccess.getVariable().getType().getSimpleName().equals(candidateField.getType().getSimpleName()))
					|| (fieldAccess.getVariable().getSimpleName().equals(candidateField.getSimpleName())
					&& fieldAccess.getVariable().getType().getSimpleName().equals(candidateField.getType().getSimpleName()))) {
					if (!classesUsingField.contains(subClass)) {
						numSubClassesFieldReferences++;
						classesUsingField.add(subClass);
						
						CtMethod<?> method = fieldAccess.getParent().getParent(CtMethod.class);
						numSubClassesFieldReferences += checkInheritedAccesses(subClass, method);
						break;
					}
				}
			}
			
			//subclass field is accessed outside
			if (!candidateField.getModifiers().contains(ModifierKind.PRIVATE)
				&& !classesUsingField.contains(subClass)) {
				
				for (CtFieldAccess<?> fieldAccess : allFieldAccesses) {
					if (fieldAccess.getTarget() != null
						&& fieldAccess.getTarget().getType().getSimpleName().equals(subClass.getSimpleName())
						&& fieldAccess.getVariable().getDeclaringType() != null
						&& fieldAccess.getVariable().getDeclaringType().getSimpleName().equals(candidateField.getDeclaringType().getSimpleName())
						&& fieldAccess.getVariable().getSimpleName().equals(candidateField.getSimpleName())
						&& fieldAccess.getVariable().getType().getSimpleName().equals(candidateField.getType().getSimpleName())) {
						
						numSubClassesFieldReferences++;
						classesUsingField.add(subClass);
						break;
					}
				}
			}
		}
		
		if (numSubClassesFieldReferences > Math.round((double) subClasses.size() * toleranceLevel)) {
			classesUsingField.clear();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Moves the candidate field up to its class's parent class.
	 * @param candidateField field to move up.
	 */
	public void process(final CtField<?> candidateField) {
		timesApplied++;
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);

		
		//If field is private set to protected
		if ((candidateField.hasModifier(ModifierKind.PRIVATE)) 
			|| (!candidateField.hasModifier(ModifierKind.PROTECTED) 
				&& !candidateField.hasModifier(ModifierKind.PUBLIC))) {
			Set<ModifierKind> modifiers = candidateField.getModifiers();
			modifiers.remove(ModifierKind.PRIVATE);
			candidateField.setModifiers(modifiers);
			candidateField.addModifier(ModifierKind.PROTECTED);
		}
		
		List<CtClass<?>> highestSubclasses = RefactoringHelperUtils.getHighestSubClasses(classesUsingField);
		
		for (CtClass<?> subClass : highestSubclasses) {
			subClass.addFieldAtTop(candidateField);
		}
		
		enclosingClass.removeField(candidateField);
		
		classesUsingField.clear();
	}
	
	/**
	 * Checks if subclasses inherited method containing a field access.
	 * @param parentClass class
	 * @param method method to check
	 * @return count of matching classes
	 */
	private int checkInheritedAccesses(final CtClass<?> parentClass, final CtMethod<?> method) {
		List<CtClass<?>> subClasses = RefactoringHelperUtils.getAllSubClasses(parentClass);
		
		int countMatchingAccesses = 0;
		
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
				if (inheritedMethod.getSignature().equals(method.getSignature())) {
					if (!classesUsingField.contains(subClass)) {
						countMatchingAccesses++;
						classesUsingField.add(subClass);
						break;
					}
				}
			}
		}
		
		return countMatchingAccesses;
	}
}
