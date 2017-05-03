package restructureit.refactorings.processors;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import restructureit.refactorings.processors.collapsehierarchy.B;
import restructureit.utils.RefactoringTask;
import spoon.testing.Assert;

public class CollapseHierarchyTests {

	String resultsPath = "src/test/resources/restructureit/refactorings/processors/collapsehierarchy/Results/";
	String expectedResultsPath = "src/test/resources/restructureit/refactorings/processors/collapsehierarchy/Expected/";
	String packageStructure = "restructureit/refactorings/processors/collapsehierarchy/";
	RefactoringTask launcher = new RefactoringTask();
	
	@Before
	public void setUp() throws Exception {
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/A.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/B.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/C.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/D.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/E.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/InterfaceA.java");
		launcher.addInputSource("src/test/resources/restructureit/refactorings/processors/collapsehierarchy/InterfaceB.java");
		launcher.setOutputSource(resultsPath);
		launcher.addProcessor("restructureit.refactorings.processors.CollapseHierarchy");
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
	public void CollapseHeirarchyTest() {
		
        Assert.assertThat(new File(resultsPath + packageStructure + "A.java")).isEqualTo(new File(expectedResultsPath + "A.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "C.java")).isEqualTo(new File(expectedResultsPath + "C.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "D.java")).isEqualTo(new File(expectedResultsPath + "D.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "E.java")).isEqualTo(new File(expectedResultsPath + "E.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "InterfaceA.java")).isEqualTo(new File(expectedResultsPath + "InterfaceA.java"));
		Assert.assertThat(new File(resultsPath + packageStructure + "InterfaceB.java")).isEqualTo(new File(expectedResultsPath + "InterfaceB.java"));
		
		junit.framework.Assert.assertFalse(new File(resultsPath + packageStructure + "B.java").isFile());

		int timesApplied = launcher.getRefactoringsApplied().get("CollapseHierarchy");
		junit.framework.Assert.assertEquals(1, timesApplied);		
		
		File resultsDirectory = new File(resultsPath);
		clearResultsFiles(resultsDirectory);
		
	}

}
