package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class CompositionCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/composition");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/composition/");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.CompositionCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests average composition of the project is calculated correctly.
	 */
	@Test
	public void getCohesionTest() {
		Assert.assertEquals((double)7/4, CompositionCalculator.getComposition(), 0);
	}
}
