package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class EncapsulationCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/encapsulation");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/encapsulation");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.HierarchiesCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests encapsulation is calculated correctly.
	 */
	@Test
	public void getEncapsulationTest() {
		Assert.assertEquals((1 + 0 + 2/3 + 0)/4, EncapsulationCalculator.getEncapsulation(), 0);
	}

}
