package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.refactorings.processors.EncapsulateField;
import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class EncapsulateCollectionTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/encapsulatecollection/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/encapsulatecollection/Expected/";
	String packageStructure = "restructureit/refactorings/processors/encapsulatecollection/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/encapsulatecollection/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/encapsulatecollection/B.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.EncapsulateCollection");
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
	public void EncapsulateCollectionTest() {
		
        Assert.assertThat(new File(resultsPath + packageStructure + "A.java")).isEqualTo(new File(expectedResultsPath + "A.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "B.java")).isEqualTo(new File(expectedResultsPath + "B.java"));
		
		int timesApplied = launcher.getRefactoringsApplied().get("EncapsulateCollection");
		junit.framework.Assert.assertEquals(1, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
