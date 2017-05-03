package restructureit.qualitymeasurement.processors;

import java.util.List;
import java.util.Set;

import restructureit.refactorings.utils.RefactoringHelperUtils;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.AbstractFilter;

/**
 * Calculates the average number of methods that can exhibit polymorphic behaviour if it is overridden
 * by one or more descendant classes.
 * Also known as Number of Polymorphic Methods
 */
public class PolymorphismCalculator extends AbstractProcessor<CtClass<?>> {
	
	/**
	 * Average number of polymorphic methods in input project.
	 */
	private static double polymorphism = 0;
	
	/**
	 * Number of classes in input project.
	 */
	private int numClasses = 0; 
	
	/**
	 * Total number of polymorphic methods in project.
	 */
	private int totalNumPolymorphicMethods = 0;
	
	/**
	 * @return the complexity
	 */
	public static double getPolymorphism() {
		return polymorphism;
	}
	
	/**
	 * Resets polymorphism value to zero.
	 */
	public static void resetPolymorphismValue() {
		polymorphism = 0;
	}

	/**
	 * Calculates the complexity of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {	
		Set<CtMethod<?>> classMethods;
		Set<CtMethod<?>> childClassMethods;
		List<CtClass<?>> childClasses;
		
		numClasses++;
		classMethods = ctClass.getMethods();
		childClasses = RefactoringHelperUtils.getAllSubClasses(ctClass);
		
		for (CtMethod<?> method : classMethods) {
			boolean isPolymorphic = false;
			String methodName = method.getSimpleName();
			List<CtParameter<?>> methodParameters = method.getParameters();
			
			for (CtClass<?> childClass : childClasses) {
				childClassMethods = childClass.getMethods();
				
				for (CtMethod<?> childMethod : childClassMethods) {
					
					if (childMethod.getSignature().equals(method.getSignature())) {
						isPolymorphic = true;
						totalNumPolymorphicMethods++;
						break;
					}
				}
				
				if (isPolymorphic) {
					break;
				}
			}
		}
	}
	
	/**
	 * Calculates average number of polymorphic methods when all classes have been processed.
	 */
	public void processingDone() {
		polymorphism = (double) totalNumPolymorphicMethods / (double) numClasses;
	}
}
