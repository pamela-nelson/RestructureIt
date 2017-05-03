package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;

public class MessagingCalculatorTests {

	public RefactoringTask launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new RefactoringTask();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/messaging");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/messaging");
		launcher.addProcessor("restructureit.qualitymeasurement.processors.MessagingCalculator");
		launcher.processCode();
	}

	/**
	 * Tests messaging for a project is calculated correctly.
	 */
	@Test
	public void getMessagingTest() {
		Assert.assertEquals((double) 6/5, MessagingCalculator.getMessaging(), 0);
	}

}
