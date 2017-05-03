package restructureit.qualitymeasurement.processors;

import java.util.List;

import javax.lang.model.element.Modifier;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;

/**
 * Calculates the ratio of the number of private attributes to the 
 * total number of attributes declared in each class.
 * Also known as Data Access Metrics
 */
public class EncapsulationCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Encapsulation in input project. (public/private field ratio)
	 */
	private static double encapsulation = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0;
		
	/**
	 * @return the numClasses
	 */
	public static double getEncapsulation() {
		return encapsulation;
	}
	
	/**
	 * Resets encapsulation value to zero.
	 */
	public static void resetEncapsulationValue() {
		encapsulation = 0;
	}

	/**
	 * Calculates the encapsulation of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {
		int numFields = 0;
		int numPrivateFields = 0;
		
		numClasses++;
		
		List<CtField<?>> fields = ctClass.getFields();
		numFields = fields.size();
		
		for (CtField<?> field : fields) {
			if (!field.getModifiers().contains(Modifier.PUBLIC)) {
				numPrivateFields++;
			}
		}
		
		if (numFields > 0) {
			encapsulation += numPrivateFields / numFields;
		}
		
	}
	
	/**
	 * Calculates overall encapsulation when all classes have been processed.
	 */
	public void processingDone() {
		encapsulation = encapsulation / (double) numClasses;
	}

}
