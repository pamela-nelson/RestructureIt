package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class ComplexityCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/complexity");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/complexity");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.ComplexityCalculator");
		launcher.processCode();
	}

	/**
	 * Tests complexity for a project is calculated correctly.
	 */
	@Test
	public void getComplexityTest() {
		Assert.assertEquals((double) 9/5, ComplexityCalculator.getComplexity(), 0);
	}


}
