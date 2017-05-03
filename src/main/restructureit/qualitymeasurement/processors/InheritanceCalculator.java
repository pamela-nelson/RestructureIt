package restructureit.qualitymeasurement.processors;

import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;

/**
 * Calculates the average ratio of the number of methods inherited by a class 
 * to the total number of methods accessible by member methods of the class.
 * Also known as Measure of Functional Abstraction.
 */
public class InheritanceCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Average inheritance in input project.
	 */
	private static double inheritance = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0; 
	
	/**
	 * @return the inheritance
	 */
	public static double getInheritance() {
		return inheritance;
	}
	
	/**
	 * Resets inheritance value to zero.
	 */
	public static void resetInheritanceValue() {
		inheritance = 0;
	}

	/**
	 * Calculates the inheritance of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {		
		int numInheritedMethods = 0;
		int totalNumMethodsInClass = 0;
		Set<CtMethod<?>> classMethods;
		CtClass<?> parentClass;
		
		numClasses++;
		
		classMethods = ctClass.getMethods();
		totalNumMethodsInClass = classMethods.size();
		
		if (ctClass.getSuperclass() != null) {
			parentClass = (CtClass<?>) ctClass.getSuperclass().getTypeDeclaration();
				
			while (parentClass != null) {
				classMethods = parentClass.getMethods();
				
				for (CtMethod<?> method : classMethods) {
					if (method.getModifiers().contains(ModifierKind.PUBLIC) || method.getModifiers().contains(ModifierKind.PROTECTED)) {
						numInheritedMethods++;
					}
				}
				
				if (parentClass.getSuperclass() != null) {
					parentClass = (CtClass<?>) parentClass.getSuperclass().getTypeDeclaration();
				} else {
					break;
				}

			}
		}
		
		
		totalNumMethodsInClass += numInheritedMethods;
		
		if (totalNumMethodsInClass > 0) {
			inheritance += (double) numInheritedMethods / (double) totalNumMethodsInClass;
		}			
	}
	
	/**
	 * Calculates average inheritance when all classes have been processed.
	 */
	public void processingDone() {
		inheritance = inheritance / (double) numClasses;
	}

}
