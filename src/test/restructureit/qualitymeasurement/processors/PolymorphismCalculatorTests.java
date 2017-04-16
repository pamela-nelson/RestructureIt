package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class PolymorphismCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/polymorphism");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/polymorphism");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.PolymorphismCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests polymorphism for a project is calculated correctly.
	 */
	@Test
	public void getPolymorphismTest() {
		Assert.assertEquals((double) 3/7, PolymorphismCalculator.getPolymorphism(), 0);
	}

}
