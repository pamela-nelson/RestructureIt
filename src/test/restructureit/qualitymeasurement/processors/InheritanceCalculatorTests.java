package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class InheritanceCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/inheritance");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/inheritance");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.InheritanceCalculator");
		launcher.processCode();
	}

	/**
	 * Tests average inheritance for a project is calculated correctly.
	 */
	@Test
	public void getInheritanceTest() {
		Assert.assertEquals(0.36, InheritanceCalculator.getInheritance(), 0);
	}

}
