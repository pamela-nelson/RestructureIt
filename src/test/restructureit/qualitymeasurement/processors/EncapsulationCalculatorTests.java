package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class EncapsulationCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/encapsulation");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/encapsulation");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.HierarchiesCalculator");
		launcher.processCode();
	}

	/**
	 * Tests encapsulation is calculated correctly.
	 */
	@Test
	public void getEncapsulationTest() {
		Assert.assertEquals((1 + 0 + 2/3 + 0)/4, EncapsulationCalculator.getEncapsulation(), 0);
	}

}
