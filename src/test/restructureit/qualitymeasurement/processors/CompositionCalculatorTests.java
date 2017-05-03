package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class CompositionCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/composition");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/composition/");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.CompositionCalculator");
		launcher.processCode();
	}

	/**
	 * Tests average composition of the project is calculated correctly.
	 */
	@Test
	public void getCohesionTest() {
		Assert.assertEquals((double)7/4, CompositionCalculator.getComposition(), 0);
	}
}
