package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.refactorings.processors.EncapsulateField;
import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class ExtractSuperClassTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/extractsuperclass/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/extractsuperclass/Expected/";
	String packageStructure = "restructureit/refactorings/processors/extractsuperclass/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/extractsuperclass/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/extractsuperclass/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/extractsuperclass/C.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.ExtractSuperClass");
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
	public void ExtractSuperClassTest() {
		
        Assert.assertThat(new File(resultsPath + packageStructure + "A.java")).isEqualTo(new File(expectedResultsPath + "A.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "B.java")).isEqualTo(new File(expectedResultsPath + "B.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "C.java")).isEqualTo(new File(expectedResultsPath + "C.java"));
		junit.framework.Assert.assertTrue(new File(resultsPath + packageStructure + "ABSuper.java").isFile());
		
		int timesApplied = launcher.getRefactoringsApplied().get("ExtractSuperClass");
		junit.framework.Assert.assertEquals(1, timesApplied);
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
