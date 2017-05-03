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

import restructureit.utils.RefactoringTask;
import restructureit.utils.Task;
import spoon.Launcher;

/**
 * Tests the functionality of the Spoon Launcher Class.
 * @author Pamela Nelson
 */
public class RefactoringTaskTests {

	public Task launcher;
	
	/**
	 * Sets up the Spoon Launcher for each test.
	 */
	@Before
	public void setUp() {
		launcher = new RefactoringTask();
	}

	/**
	 * Test method for {@link restructureit.utils.Task#SpoonLauncher()}.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Test
	public void testSpoonLauncher() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field launcherInstance = Task.class.getDeclaredField("launcher");
		Field isProcessed = Task.class.getDeclaredField("isProcessed");

		launcherInstance.setAccessible(true);
		isProcessed.setAccessible(true);

		Launcher launcherValue = (Launcher) launcherInstance.get(launcher);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertNotNull("launcher should be initialised", launcherValue);
		Assert.assertFalse("isProcessed should be false", isProcessedValue);
		Assert.assertTrue(launcher.getInputSources() != null && launcher.getInputSources().size() == 0);
		Assert.assertTrue(launcher.getProcessors() != null && launcher.getProcessors().size() == 0);
		Assert.assertTrue(launcher.getOutputSource().equals(""));
	}

	/**
	 * Test method for {@link restructureit.utils.Task#getLauncher()}.
	 */
	@Test
	public void testGetLauncher() {
		Assert.assertNotNull(launcher.getLauncher());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#setLauncher(spoon.Launcher)}.
	 */
	@Test
	public void testSetLauncher() {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setLauncher(new Launcher());
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#getInputSources()}.
	 */
	@Test
	public void testGetInputSources() {
		Assert.assertNotNull(launcher.getInputSources());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#setInputSources(java.util.ArrayList)}.
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
	 * Test method for {@link restructureit.utils.Task#setInputSources(java.util.ArrayList)}.
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
	 * Test method for {@link restructureit.utils.Task#getProcessors()}.
	 */
	@Test
	public void testGetRefactorings() {
		Assert.assertNotNull(launcher.getOutputSource());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#setProcessors(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetRefactoringsNoReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		List<String> newRefactorings = Arrays.asList("ExtractMethod", "ExtractClass");
		launcher.setProcessors(newRefactorings);
		
		Assert.assertEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newRefactorings == launcher.getProcessors());
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#setProcessors(java.util.ArrayList)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testSetRefactoringsReset() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Launcher originalLauncher = launcher.getLauncher();
		launcher.setProcessors(Arrays.asList("ExtractMethod", "ExtractClass"));
		List<String> newRefactorings = Arrays.asList("ExtractMethod", "ExtractClass");
		launcher.setProcessors(newRefactorings);
		
		Assert.assertNotEquals(originalLauncher, launcher.getLauncher());
		Assert.assertTrue(newRefactorings == launcher.getProcessors());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#getOutputSource()}.
	 */
	@Test
	public void testGetOutputSource() {
		Assert.assertEquals("", launcher.getOutputSource());
	}

	/**
	 * Test method for {@link restructureit.utils.Task#setOutputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#setOutputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#getProgramModel()}.
	 */
	@Test
	public void testGetProgramModelNoInputs() {
		Assert.assertNull(launcher.getProgramModel());
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#getProgramModel()}.
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
	 * Test method for {@link restructureit.utils.Task#addInputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#addInputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#removeInputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#removeInputSource(java.lang.String)}.
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
	 * Test method for {@link restructureit.utils.Task#addProcessor(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddRefactoringNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		Assert.assertTrue(launcher.getProcessors().size() == 1);
		Assert.assertEquals("restructureit.refactorings.processors.EncapsulateField", launcher.getProcessors().get(0));
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#addProcessor(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testAddRefactoringAlreadyInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		Assert.assertTrue(launcher.getProcessors().size() == 1);
		Assert.assertEquals("restructureit.refactorings.processors.EncapsulateField", launcher.getProcessors().get(0));
	}

	/**
	 * Test method for {@link restructureit.utils.Task#removeRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveRefactoringInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		Assert.assertTrue(launcher.getProcessors().size() == 1);
		
		launcher.removeRefactoring("restructureit.refactorings.processors.EncapsulateField");
		Assert.assertTrue(launcher.getProcessors().size() == 0);
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#removeRefactoring(java.lang.String)}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testRemoveRefactoringNotInList() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.removeRefactoring("ExtractMethod");
		
		Assert.assertTrue(launcher.getProcessors().size() == 0);
	}

	/**
	 * Test method for {@link restructureit.utils.Task#removeOutputSource()}.
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
	 * Test method for {@link restructureit.utils.Task#removeOutputSource()}.
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
	 * Test method for {@link restructureit.utils.Task#processCode()}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testProcessRefactoring() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/encapsulatefield/A.java");
		launcher.setOutputSource("src/test/resources/restructureit/refactorings/processors/encapsulatefield/Results/");
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		launcher.processCode();
		
		Field isProcessed = Task.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertTrue(isProcessedValue);
		Assert.assertTrue(new File("src/test/resources/restructureit/refactorings/processors/encapsulatefield/Results/restructureit/refactorings/processors/encapsulatefield/A.java").isFile());
		
		new File("src/test/resources/restructureit/refactorings/processors/encapsulatefield/Results/restructureit/refactorings/processors/encapsulatefield/A.java").delete();
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#processCode()}.
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
		launcher.processCode();
		
		Field isProcessed = Task.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertFalse(isProcessedValue);
	}
	
	/**
	 * Test method for {@link restructureit.utils.Task#processCode()}.
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testProcessRefactoringNoInputs() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		launcher.processCode();
		
		Field isProcessed = Task.class.getDeclaredField("isProcessed");
		isProcessed.setAccessible(true);
		boolean isProcessedValue = (boolean) isProcessed.get(launcher);
		
		Assert.assertFalse(isProcessedValue);
	}

	/**
	 * Test method for {@link restructureit.utils.Task#outputProcessedCode()}.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testOutputProcessedCodeNotProcessed() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher.addInputSource("src/test/restructureit/ui/utils/SpoonLauncherTests.java");
		launcher.setOutputSource("src/test/resources/restructureit/test/utils/SpoonLauncherTests/");
		Assert.assertFalse(new File("src/test/resources/restructureit/test/utils/SpoonLauncherTests/SpoonLauncherTests.java").isFile());
	}

}
