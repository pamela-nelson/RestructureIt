package restructureit.qualitymeasurement.processors;

import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

/**
 * Calculates the average count of all the methods defined in a class. (the complexity)
 * Also known as number of methods
 */
public class ComplexityCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Average complexity in input project.
	 */
	private static double complexity = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0; 
	
	/**
	 * Total number of methods in project.
	 */
	private int totalNumMethods = 0;
	
	/**
	 * @return the complexity
	 */
	public static double getComplexity() {
		return complexity;
	}

	/**
	 * Calculates the complexity of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {		
		Set<CtMethod<?>> classMethods;
		
		numClasses++;
		
		classMethods = ctClass.getMethods();
		
		totalNumMethods += classMethods.size();
	}
	
	/**
	 * Calculates average number of methods when all classes have been processed.
	 */
	public void processingDone() {
		complexity = (double) totalNumMethods / (double) numClasses;
	}

}
