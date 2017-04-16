package restructureit.utils;

import java.util.ArrayList;
import java.util.List;

import spoon.Launcher;
import spoon.compiler.SpoonResource;
import spoon.processing.Processor;
import spoon.reflect.CtModel;

/**
 * SpoonLauncher controls the refactoring process. It is responsible for
 * building an AST for the given input sources. Applying the given refactorings
 * and generating the refactored java code to the provided output location.
 *
 * @author Pamela Nelson
 */
public class SpoonLauncher {

	/**
	 * Launcher instance to process code refactorings.
	 */
	private Launcher launcher;
	
	/**
	 * Input sources to be refactored. (Can be files or folders.)
	 */
	private List<String> inputSources;
	
	/**
	 * List of refactorings to be applied. (Applied in order of list)
	 */
	private List<String> refactorings;
	
	/**
	 * List of templates to be applied.
	 */
	private List<SpoonResource> templates;
	
	/**
	 * Location to output the refactored code.
	 **/
	private String outputSource;
	
	/**
	 * Indicates whether the input code has been refactored.
	 */
	private boolean isProcessed;
	
	/**
	 * Indicates whether the model has been built for the input programs.
	 */
	private boolean isModelBuilt;
	
	//CONSTRUCTOR
	
	/**
	 * Creates a new instance of Spoon Launcher.
	 */
	public SpoonLauncher() {
		launcher = new Launcher();
		inputSources = new ArrayList<String>();
		refactorings = new ArrayList<String>();
		templates = new ArrayList<SpoonResource>();
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
	 * @param launcher to refactor code
	 */
	public void setLauncher(final Launcher launcher) {
		this.launcher = launcher;
	}

	/**
	 * Get the input sources to be refactored.
	 * @return the inputSources
	 */
	public List<String> getInputSources() {
		return inputSources;
	}

	/**
	 * Set input source file/folder to refactor.
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
	 * Get the list of refactorings to be applied.
	 * @return the refactorings
	 */
	public List<String> getRefactorings() {
		return refactorings;
	}

	/**
	 * Set the list of refactorings to be applied.
	 * @param refactorings the refactorings to set
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void setRefactorings(final List<String> refactorings) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (this.refactorings.size() > 0) {
			clearLauncherParameter("refactoring");
		}
		this.refactorings = refactorings;
		
		for (String refactoring : refactorings) {
			addRefactoring(refactoring);
		}
	}

	/**
	 * Get the output file path of the refactored code.
	 * @return the outputSource
	 */
	public String getOutputSource() {
		return outputSource;
	}

	/**
	 * Set the file path for the refactored code.
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
	
	//PUBLIC METHODS (ADD/REMOVE/CLEAR/PROCESS REFACTORING/OUTPUT REFACTORED CODE)
	
	/**
	 * Adds the provided input source path to the launcher.
	 * @param inputSourcePath file path of code to refactor
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
	 * Adds the given refactoring to the launcher.
	 * @param refactoring to be applied to code
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void addRefactoring(final String refactoring) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (!refactorings.contains(refactoring) && !isProcessed) {
			refactorings.add(refactoring);
			launcher.addProcessor((Processor) Class.forName(refactoring).newInstance());
		} else if (!refactorings.contains(refactoring)) {
			refactorings.add(refactoring);
			setRefactorings(refactorings);
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
		if (refactorings.contains(refactoring)) {
			refactorings.remove(refactoring);
			setRefactorings(refactorings);
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
	 * Refactors the given input source code.
	 */
	public void processRefactoring() {
		if (refactorings.size() > 0 && inputSources.size() > 0 && !isProcessed) {
			if (!isModelBuilt) {
				launcher.buildModel();
			}
			launcher.process();
			isProcessed = true;
		}
	}
	
	/**
	 * Outputs the refactored code to the provided outputsource.
	 */
	public void outputProcessedCode() {
		if (isProcessed) {
			launcher.prettyprint();
		}
	}
	
	//PRIVATE METHODS (CLEAR PARAMETER/ SET TEMPLATES)
	/**
	 * Resets/Clears the given parameter in the launcher.
	 * @param parameterToClear accepts "input", "refactoring", "output" and "all"
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void clearLauncherParameter(final String parameterToClear) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		launcher = new Launcher();
		isProcessed = false;
		
		switch (parameterToClear) {
			case "input": 		setRefactorings(getRefactorings());
								setOutputSource(getOutputSource());
								setTemplateResources(getTemplates());
								break;
			case "refactoring": setInputSources(getInputSources());
							    setOutputSource(getOutputSource());
							    setTemplateResources(getTemplates());
							    break;
			case "output":		setInputSources(getInputSources());
								setRefactorings(getRefactorings());
								setTemplateResources(getTemplates());
								break;
			case "all":			break;
		}
	}
	
	/**
	 * Sets the templates for the launcher. SHould only be done by spoonLauncher class.
	 * @param templates templates
	 */
	private void setTemplateResources(final List<SpoonResource> templates) {
		for (SpoonResource template : templates) {
			addTemplate(template);
		}
	}
	
}
