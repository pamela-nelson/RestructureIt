/**
 * 
 */
package restructureit.ui.utils;



import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restructureit.utils.SpoonLauncher;
import spoon.Launcher;

/**
 * Tests the functionality of the Spoon Launcher Class.
 * @author Pamela Nelson
 */
public class SpoonLauncherTests {

	public SpoonLauncher launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() {
		launcher = new SpoonLauncher();
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#SpoonLauncher()}.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Test
	public void testSpoonLauncher() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field launcherInstance = SpoonLauncher.class.getDeclaredField("launcher");
		Field isProcessed = SpoonLauncher.class.getDeclaredField("isProcessed");

		launcherInstance.setAccessible(true);
		isProcessed.setAccessible(true);

		Launcher launcherValue = (Launcher) launcherInstance.get(launcher);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertNotNull("launcher should be initialised", launcherValue);
		Assert.assertFalse("isProcessed should be false", isProcessedValue);
		Assert.assertTrue(launcher.getInputSources() != null && launcher.getInputSources().size() == 0);
		Assert.assertTrue(launcher.getRefactorings() != null && launcher.getRefactorings().size() == 0);
		Assert.assertTrue(launcher.getOutputSource().equals(""));
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getLauncher()}.
	 */
	@Test
	public void testGetLauncher() {
		Assert.assertNotNull(launcher.getLauncher());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setLauncher(spoon.Launcher)}.
	 */
	@Test
	public void testSetLauncher() {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setLauncher(new Launcher());
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getInputSources()}.
	 */
	@Test
	public void testGetInputSources() {
		Assert.assertNotNull(launcher.getInputSources());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setInputSources(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetInputSourcesNoReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		List<String> newInputs = Arrays.asList("resources/", "src/", "test/");
		launcher.setInputSources(newInputs);
		
		Assert.assertEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newInputs == launcher.getInputSources());
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setInputSources(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetInputSourcesReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setInputSources(Arrays.asList("resources/"));
		List<String> newInputs = Arrays.asList("resources/", "src/", "test/");
		launcher.setInputSources(newInputs);
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newInputs == launcher.getInputSources());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getRefactorings()}.
	 */
	@Test
	public void testGetRefactorings() {
		Assert.assertNotNull(launcher.getOutputSource());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setRefactorings(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetRefactoringsNoReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		List<String> newRefactorings = Arrays.asList("ExtractMethod", "ExtractClass");
		launcher.setRefactorings(newRefactorings);
		
		Assert.assertEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newRefactorings == launcher.getRefactorings());
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setRefactorings(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetRefactoringsReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setRefactorings(Arrays.asList("ExtractMethod", "ExtractClass"));
		List<String> newRefactorings = Arrays.asList("ExtractMethod", "ExtractClass");
		launcher.setRefactorings(newRefactorings);
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newRefactorings == launcher.getRefactorings());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getOutputSource()}.
	 */
	@Test
	public void testGetOutputSource() {
		Assert.assertEquals("", launcher.getOutputSource());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setOutputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetOutputSourceNoReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		String newPath = "results/";
		launcher.setOutputSource(newPath);
		
		Assert.assertEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newPath == launcher.getOutputSource());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#setOutputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetOutputSourceReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setOutputSource("results/");
		String newPath = "results/project/";
		launcher.setOutputSource(newPath);
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newPath == launcher.getOutputSource());
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getProgramModel()}.
	 */
	@Test
	public void testGetProgramModelNoInputs() {
		Assert.assertNull(launcher.getProgramModel());
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#getProgramModel()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testGetProgramModelInputPresent() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit.ui.utils/");
		launcher.setOutputSource("src/");
		Assert.assertNotNull(launcher.getProgramModel());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#addInputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddInputSourceNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/");
		Assert.assertTrue(launcher.getInputSources().size() == 1);
		Assert.assertEquals("src/", launcher.getInputSources().get(0));
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#addInputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddInputSourceAlreadyInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/");
		launcher.addInputSource("src/");
		Assert.assertTrue(launcher.getInputSources().size() == 1);
		Assert.assertEquals("src/", launcher.getInputSources().get(0));
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeInputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveInputSourceInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/");
		Assert.assertTrue(launcher.getInputSources().size() == 1);
		launcher.removeInputSource("src/");
		Assert.assertTrue(launcher.getInputSources().size() == 0);
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeInputSource(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveInputSourceNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.removeInputSource("src/");
		Assert.assertTrue(launcher.getInputSources().size() == 0);
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#addRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddRefactoringNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addRefactoring("ExtractMethod");
		Assert.assertTrue(launcher.getRefactorings().size() == 1);
		Assert.assertEquals("ExtractMethod", launcher.getRefactorings().get(0));
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#addRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddRefactoringAlreadyInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addRefactoring("ExtractMethod");
		launcher.addRefactoring("ExtractMethod");
		Assert.assertTrue(launcher.getRefactorings().size() == 1);
		Assert.assertEquals("ExtractMethod", launcher.getRefactorings().get(0));
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveRefactoringInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addRefactoring("ExtractMethod");
		Assert.assertTrue(launcher.getRefactorings().size() == 1);
		
		launcher.removeRefactoring("ExtractMethod");
		Assert.assertTrue(launcher.getRefactorings().size() == 0);
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveRefactoringNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.removeRefactoring("ExtractMethod");
		
		Assert.assertTrue(launcher.getRefactorings().size() == 0);
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeOutputSource()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveOutputSourceSet() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setOutputSource("results/");
		launcher.removeOutputSource();
		
		Assert.assertEquals("", launcher.getOutputSource());
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());	
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#removeOutputSource()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveOutputSourceNotSet() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.removeOutputSource();
		
		Assert.assertEquals("", launcher.getOutputSource());
		Assert.assertEquals(originalLauncher, launcher.getLauncher());
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#processRefactoring()}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testProcessRefactoring() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit/ui/utils/SpoonLauncherTests.java");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		//TODO add refactoring
		launcher.processRefactoring();
		
		Field isProcessed = SpoonLauncher.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertTrue(isProcessedValue);
		Assert.fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#processRefactoring()}.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testProcessRefactoringNoRefactorings() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit/ui/utils/SpoonLauncherTests.java");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		launcher.processRefactoring();
		
		Field isProcessed = SpoonLauncher.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertFalse(isProcessedValue);
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#processRefactoring()}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testProcessRefactoringNoInputs() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		launcher.addRefactoring("ExtractMethod");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		launcher.processRefactoring();
		
		Field isProcessed = SpoonLauncher.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertFalse(isProcessedValue);
	}

	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#outputProcessedCode()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testOutputProcessedCodeNotProcessed() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit/ui/utils/SpoonLauncherTests.java");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		launcher.outputProcessedCode();
		Assert.assertFalse(new File("src/test/resources/restructureit/test/utils/SpoonLauncherTests/SpoonLauncherTests.java").isFile());
	}
	
	/**
	 * Test method for {@link restructureit.utils.SpoonLauncher#outputProcessedCode()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testOutputProcessedCodeProcessed() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit/ui/utils/SpoonLauncherTests.java");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		//TODO add refactoring 
		launcher.processRefactoring();
		launcher.outputProcessedCode();
		Assert.assertTrue(new File("src/test/resources/restructureit/test/utils/SpoonLauncherTests/SpoonLauncherTests.java").isFile());
		Assert.fail("Not yet implemented");
	}

}
