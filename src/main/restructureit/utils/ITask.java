package restructureit.utils;

import java.util.List;
import java.util.Map;

import restructureit.qualitymeasurement.Metrics;
import restructureit.qualitymeasurement.QualityReport;
import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.reflect.CtModel;

/**
 *
 */
public interface ITask {

	//GETTERS AND SETTERS
	
		/**
		 * Gets the current instance of the launcher.
		 * @return Spoon launcher
		 */
		Launcher getLauncher();
		
		/**
		 * Sets the launcher.
		 * @param launcher to process code
		 */
		void setLauncher(Launcher launcher);

		/**
		 * Get the input sources to be processed.
		 * @return the inputSources
		 */
		List<String> getInputSources();

		/**
		 * Set input source file/folder to process.
		 * @param inputSources the inputSources to set
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void setInputSources(List<String> inputSources) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

		/**
		 * Get the list of processors to be applied.
		 * @return the processors
		 */
		List<String> getProcessors();

		/**
		 * Set the list of processors to be applied.
		 * @param processors the processors to set
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void setProcessors(List<String> processors) throws InstantiationException, IllegalAccessException, ClassNotFoundException;

		/**
		 * @return the refactoringsApplied
		 */
		Map<String, Integer> getRefactoringsApplied();

		/**
		 * @param refactoringsApplied the refactoringsApplied to set
		 */
		void setRefactoringsApplied(Map<String, Integer> refactoringsApplied);

		/**
		 * Get the output file path of the processed code.
		 * @return the outputSource
		 */
		String getOutputSource();

		/**
		 * Set the file path for the processed code.
		 * @param outputSource 
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void setOutputSource(String outputSource) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * @return the list of added templates
		 */
		List<SpoonResource> getTemplates();

		/**
		 * Returns the model built from the input sources given.
		 * @return program AST model
		 */
		CtModel getProgramModel();
		
		/**
		 * @return the isProcessed
		 */
		boolean isProcessed();

		/**
		 * @return the isModelBuilt
		 */
		boolean isModelBuilt();

		/**
		 * @return the projectName
		 */
		String getProjectName();
		
		/**
		 * @param projectName project Name
		 */
		void setProjectName(String projectName);

		/**
		 * @return the experimentName
		 */
		String getExperimentName();
		
		/**
		 * @param experimentName experiment Name
		 */
		void setExperimentName(String experimentName);
		
		/**
		 * @return the projectMetrics
		 */
		Metrics getProjectMetrics();

		/**
		 * @return the projectQualityReport
		 */
		QualityReport getProjectQualityReport();
		
		//PUBLIC METHODS (ADD/REMOVE/CLEAR/PROCESS)
		
		/**
		 * Adds the provided input source path to the launcher.
		 * @param inputSourcePath file path of code to process
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void addInputSource(String inputSourcePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * Removes the provided input Source path from the launcher.
		 * @param inputSourcePath file path of code to refactor to remove
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void removeInputSource(String inputSourcePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * Adds the given processor to the launcher.
		 * @param processorName to be applied to code
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void addProcessor(String processorName) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * Removes the provided refactoring from the launcher.
		 * @param refactoring method to remove
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void removeRefactoring(String refactoring) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * Removes the provided Output Source from the launcher.
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void removeOutputSource() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		
		/**
		 * Adds the given template to the launcher.
		 * @param template template to be added to launcher
		 */
		void addTemplate(SpoonResource template);
		
		/**
		 * Processes the given input source code.
		 * @throws ClassNotFoundException 
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		void processCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException;
		

		
}
