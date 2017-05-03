package restructureit.qualitymeasurement.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

/** 
* Calculates the average number of classes from which a class inherits information in a given project.
* Also known as average number of ancestors
*/

public class AbstractionCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Number of classes in the given project.
	 */
	private int numClasses = 0;
	
	/**
	 * Total of the distances each class is from its root hierarchy.
	 */
	private int totalDistanceFromRoot = 0;
	
	/**
	 * Average number of ancestors.
	 */
	private static double abstraction = 0;
	
	/**
	 * @return the abstraction
	 */
	public static double getAbstraction() {
		return abstraction;
	}
	
	/**
	 * Resets abstraction value to zero.
	 */
	public static void resetAbstractionValue() {
		abstraction = 0;
	}

	/**
	 * Calculates the average number of ancestors of the project.
	 * @param ctClass class in project
	 */
	public void process(final CtClass<?> ctClass) {
		
		numClasses++;
		
		CtClass<?> tempClass = ctClass;
		
		while (tempClass.getParent(CtClass.class) != null) {
			totalDistanceFromRoot++;
			tempClass = (CtClass<?>) tempClass.getParent(CtClass.class);
		}
	}
	
	/**
	 * Calculates abstraction when all classes have been processed.
	 */
	public void processingDone() {
		abstraction = (double) totalDistanceFromRoot / (double) numClasses;
	}

}
