package restructureit.qualitymeasurement.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import restructureit.qualitymeasurement.QualityReport;

/**
 * Creates a text file in the specified file path containing the information from a given quality report.
 * 
 * @author Pamela
 *
 */
public class QualityReportWriter {
	
	/**
	 * Default filepath to create the quality report file.
	 */
	private String defaultFilePath = ".//results//";
	
	//PUBLIC METHODS
	
	/**
	 * Creates a quality Report Text file at the specified location with the specified file name.
	 * @param qualityReport quality report file to write to text file
	 * @param filePath file path to create quality report
	 * @param fileName name to call quality report text file
	 */
	public void writeQualityReport(final QualityReport qualityReport, final String filePath, final String fileName) {
		if (qualityReport.getBeforeRefactoring() != null && qualityReport.getAfterRefactoring() != null) {
			try {
				 	File report = new File(String.format("%s%s//%s.txt", defaultFilePath, filePath, fileName));
		            if (report.getParentFile() != null) {
		                report.getParentFile().mkdirs();
		            }
		            
		            FileWriter writer = new FileWriter(report);
		            BufferedWriter bufferedWriter = new BufferedWriter(writer);
	
		            //Header
		            bufferedWriter.write(String.format("======== %s QUALITY REPORT ========", qualityReport.getProjectName().toUpperCase()));
		            bufferedWriter.newLine();
		            
		            //Project Details
		            writeProjectDetails(bufferedWriter, qualityReport);
		            
		            //Refactorings applied details
		            writeRefactoringsAppliedDetails(bufferedWriter, qualityReport);
		          
		            //Quality Before
		            writeQualityBeforeRefactoringDetails(bufferedWriter, qualityReport);
		            
		            //Quality After
		            writeQualityAfterRefactoringDetails(bufferedWriter, qualityReport);
		            
		            //Quality Changes
		            writeQualityChangesDetails(bufferedWriter, qualityReport);
		            
		            bufferedWriter.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
	}
	
	//PRIVATE METHODS
	
	/**
	 * Writes details about the project to the quality report file.
	 * @param bufferedWriter writer
	 * @param qualityReport quality report to be written to file
	 */
	private void writeProjectDetails(final BufferedWriter bufferedWriter, final QualityReport qualityReport) {
		try {
				 bufferedWriter.write("======== Project Details ========");
		         bufferedWriter.newLine();
		         bufferedWriter.write(String.format("Project Name: %s", qualityReport.getProjectName()));
		         bufferedWriter.newLine();
		         bufferedWriter.write(String.format("Project Location: %s", qualityReport.getProjectLocation()));
		         bufferedWriter.newLine();
		         bufferedWriter.write(String.format("Project Size: %s", qualityReport.getBeforeRefactoring().getLinesOfCode()));
		         bufferedWriter.newLine();
		         bufferedWriter.newLine();
		 } catch (IOException e) {
	            e.printStackTrace();
	     } 
	}
	
	/** 
	 * Writes details about the refactorings applied to the project to the quality report file.
	 * @param bufferedWriter writer
	 * @param qualityReport quality report to be written to file
	 */
	private void writeRefactoringsAppliedDetails(final BufferedWriter bufferedWriter, final QualityReport qualityReport) {
		try {
				bufferedWriter.write("======== Refactoring Details ========");
	            bufferedWriter.newLine();
	            Map<String, Integer> refactorings = qualityReport.getRefactoringsApplied();
	            for (String refactorType : refactorings.keySet()) {
	            	bufferedWriter.write(String.format("Refactoring Type: %s \t Applied: %d", refactorType, refactorings.get(refactorType)));
		            bufferedWriter.newLine();
	            }
	            bufferedWriter.newLine();
			 } catch (IOException e) {
		            e.printStackTrace();
		     } 
	}
	
	/**
	 * Writes details about the projects quality before refactoring to the quality report file.
	 * @param bufferedWriter writer
	 * @param qualityReport quality report to be written to file
	 */
	private void writeQualityBeforeRefactoringDetails(final BufferedWriter bufferedWriter, final QualityReport qualityReport) {
		try {
			bufferedWriter.write("======== Quality Before Refactoring ========");
            bufferedWriter.newLine();
            //metrics
            bufferedWriter.write("----Metrics----");
            bufferedWriter.write(String.format("Design Size in Class(Design Size): %,.5f", qualityReport.getBeforeRefactoring().getDesignSize()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of hierarchies(Hierachies): %,.5f", qualityReport.getBeforeRefactoring().getHierarchies()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Average Number of Ancestors(Abstraction): %,.5f", qualityReport.getBeforeRefactoring().getAbstraction()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Data Access Metrics (Encapsulation): %,.5f", qualityReport.getBeforeRefactoring().getEncapsulation()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Direct Class Coupling(Coupling): %,.5f", qualityReport.getBeforeRefactoring().getCoupling()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Cohesion Among Methods in Class(Cohesion): %,.5f", qualityReport.getBeforeRefactoring().getCohesion()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Aggregation(Composition): %,.5f", qualityReport.getBeforeRefactoring().getComposition()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Functional Abstraction(Inheritance): %,.5f", qualityReport.getBeforeRefactoring().getInheritance()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Polymorphic Methods(Polymorphism): %,.5f", qualityReport.getBeforeRefactoring().getPolymorphism()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Class Interface Size(Messaging): %,.5f", qualityReport.getBeforeRefactoring().getMessaging()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Methods(Complexity): %,.5f", qualityReport.getBeforeRefactoring().getComplexity()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Lines of code (LOC): %,.5f", qualityReport.getBeforeRefactoring().getLinesOfCode()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            
            //quality attributes
            bufferedWriter.write("----Quality Attributes----");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Reusability: %,.5f", qualityReport.getBeforeRefactoring().getReusability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Flexability: %,.5f", qualityReport.getBeforeRefactoring().getFlexability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Understandability: %,.5f", qualityReport.getBeforeRefactoring().getUnderstandability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Functionality: %,.5f", qualityReport.getBeforeRefactoring().getFunctionality()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Extendability: %,.5f", qualityReport.getBeforeRefactoring().getExtendability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Effectiveness: %,.5f", qualityReport.getBeforeRefactoring().getEffectiveness()));
            bufferedWriter.newLine();
            bufferedWriter.write("------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Quality Score: %,.5f", qualityReport.getBeforeRefactoring().getQualityScore()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
		 } catch (IOException e) {
	            e.printStackTrace();
	     } 
	}
	
	/**
	 * Writes details about the quality of the project after refactoring to the quality report file.
	 * @param bufferedWriter writer
	 * @param qualityReport quality report to be written to file
	 */
	private void writeQualityAfterRefactoringDetails(final BufferedWriter bufferedWriter, final QualityReport qualityReport) {
		try {
			bufferedWriter.write("======== Quality After Refactoring ========");
            bufferedWriter.newLine();
            //metrics
            bufferedWriter.write("----Metrics----");
            bufferedWriter.write(String.format("Design Size in Class(Design Size): %,.5f", qualityReport.getAfterRefactoring().getDesignSize()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of hierarchies(Hierachies): %,.5f", qualityReport.getAfterRefactoring().getHierarchies()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Average Number of Ancestors(Abstraction): %,.5f", qualityReport.getAfterRefactoring().getAbstraction()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Data Access Metrics (Encapsulation): %,.5f", qualityReport.getAfterRefactoring().getEncapsulation()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Direct Class Coupling(Coupling): %,.5f", qualityReport.getAfterRefactoring().getCoupling()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Cohesion Among Methods in Class(Cohesion): %,.5f", qualityReport.getAfterRefactoring().getCohesion()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Aggregation(Composition): %,.5f", qualityReport.getAfterRefactoring().getComposition()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Functional Abstraction(Inheritance): %,.5f", qualityReport.getAfterRefactoring().getInheritance()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Polymorphic Methods(Polymorphism): %,.5f", qualityReport.getAfterRefactoring().getPolymorphism()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Class Interface Size(Messaging): %,.5f", qualityReport.getAfterRefactoring().getMessaging()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Methods(Complexity): %,.5f", qualityReport.getAfterRefactoring().getComplexity()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Lines of code (LOC): %,.5f", qualityReport.getAfterRefactoring().getLinesOfCode()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            
            //quality attributes
            bufferedWriter.write("----Quality Attributes----");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Reusability: %,.5f", qualityReport.getAfterRefactoring().getReusability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Flexability: %,.5f", qualityReport.getAfterRefactoring().getFlexability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Understandability: %,.5f", qualityReport.getAfterRefactoring().getUnderstandability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Functionality: %,.5f", qualityReport.getAfterRefactoring().getFunctionality()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Extendability: %,.5f", qualityReport.getAfterRefactoring().getExtendability()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Effectiveness: %,.5f", qualityReport.getAfterRefactoring().getEffectiveness()));
            bufferedWriter.newLine();
            bufferedWriter.write("------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Quality Score: %,.5f", qualityReport.getAfterRefactoring().getQualityScore()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
		 } catch (IOException e) {
	            e.printStackTrace();
	     } 
	}

	/**
	 * Writes details about the how much the quality of the project has changed after refactoring to the quality report file.
	 * @param bufferedWriter writer
	 * @param qualityReport quality report to be written to file
	 */
	private void writeQualityChangesDetails(final BufferedWriter bufferedWriter, final QualityReport qualityReport) {
		try {
			bufferedWriter.write("======== Quality Changes After Refactoring ========");
            bufferedWriter.newLine();
            //metrics
            bufferedWriter.write("----Metrics----");
            bufferedWriter.write(String.format("Design Size in Class(Design Size): %,.5f", qualityReport.getDesignSizeChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of hierarchies(Hierachies): %,.5f", qualityReport.getHierarchiesChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Average Number of Ancestors(Abstraction): %,.5f", qualityReport.getAbstractionChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Data Access Metrics (Encapsulation): %,.5f", qualityReport.getEncapsulationChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Direct Class Coupling(Coupling): %,.5f", qualityReport.getCouplingChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Cohesion Among Methods in Class(Cohesion): %,.5f", qualityReport.getCohesionChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Aggregation(Composition): %,.5f", qualityReport.getCompositionChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Measure of Functional Abstraction(Inheritance): %,.5f", qualityReport.getInheritanceChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Polymorphic Methods(Polymorphism): %,.5f", qualityReport.getPolymorphismChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Class Interface Size(Messaging): %,.5f", qualityReport.getMessagingChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Number of Methods(Complexity): %,.5f", qualityReport.getComplexityChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Lines of code (LOC): %,.5f", qualityReport.getLinesOfCodeChange()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            
            //quality attributes
            bufferedWriter.write("----Quality Attributes----");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Reusability: %,.5f", qualityReport.getReusabilityChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Flexability: %,.5f", qualityReport.getFlexabilityChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Understandability: %,.5f", qualityReport.getUnderstandabiltyChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Functionality: %,.5f", qualityReport.getFunctionalityChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Extendability: %,.5f", qualityReport.getExtendabilityChange()));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Effectiveness: %,.5f", qualityReport.getEffectivenessChange()));
            bufferedWriter.newLine();
            bufferedWriter.write("------------------------------------------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Quality Score: %,.5f", qualityReport.getQualityScoreChange()));
            bufferedWriter.newLine();
            bufferedWriter.newLine();
		 } catch (IOException e) {
	            e.printStackTrace();
	     } 
	}
}
