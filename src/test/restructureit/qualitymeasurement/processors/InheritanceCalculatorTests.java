package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class InheritanceCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/inheritance");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/inheritance");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.InheritanceCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests average inheritance for a project is calculated correctly.
	 */
	@Test
	public void getInheritanceTest() {
		Assert.assertEquals(0.36, InheritanceCalculator.getInheritance(), 0);
	}

}
