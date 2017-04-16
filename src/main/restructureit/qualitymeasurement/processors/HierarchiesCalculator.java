package restructureit.qualitymeasurement.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

/** 
* Calculates the number of class hierarchies in the input project.
* 
*/
public class HierarchiesCalculator extends AbstractProcessor<CtClass<?>> {

		/**
		 * Number of classes in input project.
		 */
		private static int numHierarchies = 0;
			
		/**
		 * @return the numClasses
		 */
		public static int getHierarchies() {
			return numHierarchies;
		}

		/**
		 * Determines if this class is the root of a class hierarchy.
		 * @param element class to check if root class
		 * @return if the class is a root class of a hierarchy
		 */
		public boolean isToBeProcessed(final CtClass<?> element) {
			return element.getSuperclass() == null;
		}
		
		/**
		 * Calculates the number of hierarchies in the project.
		 * @param element root class in project
		 */
		public void process(final CtClass<?> element) {
			numHierarchies++;
		}
		
	
}
