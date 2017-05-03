package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.refactorings.processors.EncapsulateField;
import restructureit.utils.RefactoringTask;
import spoon.Launcher;
import spoon.testing.Assert;

public class EncapsulateFieldTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/encapsulatefield/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/encapsulatefield/Expected/";
	String packageStructure = "restructureit/refactorings/processors/encapsulatefield/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/encapsulatefield/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/encapsulatefield/B.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateField");
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
	public void EncapsulateFieldTest() {
		
        Assert.assertThat(new File(resultsPath + packageStructure + "A.java")).isEqualTo(new File(expectedResultsPath + "A.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "B.java")).isEqualTo(new File(expectedResultsPath + "B.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("EncapsulateField");
		junit.framework.Assert.assertEquals(2, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
