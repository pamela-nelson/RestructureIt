package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class ComplexityCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/complexity");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/complexity");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.ComplexityCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests complexity for a project is calculated correctly.
	 */
	@Test
	public void getComplexityTest() {
		Assert.assertEquals((double) 9/5, ComplexityCalculator.getComplexity(), 0);
	}


}
