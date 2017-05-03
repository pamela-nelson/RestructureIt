package restructureit.refactorings.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;

/**
 * Performs the refactoring Extract Superclass. It creates a super class for classes with similar fields/methods.
 * For simplicity the best matching class is used to create a superclass.
 * 
 * Reference: https://refactoring.com/catalog/extractSuperclass.html
 *
 */
public class ExtractSuperClass extends AbstractProcessor<CtClass<?>> {
	/**
	 * Total number of times the encapsulateField refactoring was performed.
	 */
	private static int timesApplied = 0;
	
	/**
	 * Percentage of fields that must the same for a class to be considered similar enough to extract 
	 * a superclass.
	 */
	private double fieldSimilarityTolerance = 0.7;
	
	/**
	 * Percentage of methods that must be the same for a class to be considered similar enough to extract 
	 * a superclass.
	 */
	private double methodSimilarityTolerance = 0.5;

	/**
	 * Root Package of the project.
	 */
	private CtPackage rootPackage;
	
	/**
	 * Best matching class to create superclass with.
	 */
	CtClass<?> bestMatchingClass;
	
	/**
	 * MatchingFields of class.
	 */
	List<CtField<?>> matchingFields = new ArrayList<CtField<?>>();
	
	/**
	 * Matching Methods of class.
	 */
	Set<CtMethod<?>> matchingMethods = new HashSet<CtMethod<?>>();
	
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
	 * Determines whether a candidate class should have a super class extracted.
	 * @param candidateClass  class of AST to check
	 * @return if class should be refactored
	 */
	public boolean isToBeProcessed(final CtClass<?> candidateClass) {
		
		if (rootPackage == null) {
			rootPackage = RefactoringHelperUtils.getRootPackage(candidateClass);	
		}
		
		//Check if candidate class has a parent class
		if (candidateClass.getSuperclass() != null) {
			return false;
		}
		
		//Check if classes exist that are similar in the same package
		if (!hasSimilarClass(candidateClass)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Applies the extract superclass refactoring to the candidate class and its best matching class.
	 * @param candidateClass class to apply refactoring to
	 */
	public void process(final CtClass<?> candidateClass) {
		timesApplied++;
		
		//create super class
		CtClass<?> superClass = getFactory().createClass();
		candidateClass.getPackage().addType(superClass);
		superClass.setSimpleName(candidateClass.getSimpleName() + bestMatchingClass.getSimpleName() + "Super");
		superClass.addModifier(ModifierKind.PUBLIC);
		superClass.addModifier(ModifierKind.ABSTRACT);
		superClass.setFields(matchingFields);
		superClass.setMethods(matchingMethods);
		
		candidateClass.setSuperclass(superClass.getReference());
		bestMatchingClass.setSuperclass(superClass.getReference());
		
		//remove matching methods from subclasses
		for (CtField<?> field : matchingFields) {
			candidateClass.removeField(field);
			
			CtField<?> matchingField = bestMatchingClass.getField(field.getSimpleName());
			bestMatchingClass.removeField(matchingField);
		}
		
		//remove matching fields from subclasses
		for (CtMethod<?> method : matchingMethods) {
			candidateClass.removeMethod(method);
			
			List<CtMethod<?>> matchingMethod = bestMatchingClass.filterChildren((CtMethod<?> ctMethod) -> ctMethod.getSignature().equals(method.getSignature()))
																					.list();
			
			bestMatchingClass.removeMethod(matchingMethod.get(0));
		}
		
	}

	/**
	 * Determines if a similar class exists.
	 * @param candidateClass class
	 * @return similar class exists
	 */
	private boolean hasSimilarClass(final CtClass<?> candidateClass) {
		List<CtField<?>> classFields = candidateClass.getFields();
		Set<CtMethod<?>> classMethods = candidateClass.getMethods();
		
		List<CtClass<?>> classes = rootPackage.filterChildren((CtClass<?> classs) -> classs.getSuperclass() == null
																&& classs.getPackage() != null
																&& classs.getPackage().equals(candidateClass.getPackage())
																&& !classs.getSimpleName().equals(candidateClass.getSimpleName()))
																.list();
		
		bestMatchingClass = null;
		int bestMatchingFields = 0;
		int bestMatchingMethods = 0;
		List<CtField<?>> matchingFieldsList = new ArrayList<CtField<?>>();
		Set<CtMethod<?>> matchingMethodsList = new HashSet<CtMethod<?>>();
		
		for (CtClass<?> ctClass : classes) {
			int matchingFieldsCount = 0;
			int matchingMethodsCount = 0;
			
			for (CtField<?> candidateClassField : classFields) {
				CtField<?> field = ctClass.getField(candidateClassField.getSimpleName());
				
				if (field != null 
					&& ((field.getDefaultExpression() == null && candidateClassField.getDefaultExpression() == null)
					 || ((field.getDefaultExpression() != null && candidateClassField.getDefaultExpression() != null)
						&& field.getDefaultExpression().equals(candidateClassField.getDefaultExpression())))
					&& field.getType().equals(candidateClassField.getType())) {
					matchingFieldsCount++;
					matchingFieldsList.add(candidateClassField);
				}
			}
			
			if (!(matchingFieldsCount >= Math.round(fieldSimilarityTolerance * classFields.size()))) {
				matchingFieldsList.clear();
				continue;
			}
			
			for (CtMethod<?> candidateClassMethod : classMethods) {
				List<CtMethod<?>> method = ctClass.filterChildren((CtMethod<?> ctMethod) -> ctMethod.getSignature().equals(candidateClassMethod.getSignature())
																	&& ctMethod.getBody() != null && candidateClassMethod.getBody() != null
																	&& ctMethod.getBody().getStatements().size() == candidateClassMethod.getBody().getStatements().size())
																	.list();
				
				if (method.size() == 1) {
					boolean statementsMatch = true;
					
					List<CtStatement> classMethodStatements = candidateClassMethod.getBody().getStatements();
					List<CtStatement> methodStatements = method.get(0).getBody().getStatements();
					
					for (int i = 0; i < classMethodStatements.size(); i++) {
						if (!classMethodStatements.get(i).toString().equals(methodStatements.get(i).toString())) {
							statementsMatch = false;
						}
					}
					
					if (!statementsMatch) {
						continue;
					} else {
						matchingMethodsCount++;
						matchingMethodsList.add(candidateClassMethod);
					}
				}
			}
			
			if (!(matchingMethodsCount >= Math.round(methodSimilarityTolerance * classMethods.size()))) {
				matchingFieldsList.clear();
				matchingMethodsList.clear();
				continue;
			}
			
			if ((matchingFieldsCount + matchingMethodsCount) > (bestMatchingFields + bestMatchingMethods)) {
				bestMatchingFields = matchingFieldsCount;
				bestMatchingMethods = matchingMethodsCount;
				bestMatchingClass = ctClass;
				matchingFields = new ArrayList<>(matchingFieldsList);
				matchingMethods = new HashSet<>(matchingMethodsList);
			}
		}
		
		return bestMatchingClass != null;
	}
}
