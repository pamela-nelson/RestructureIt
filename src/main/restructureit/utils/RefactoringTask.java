package restructureit.utils;

import java.util.ArrayList;
import java.util.HashMap;

import restructureit.qualitymeasurement.Metrics;
import restructureit.qualitymeasurement.QualityReport;
import restructureit.refactorings.processors.CollapseHierarchy;
import restructureit.refactorings.processors.EncapsulateCollection;
import restructureit.refactorings.processors.EncapsulateField;
import restructureit.refactorings.processors.ExtractSuperClass;
import restructureit.refactorings.processors.HideMethod;
import restructureit.refactorings.processors.PullUpField;
import restructureit.refactorings.processors.PullUpMethod;
import restructureit.refactorings.processors.PushDownField;
import restructureit.refactorings.processors.PushDownMethod;
import spoon.Launcher;
import spoon.compiler.SpoonResource;

/**
 * RefactoringTask controls the refactoring of code. It is responsible for building 
 * an AST for the given input sources and applying the given refactorings.
 * It then outputs the refactored code into the specified output folder.
 */
public class RefactoringTask extends Task {

	//CONSTRUCTOR
	
	/**
	 * Creates a new instance of Spoon Launcher.
	 */
	public RefactoringTask() {
		launcher = new Launcher();
		inputSources = new ArrayList<String>();
		processors = new ArrayList<String>();
		templates = new ArrayList<SpoonResource>();
		refactoringsApplied = new HashMap<String, Integer>();
		outputSource = "";
		isProcessed = false;
		isModelBuilt = false;
	}
	
	/**
	 * @return the projectMetrics
	 */
	public Metrics getProjectMetrics() {
		return null;
	}

	/**
	 * @return the projectQualityReport
	 */
	public QualityReport getProjectQualityReport() {
		return null;
	}
	
	/**
	 * Refactors the given input source code.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void processCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		super.processCode();	
		
		for (String processor : getProcessors()) {
			String[] refactorName = processor.split("\\.");
			String refactor = refactorName[refactorName.length - 1];
			
			switch (refactor) {
				case "CollapseHierarchy"     : refactoringsApplied.put(refactor, CollapseHierarchy.getTimesApplied());
											   CollapseHierarchy.resetTimesApplied();
										       break;
				case "EncapsulateCollection"  : refactoringsApplied.put(refactor, EncapsulateCollection.getTimesApplied());
											   EncapsulateCollection.resetTimesApplied();
				   							   break;
				case "EncapsulateField"  	 : refactoringsApplied.put(refactor, EncapsulateField.getTimesApplied());
											   EncapsulateField.resetTimesApplied();
										   	   break;
				case "ExtractSuperClass"     : refactoringsApplied.put(refactor, ExtractSuperClass.getTimesApplied());
										       ExtractSuperClass.resetTimesApplied();
											   break;
				case "HideMethod"            : refactoringsApplied.put(refactor, HideMethod.getTimesApplied());
											   HideMethod.resetTimesApplied();
											   break;
				case "PullUpField"			 : refactoringsApplied.put(refactor, PullUpField.getTimesApplied());
											   PullUpField.resetTimesApplied();
											   break;
				case "PullUpMethod"          : refactoringsApplied.put(refactor, PullUpMethod.getTimesApplied());
											   PullUpMethod.resetTimesApplied();
											   break;
				case "PushDownField"		 : refactoringsApplied.put(refactor, PushDownField.getTimesApplied());
											   PushDownField.resetTimesApplied();
											   break;
				case "PushDownMethod"	     : refactoringsApplied.put(refactor, PushDownMethod.getTimesApplied());
											   PushDownMethod.resetTimesApplied();
											   break;
			}	
		}
		
		outputProcessedCode();
	}
	
	/**
	 * Outputs the refactored code to the provided outputsource.
	 */
	private void outputProcessedCode() {
		if (isProcessed()) {
			getLauncher().prettyprint();
		}
	}
}
