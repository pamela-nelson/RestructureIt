package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class PullUpMethodTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/pullupmethod/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/pullupmethod/Expected/";
	String packageStructure = "restructureit/refactorings/processors/pullupmethod/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupmethod/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupmethod/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupmethod/C.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupmethod/D.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.PullUpMethod");
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
		Assert.assertThat(new File(resultsPath + packageStructure + "D.java")).isEqualTo(new File(expectedResultsPath + "D.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("PullUpMethod");
		junit.framework.Assert.assertEquals(1, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
