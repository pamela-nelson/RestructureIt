package restructureit.qualitymeasurement.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;

/**
 * Calculates the number of lines of code in a given project.
 */
public class LinesOfCodeCalculator extends AbstractProcessor<CtStatement> {

	/**
	 * Number of lines of code in input project.
	 */
	private static int loc = 0;
		
	/**
	 * @return the linesOfCode
	 */
	public static int getLinesOfCode() {
		return loc;
	}
	
	/**
	 * Resets lines of code value to zero.
	 */
	public static void resetLinesOfCodeValue() {
		loc = 0;
	}
	
	/**
	 * @param statement line of code
	 */
	public void process(final CtStatement statement) {
		loc++;
	}
	
}
