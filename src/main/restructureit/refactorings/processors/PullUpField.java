package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtFieldReference;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.AbstractFilter;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * Performs the refactoring Pull up field. It moves a field up to its parent class if 
 * two or more subclasses declare the same field.
 * 
 * Reference: https://www.refactoring.com/catalog/pullUpField.html
 *
 */
public class PullUpField extends AbstractProcessor<CtField<?>> {

	/**
	 * Total number of times the pull up refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * List to hold all classes that contain the field to be pulled up.
	 */
	private List<CtClass<?>> classesContainingField = new ArrayList<CtClass<?>>();
	
	/**
	 * List to hold all classes that use field in read or write operations.
	 */
	private List<CtClass<?>> classesUsingField = new ArrayList<CtClass<?>>();
	
	/**
	 * Tolerance level which indicates what percentage of subclasses should use the field for it to be considered
	 * for moving up.
	 */
	private double toleranceLevel = (double) 2 / 3;
	
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
	 * Determines if this field should be pulled up.
	 * @param candidateField filed to be checked
	 * @return if field should be pulled up
	 */
	public boolean isToBeProcessed(final CtField<?> candidateField) {
		
		CtClass<?> enclosingClass = (CtClass<?>) candidateField.getParent(CtClass.class);
		
		if (enclosingClass == null) {
			return false;
		}
		
		//Super class so cannot pull up field further
		if (enclosingClass.getSuperclass() == null
			|| enclosingClass.getSuperclass().getDeclaration() == null) {
			return false;
		}
		
		CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
		
		//Check parent class does not already contain field
		Collection<CtFieldReference<?>> parentClassFieldReferences = parentClass.getDeclaredFields();
		
		for (CtFieldReference<?> fieldReference : parentClassFieldReferences) {
			if (fieldReference.getDeclaration().equals(candidateField)) {
				return false;
			}
		}
		
		//Check at least 2 subclasses of parent class have the same field declared
		List<CtClass<?>> subclasses = RefactoringHelperUtils.getAllSubClasses(parentClass);
		
		int matchingFieldCount = 0;
		
		for (CtClass<?> subClass : subclasses) {
			Collection<CtFieldReference<?>> subClassFieldReferences = subClass.getDeclaredFields();
			
			for (CtFieldReference<?> fieldReference : subClassFieldReferences) {
				if (fieldReference.getDeclaration().equals(candidateField)) {
					matchingFieldCount++;
					classesContainingField.add(subClass);
				}
			}
		}
		
		if (matchingFieldCount < 2) {
			classesContainingField.clear();
			return false;
		}
		
		//Check if percentage of subclasses that use the field is sufficient to warrant moving the field up
		//Must be more than specified tolerance.
		int numSubClassesFieldReferences = 0;
		
		CtPackage rootPackage = RefactoringHelperUtils.getRootPackage(enclosingClass);
		
		List<CtFieldAccess> allFieldAccesses = rootPackage.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
		
		for (CtClass<?> subClass : subclasses) {
			//Subclass uses field itself
			List<CtFieldAccess> classFieldAccesses = subClass.filterChildren(new TypeFilter(CtFieldAccess.class)).list();
			
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
						&& fieldAccess.getVariable().equals(candidateField.getReference())
						&& fieldAccess.getVariable().getDeclaringType().getSimpleName().equals(candidateField.getDeclaringType().getSimpleName())) {
						
						numSubClassesFieldReferences++;
						classesUsingField.add(subClass);
						break;
					}
				}
			}
		}
		
		if (numSubClassesFieldReferences < Math.round((double) subclasses.size() * toleranceLevel)) {
			classesUsingField.clear();
			classesContainingField.clear();
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
		CtClass<?> parentClass = (CtClass<?>) enclosingClass.getSuperclass().getDeclaration();
		
		//If field is private set to protected
		if ((candidateField.hasModifier(ModifierKind.PRIVATE)) 
			|| (!candidateField.hasModifier(ModifierKind.PROTECTED) 
				&& !candidateField.hasModifier(ModifierKind.PUBLIC))) {
			Set<ModifierKind> modifiers = candidateField.getModifiers();
			modifiers.remove(ModifierKind.PRIVATE);
			candidateField.setModifiers(modifiers);
			candidateField.addModifier(ModifierKind.PROTECTED);
		}
		
		for (CtClass<?> subClass : classesContainingField) {
			CtField<?> classMethod = subClass.filterChildren((CtField field) -> field.getSimpleName().equals(candidateField.getSimpleName())).list().get(0);
			subClass.removeField(classMethod);
		}
		
		parentClass.addField(candidateField);
		
		classesContainingField.clear();
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
