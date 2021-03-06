package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class CouplingCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/coupling");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/coupling/");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.CouplingCalculator");
		launcher.processCode();
	}

	/**
	 * Tests average coupling of the project is calculated correctly.
	 */
	@Test
	public void getCouplingTest() {
		Assert.assertEquals(1, CouplingCalculator.getCoupling(), 0);
	}

}
