package restructureit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import restructureit.qualitymeasurement.Metrics;
import restructureit.qualitymeasurement.QualityReport;
import restructureit.qualitymeasurement.processors.AbstractionCalculator;
import restructureit.qualitymeasurement.processors.CohesionCalculator;
import restructureit.qualitymeasurement.processors.ComplexityCalculator;
import restructureit.qualitymeasurement.processors.CompositionCalculator;
import restructureit.qualitymeasurement.processors.CouplingCalculator;
import restructureit.qualitymeasurement.processors.DesignSizeCalculator;
import restructureit.qualitymeasurement.processors.EncapsulationCalculator;
import restructureit.qualitymeasurement.processors.HierarchiesCalculator;
import restructureit.qualitymeasurement.processors.InheritanceCalculator;
import restructureit.qualitymeasurement.processors.LinesOfCodeCalculator;
import restructureit.qualitymeasurement.processors.MessagingCalculator;
import restructureit.qualitymeasurement.processors.PolymorphismCalculator;
import restructureit.qualitymeasurement.utils.QualityReportWriter;
import spoon.Launcher;
import spoon.compiler.SpoonResource;

/**
 * QualityMeasurementTask controls the quality measurement of code. It is responsible for building 
 * an AST for the given input sources and measuring each quality attribute.
 * It then creates a Metrics summary and Quality Report.
 */
public class QualityMeasurementTask extends Task {

	//CONSTRUCTOR
	
	/**
	 * Creates a new instance of Spoon Launcher.
	 */
	public QualityMeasurementTask() {
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
	 * Processes the given input source code.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void processCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//Add all quality measuring processors
		addProcessor("restructureit.qualitymeasurement.processors.AbstractionCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.CohesionCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.ComplexityCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.CompositionCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.CouplingCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.DesignSizeCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.EncapsulationCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.HierarchiesCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.InheritanceCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.LinesOfCodeCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.MessagingCalculator");
		addProcessor("restructureit.qualitymeasurement.processors.PolymorphismCalculator");
		
		super.processCode();
		
		populateMetrics();	
	}
	
	/**
	 * Populate quality metrics from processed code.
	 */
	private void populateMetrics() {
		
		projectMetrics = new Metrics();
		projectMetrics.setAbstraction(AbstractionCalculator.getAbstraction());
		projectMetrics.setCohesion(CohesionCalculator.getCohesion());
		projectMetrics.setComplexity(ComplexityCalculator.getComplexity());
		projectMetrics.setComposition(CompositionCalculator.getComposition());
		projectMetrics.setCoupling(CouplingCalculator.getCoupling());
		projectMetrics.setDesignSize(DesignSizeCalculator.getDesignSize());
		projectMetrics.setEncapsulation(EncapsulationCalculator.getEncapsulation());
		projectMetrics.setHierarchies(HierarchiesCalculator.getHierarchies());
		projectMetrics.setInheritance(InheritanceCalculator.getInheritance());
		projectMetrics.setLinesOfCode(LinesOfCodeCalculator.getLinesOfCode());
		projectMetrics.setMessaging(MessagingCalculator.getMessaging());
		projectMetrics.setPolymorphism(PolymorphismCalculator.getPolymorphism());
		
		projectMetrics.calculateQualityAttributes();
		
		resetQualityCalculatorValues();
		
		if (getExperimentName().equals("Original")) {
			if (!new File(String.format("results/OriginalQualityReports/%s.ser", getProjectName())).isFile()) {
		        FileOutputStream fileOut;
				try {
					new File("results/OriginalQualityReports").mkdir();
					fileOut = new FileOutputStream(String.format("results/OriginalQualityReports/%s.ser", getProjectName()));
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
			         out.writeObject(projectMetrics);
			         out.close();
			         fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		} else {
			generateQualityReport();
		}
	}
	
	/**
	 * Generate a quality report for the given project.
	 */
	private void generateQualityReport() {
		projectQualityReport = new QualityReport();
		
        Metrics beforeRefactoring = new Metrics();
		try {
			FileInputStream fileIn = new FileInputStream(String.format("results/OriginalQualityReports/%s.ser", getProjectName()));
	        ObjectInputStream in = new ObjectInputStream(fileIn);
			beforeRefactoring = (Metrics) in.readObject();
			in.close();
		    fileIn.close();
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
       
		projectQualityReport.setBeforeRefactoring(beforeRefactoring);
		projectQualityReport.setAfterRefactoring(projectMetrics);
		projectQualityReport.setProjectName(getProjectName());
		//num into processors, refactorings done.
		projectQualityReport.setRefactoringsApplied(getRefactoringsApplied());
		projectQualityReport.calculateQualityChanges();
		
		String fileName = "";
		for (String refactor : refactoringsApplied.keySet()) {
				switch (refactor) {
					case "CollapseHierarchy"     : fileName += "CH_";
												   break;
					case "EncapsulateCollection" : fileName += "EC_";
					   							   break;
					case "EncapsulateField"  	 : fileName += "EF_";
					   							   break;
					case "ExtractSuperClass"     : fileName += "ESC_";
												   break;
					case "HideMethod"            : fileName += "HM_";
					   							   break;
					case "PullUpField"			 : fileName += "PUF_";
					   							   break;
					case "PullUpMethod"          : fileName += "PUM_";
					   							   break;
					case "PushDownField"		 : fileName += "PDF_";
					   							   break;
					case "PushDownMethod"	     : fileName += "PDM_";
					   							   break;
				}
		}
		
		fileName = fileName.substring(0, fileName.length() - 1);
		
		QualityReportWriter.writeQualityReport(projectQualityReport, getExperimentName(), getProjectName(), fileName);
	}
	
	/**
	 * Resets all the values of the quality Calculators.
	 */
	private void resetQualityCalculatorValues() {
		AbstractionCalculator.resetAbstractionValue();
		CohesionCalculator.resetCohesionValue();
		ComplexityCalculator.resetComplexityValue();
		CompositionCalculator.resetCompositionValue();
		CouplingCalculator.resetCouplingValue();
		DesignSizeCalculator.resetDesignSizeValue();
		EncapsulationCalculator.resetEncapsulationValue();
		HierarchiesCalculator.resetHierarchiesValue();
		InheritanceCalculator.resetInheritanceValue();
		LinesOfCodeCalculator.resetLinesOfCodeValue();
		MessagingCalculator.resetMessagingValue();
		PolymorphismCalculator.resetPolymorphismValue();
	}
}
