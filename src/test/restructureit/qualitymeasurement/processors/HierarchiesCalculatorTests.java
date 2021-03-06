package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class HierarchiesCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/hierarchies");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/hierarchies");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.HierarchiesCalculator");
		launcher.processCode();
	}

	/**
	 * Tests number of hierarchies are calculated correctly.
	 */
	@Test
	public void getHierarchiesTest() {
		Assert.assertEquals(3, HierarchiesCalculator.getHierarchies(), 0);
	}

}
