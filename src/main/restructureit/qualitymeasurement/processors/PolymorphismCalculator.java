package restructureit.qualitymeasurement.processors;

import java.util.List;
import java.util.Set;

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
	 * Calculates the complexity of the project.
	 * @param ctClass  class in project
	 */
	public void process(final CtClass<?> ctClass) {	
		Set<CtMethod<?>> classMethods;
		Set<CtMethod<?>> childClassMethods;
		List<CtClass<?>> childClasses;
		
		numClasses++;
		classMethods = ctClass.getMethods();
		childClasses = getAllSubClasses(ctClass);
		
		for (CtMethod<?> method : classMethods) {
			boolean isPolymorphic = false;
			String methodName = method.getSimpleName();
			List<CtParameter<?>> methodParameters = method.getParameters();
			
			for (CtClass<?> childClass : childClasses) {
				childClassMethods = childClass.getMethods();
				
				for (CtMethod<?> childMethod : childClassMethods) {
					
					if (childMethod.getSimpleName().equals(methodName) 
							&& childMethod.getParameters().equals(methodParameters)) {
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
	
	/**
	 * Retrieves all the direct and indirect children of a given class.
	 * @param ctClass class to get children of
	 * @return children of ctClass
	 */
	private List<CtClass<?>> getAllSubClasses(final CtClass<?> ctClass) {
		return Query.getElements(ctClass.getFactory(), new AbstractFilter<CtClass<?>>(CtClass.class) {
			@Override
			public boolean matches(final CtClass<?> element) {
				CtClass<?> candidateSuperClass;
				CtClass<?> tempElement = element;
				
				if (element.getSuperclass() != null) {
					
					while (tempElement.getSuperclass() != null) {
						candidateSuperClass = (CtClass<?>) tempElement.getSuperclass().getDeclaration();
						
						if (ctClass.equals(candidateSuperClass)) {
							return true;
						}
						
						if (tempElement.getSuperclass() != null) {
							tempElement = (CtClass<?>) tempElement.getSuperclass().getDeclaration();
						}
					}		
				}
				
				return false;
			}
		});
	}
}
