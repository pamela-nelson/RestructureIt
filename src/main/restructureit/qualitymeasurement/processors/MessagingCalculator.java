package restructureit.qualitymeasurement.processors;

import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;

/**
 * Calculates the average count of the number of public methods in a class.
 * Also known as Class Interface Size.
 */
public class MessagingCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Average class interface size in input project.
	 */
	private static double messaging = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0; 
	
	/**
	 * Total number of public methods in project.
	 */
	private int totalNumPublicMethods = 0;
	
	/**
	 * @return the inheritance
	 */
	public static double getMessaging() {
		return messaging;
	}

	/**
	 * Calculates the messaging of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {		
		Set<CtMethod<?>> classMethods;
		
		numClasses++;
		
		classMethods = ctClass.getMethods();
		
		for (CtMethod<?> method : classMethods) {
			if (method.getModifiers().contains(ModifierKind.PUBLIC)) {
				totalNumPublicMethods++;
			}
		}
	}
	
	/**
	 * Calculates average number of public methods when all classes have been processed.
	 */
	public void processingDone() {
		messaging = (double) totalNumPublicMethods / (double) numClasses;
	}
	
}
