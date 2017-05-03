package restructureit.qualitymeasurement.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

/** 
 * Calculates the design size of the input project.
 * 
 */
public class DesignSizeCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Number of classes in input project.
	 */
	private static int numClasses = 0;
		
	/**
	 * @return the numClasses
	 */
	public static int getDesignSize() {
		return numClasses;
	}
	
	/**
	 * Resets design size value to zero.
	 */
	public static void resetDesignSizeValue() {
		numClasses = 0;
	}

	/**
	 * Calculates the design size of the project.
	 * @param element  class in project
	 */
	public void process(final CtClass<?> element) {
		numClasses++;
	}
	
}
