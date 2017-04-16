package restructureit.qualitymeasurement.processors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;

public class MessagingCalculatorTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() throws Exception {
		launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/qualitymeasurement/processors/messaging");
		launcher.setOutputSource("src/test/resources/restructureit/qualitymeasurement/processors/messaging");
		launcher.addRefactoring("restructureit.qualitymeasurement.processors.MessagingCalculator");
		launcher.processRefactoring();
	}

	/**
	 * Tests messaging for a project is calculated correctly.
	 */
	@Test
	public void getMessagingTest() {
		Assert.assertEquals((double) 6/5, MessagingCalculator.getMessaging(), 0);
	}

}
