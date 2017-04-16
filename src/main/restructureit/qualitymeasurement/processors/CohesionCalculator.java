package restructureit.qualitymeasurement.processors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.reference.CtTypeReference;

/**
 * Calculates average cohesion of the project which is the relatedness among methods 
 * of a class based upon the parameter list of methods.
 * Also known as Cohesion Among Methods in Class.
 */
public class CohesionCalculator extends AbstractProcessor<CtClass<?>> {

	/**
	 * Cohesion in input project.
	 */
	private static double cohesion = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0;
	
	/**
	 * @return the numClasses
	 */
	public static double getCohesion() {
		return cohesion;
	}
	
	/**
	 * Calculates the average cohesion of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {
		numClasses++;
		
		double classCohesion = 0;
		int numMethods = 0;
		
		Set<CtMethod<?>> methods;
		HashSet<CtTypeReference<?>> distinctMethodTypes = new HashSet<CtTypeReference<?>>();
		HashSet<CtTypeReference<?>> allDistinctMethodTypes = new HashSet<CtTypeReference<?>>();
		
		methods = ctClass.getMethods();
		numMethods = methods.size();
		
		CtTypeReference<?> methodType;
		List<CtParameter<?>> parameterTypes;
		for (CtMethod<?> method : methods) {
			parameterTypes = method.getParameters();
			
			for (CtParameter<?> parameterType : parameterTypes) {
				methodType =  parameterType.getType();
				distinctMethodTypes.add(methodType);
				allDistinctMethodTypes.add(methodType);
			}
			
			classCohesion += distinctMethodTypes.size();
			distinctMethodTypes.clear();
		}
		
		if ((numMethods * allDistinctMethodTypes.size()) > 0) {
			cohesion += classCohesion / (numMethods * allDistinctMethodTypes.size());
		}
	}
	
	/**
	 * Calculates average cohesion when all classes have been processed.
	 */
	public void processingDone() {
		cohesion = cohesion / (double) numClasses;
	}
}
