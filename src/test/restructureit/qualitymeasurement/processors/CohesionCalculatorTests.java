package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class CohesionCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/cohesion");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/cohesion/");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.CohesionCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests average cohesion of the project is calculated correctly.
	 */
	@Test
	public void getCohesionTest() {
		Assert.assertEquals(((double)4/6 + 1 + 0.5)/3, CohesionCalculator.getCohesion(), 0);
	}

}
