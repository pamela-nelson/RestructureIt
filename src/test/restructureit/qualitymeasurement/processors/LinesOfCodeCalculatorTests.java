package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class LinesOfCodeCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/loc");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/loc");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.LinesOfCodeCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests the number of lines of code for a project is calculated correctly.
	 */
	@Test
	public void getLinesOfCodeTest() {
		Assert.assertEquals(7, LinesOfCodeCalculator.getLinesOfCode(), 0);
	}

}
