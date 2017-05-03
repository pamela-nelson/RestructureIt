package restructureit.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import restructureit.qualitymeasurement.Metrics;
import restructureit.qualitymeasurement.QualityReport;
import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.processing.Processor;
import spoon.reflect.CtModel;

/**
 * Task controls the processing of code. It is responsible for
 * building an AST for the given input sources and applying the given processors.
 *
 * @author Pamela Nelson
 */
public abstract class Task implements ITask {

	/**
	 * Launcher instance to process code.
	 */
	protected Launcher launcher;
	
	/**
	 * Input sources to be processed. (Can be files or folders.)
	 */
	protected List<String> inputSources;
	
	/**
	 * List of processors to be applied. (Applied in order of list)
	 */
	protected List<String> processors;
	
	/**
	 * List of refactorings applied.
	 */
	protected Map<String, Integer> refactoringsApplied;
	
	/**
	 * List of templates to be applied.
	 */
	protected List<SpoonResource> templates;
	
	/**
	 * Location to output the processed code.
	 **/
	protected String outputSource;
	
	/**
	 * Indicates whether the input code has been processed.
	 */
	protected boolean isProcessed;
	
	/**
	 * Indicates whether the model has been built for the input programs.
	 */
	protected boolean isModelBuilt;
	
	/**
	 * Quality Metrics of Project.
	 */
	protected Metrics projectMetrics;

	/**
	 * Quality Report of Project.
	 */
	protected QualityReport projectQualityReport;
	
	/**
	 * Project name to be processed.
	 */
	private String projectName;
	
	/**
	 * Name of experiment being performed.
	 */
	private String experimentName;
	
	//CONSTRUCTOR
	
	/**
	 * Creates a new instance of Spoon Launcher.
	 */
	public Task() {
		launcher = new Launcher();
		inputSources = new ArrayList<String>();
		processors = new ArrayList<String>();
		templates = new ArrayList<SpoonResource>();
		refactoringsApplied = new HashMap<String, Integer>();
		outputSource = "";
		isProcessed = false;
		isModelBuilt = false;
	}
	
	//GETTERS AND SETTERS
	
	/**
	 * Gets the current instance of the launcher.
	 * @return Spoon launcher
	 */
	public Launcher getLauncher() {
		return launcher;
	}
	
	/**
	 * Sets the launcher.
	 * @param launcher to process code
	 */
	public void setLauncher(final Launcher launcher) {
		this.launcher = launcher;
	}

	/**
	 * Get the input sources to be processed.
	 * @return the inputSources
	 */
	public List<String> getInputSources() {
		return inputSources;
	}

	/**
	 * Set input source file/folder to process.
	 * @param inputSources the inputSources to set
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void setInputSources(final List<String> inputSources) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (this.inputSources.size() > 0) {
			clearLauncherParameter("input");
		}
		this.inputSources = inputSources;
		
		for (String inputSource : inputSources) {
			addInputSource(inputSource);
		}
	}

	/**
	 * Get the list of processors to be applied.
	 * @return the processors
	 */
	public List<String> getProcessors() {
		return processors;
	}

	/**
	 * Set the list of processors to be applied.
	 * @param processors the processors to set
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void setProcessors(final List<String> processors) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (this.processors.size() > 0) {
			clearLauncherParameter("refactoring");
		}
		this.processors = processors;
		
		for (String processor : processors) {
			addProcessor(processor);
		}
	}

	/**
	 * @return the refactoringsApplied
	 */
	public Map<String, Integer> getRefactoringsApplied() {
		return refactoringsApplied;
	}

	/**
	 * @param refactoringsApplied the refactoringsApplied to set
	 */
	public void setRefactoringsApplied(final Map<String, Integer> refactoringsApplied) {
		this.refactoringsApplied = refactoringsApplied;
	}

	/**
	 * Get the output file path of the processed code.
	 * @return the outputSource
	 */
	public String getOutputSource() {
		return outputSource;
	}

	/**
	 * Set the file path for the processed code.
	 * @param outputSource 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void setOutputSource(final String outputSource) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!this.outputSource.equals("")) {
			clearLauncherParameter("output");
		}
		
		this.outputSource = outputSource;
		File outputDirectory = new File(outputSource);
		outputDirectory.mkdirs();
		launcher.setSourceOutputDirectory(outputSource);
	}
	
	/**
	 * @return the list of added templates
	 */
	public List<SpoonResource> getTemplates() {
		return templates;
	}

	/**
	 * Returns the model built from the input sources given.
	 * @return program AST model
	 */
	public CtModel getProgramModel() {
		if (inputSources.size() > 0 && !isModelBuilt) {
			launcher.buildModel();
			return launcher.getModel();
		} else if (inputSources.size() > 0) {
			return launcher.getModel();
		}
		
		return null;
	}
	
	/**
	 * @return the isProcessed
	 */
	public boolean isProcessed() {
		return isProcessed;
	}

	/**
	 * @return the isModelBuilt
	 */
	public boolean isModelBuilt() {
		return isModelBuilt;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(final String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the experimentName
	 */
	public String getExperimentName() {
		return experimentName;
	}
	
	/**
	 * @param experimentName the experimentName to set
	 */
	public void setExperimentName(final String experimentName) {
		this.experimentName = experimentName;
	}

	/**
	 * @return the projectMetrics
	 */
	public Metrics getProjectMetrics() {
		return projectMetrics;
	}

	/**
	 * @return the projectQualityReport
	 */
	public QualityReport getProjectQualityReport() {
		return projectQualityReport;
	}
	
	//PUBLIC METHODS (ADD/REMOVE/CLEAR/PROCESS)
	
	/**
	 * Adds the provided input source path to the launcher.
	 * @param inputSourcePath file path of code to process
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void addInputSource(final String inputSourcePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!inputSources.contains(inputSourcePath) && !isModelBuilt) {
			inputSources.add(inputSourcePath);
			launcher.addInputResource(inputSourcePath);
		} else if (!inputSources.contains(inputSourcePath)) {
			inputSources.add(inputSourcePath);
			setInputSources(inputSources);
		}
	}
	
	/**
	 * Removes the provided input Source path from the launcher.
	 * @param inputSourcePath file path of code to refactor to remove
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void removeInputSource(final String inputSourcePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (inputSources.contains(inputSourcePath)) {
			inputSources.remove(inputSourcePath);
			setInputSources(inputSources);
		}
	}
	
	/**
	 * Adds the given processor to the launcher.
	 * @param processorName to be applied to code
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void addProcessor(final String processorName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!processors.contains(processorName) && !isProcessed) {
			processors.add(processorName);
			launcher.addProcessor((Processor<?>) Class.forName(processorName).newInstance());
		} else if (!processors.contains(processorName)) {
			processors.add(processorName);
			setProcessors(processors);
		}
	}
	
	/**
	 * Removes the provided refactoring from the launcher.
	 * @param refactoring method to remove
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void removeRefactoring(final String refactoring) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (processors.contains(refactoring)) {
			processors.remove(refactoring);
			setProcessors(processors);
		}
	}
	
	/**
	 * Removes the provided Output Source from the launcher.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void removeOutputSource() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!outputSource.equals("")) {
			clearLauncherParameter("output");
			outputSource = "";
		}
	}
	
	/**
	 * Adds the given template to the launcher.
	 * @param template template to be added to launcher
	 */
	public void addTemplate(final SpoonResource template) {
		if (!templates.contains(template)) {
			templates.add(template);
			launcher.addTemplateResource(template);
		}
	}
	
	/**
	 * Processes the given input source code.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void processCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (processors.size() > 0 && inputSources.size() > 0 && !isProcessed) {
			if (!isModelBuilt) {
				launcher.buildModel();
			}
			launcher.process();
			isProcessed = true;
		}
	}
	
	//protected METHODS (CLEAR PARAMETER/ SET TEMPLATES)
	/**
	 * Resets/Clears the given parameter in the launcher.
	 * @param parameterToClear accepts "input", "refactoring", "output" and "all"
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	protected void clearLauncherParameter(final String parameterToClear) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher = new Launcher();
		isProcessed = false;
		
		switch (parameterToClear) {
			case "input": 		setProcessors(getProcessors());
								setOutputSource(getOutputSource());
								setTemplateResources(getTemplates());
								break;
			case "refactoring": setInputSources(getInputSources());
							    setOutputSource(getOutputSource());
							    setTemplateResources(getTemplates());
							    break;
			case "output":		setInputSources(getInputSources());
								setProcessors(getProcessors());
								setTemplateResources(getTemplates());
								break;
			case "all":			break;
		}
	}
	
	/**
	 * Sets the templates for the launcher. SHould only be done by spoonLauncher class.
	 * @param templates templates
	 */
	protected void setTemplateResources(final List<SpoonResource> templates) {
		for (SpoonResource template : templates) {
			addTemplate(template);
		}
	}
	
}
