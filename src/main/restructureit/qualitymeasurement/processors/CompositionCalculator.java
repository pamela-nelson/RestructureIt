package restructureit.qualitymeasurement.processors;

import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

/**
 * Calculates Extent of part-whole relationship, realized by using attributes which simply is
 * the average amount of user defined attributes declared per class.
 * Also known as Measure of Aggregation.
 */
public class CompositionCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Average composition in input project.
	 */
	private static double composition = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0;
	
	/**
	 * Total number of attributes in project.
	 */
	private int totalNumFields = 0;
	
	/**
	 * @return composition
	 */
	public static double getComposition() {
		return composition;
	}

	/**
	 * Calculates the composition of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {		
		numClasses++;
		
		List<CtField<?>> fields = ctClass.getFields();
		totalNumFields += fields.size();	
	}
	
	/**
	 * Calculates average composition when all classes have been processed.
	 */
	public void processingDone() {
		composition = (double) totalNumFields / (double) numClasses;
	}

}
