package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class HideMethodTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/hidemethod/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/hidemethod/Expected/";
	String packageStructure = "restructureit/refactorings/processors/hidemethod/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/hidemethod/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/hidemethod/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/hidemethod/C.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.HideMethod");
		launcher.processCode();
	}
	
	public void clearResultsFiles(File directory) {
		
		for (File file: directory.listFiles()) {
	        if (file.isDirectory()) {
	        		clearResultsFiles(file);
	        }
	        file.delete();
	    }
	}

	@Test
	public void pullUpMethodTest() {
		
		Assert.assertThat(new File(resultsPath + packageStructure + "A.java")).isEqualTo(new File(expectedResultsPath + "A.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "B.java")).isEqualTo(new File(expectedResultsPath + "B.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "C.java")).isEqualTo(new File(expectedResultsPath + "C.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("HideMethod");
		junit.framework.Assert.assertEquals(3, timesApplied);

		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
	}
}
