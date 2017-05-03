package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class AbstractionCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/abstraction");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/abstraction/");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.DesignSizeCalculator");
		launcher.processCode();
	}

	/**
	 * Tests average number of ancestors (abstraction) is calculated correctly.
	 */
	@Test
	public void getAbstractionTest() {
		Assert.assertEquals(4/6, AbstractionCalculator.getAbstraction(), 0);
	}

}
