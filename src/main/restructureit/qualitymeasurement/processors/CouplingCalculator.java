package restructureit.qualitymeasurement.processors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;

/**
 * Calculates the average coupling which is the total count of different number of classes that a class is directly related to.
 * The metric includes classes that are directly related by attribute declaration and message passing (parameters) in methods.
 * Also known as Direct Class Coupling.
 */
public class CouplingCalculator extends AbstractProcessor<CtClass<?>> {

/**
 * coupling of input project.
 */
private static double coupling = 0;

/**
 * Number of classes in input project.
 */
private int numClasses = 0;

/**
 * Lists containing all user defined classes in the project.
 */
private List<CtClass<?>> classesInProject = new ArrayList<CtClass<?>>();
	
/**
 * @return the numClasses
 */
public static double getCoupling() {
	return coupling;
}

/**
 * Calculates the coupling of the project.
 * @param ctClass  class in project
 */
public void process(final CtClass<?> ctClass) {
	numClasses++;
	
	classesInProject.add(ctClass);
}

/**
 * Calculates average coupling when all classes have been processed.
 */
public void processingDone() {

	HashSet<CtClass<?>> distinctClasses = new HashSet<CtClass<?>>();
	List<CtField<?>> fields;
	Set<CtMethod<?>> methods;
	
	for (CtClass<?> classInProject : classesInProject) {
		
		fields = classInProject.getFields();
		
		CtClass<?> fieldType;
		for (CtField<?> field : fields) {
			fieldType = (CtClass<?>) field.getType().getDeclaration();
			
			if (classesInProject.contains(fieldType)) {
				distinctClasses.add(fieldType);
			}
		}
		
		methods = classInProject.getMethods();
		
		CtClass<?> methodType;
		List<CtParameter<?>> parameterTypes;
		for (CtMethod<?> method : methods) {
			parameterTypes = method.getParameters();
			
			for (CtParameter<?> parameterType : parameterTypes) {
				methodType = (CtClass<?>) parameterType.getType().getDeclaration();
				
				if (classesInProject.contains(methodType)) {
					distinctClasses.add(methodType);
				}
			}	
		}
		
		coupling += distinctClasses.size();
		distinctClasses.clear();
	}
	
	coupling = coupling / (double) numClasses;
  }

}
