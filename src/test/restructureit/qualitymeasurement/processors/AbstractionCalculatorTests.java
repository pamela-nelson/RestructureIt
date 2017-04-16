package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class AbstractionCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/abstraction");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/abstraction/");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.DesignSizeCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests average number of ancestors (abstraction) is calculated correctly.
	 */
	@Test
	public void getAbstractionTest() {
		Assert.assertEquals(4/6, AbstractionCalculator.getAbstraction(), 0);
	}

}
