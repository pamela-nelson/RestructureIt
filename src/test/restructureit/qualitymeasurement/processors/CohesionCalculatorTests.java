package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class CohesionCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/cohesion");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/cohesion/");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.CohesionCalculator");
		launcher.processCode();
	}

	/**
	 * Tests average cohesion of the project is calculated correctly.
	 */
	@Test
	public void getCohesionTest() {
		Assert.assertEquals(((double)4/6 + 1 + 0.5)/3, CohesionCalculator.getCohesion(), 0);
	}

}
