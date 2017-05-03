package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class PullUpFieldTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/pullupfield/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/pullupfield/Expected/";
	String packageStructure = "restructureit/refactorings/processors/pullupfield/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupfield/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupfield/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupfield/C.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupfield/D.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pullupfield/E.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.PullUpField");
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
		Assert.assertThat(new File(resultsPath + packageStructure + "E.java")).isEqualTo(new File(expectedResultsPath + "E.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("PullUpField");
		junit.framework.Assert.assertEquals(2, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}


}
