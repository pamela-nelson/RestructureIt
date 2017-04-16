package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class DesignSizeCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/designsize");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/designsize/results/");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.DesignSizeCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests number of classes are calculated correctly even ones within packages.
	 */
	@Test
	public void getDesignSizeTest() {
		Assert.assertEquals(4, DesignSizeCalculator.getDesignSize(), 0);
	}

}
