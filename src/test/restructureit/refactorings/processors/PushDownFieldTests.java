package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class PushDownFieldTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/pushdownfield/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/pushdownfield/Expected/";
	String packageStructure = "restructureit/refactorings/processors/pushdownfield/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/C.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/D.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/E.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/pushdownfield/F.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.PushDownField");
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
		Assert.assertThat(new File(resultsPath + packageStructure + "F.java")).isEqualTo(new File(expectedResultsPath + "F.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("PushDownField");
		junit.framework.Assert.assertEquals(1, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
