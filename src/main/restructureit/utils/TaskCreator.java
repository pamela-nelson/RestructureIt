package restructureit.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import restructureit.refactorings.processors.CollapseHierarchy;
import restructureit.refactorings.processors.EncapsulateCollection;
import restructureit.refactorings.processors.EncapsulateField;
import restructureit.refactorings.processors.ExtractSuperClass;
import restructureit.refactorings.processors.HideMethod;
import restructureit.refactorings.processors.PullUpField;
import restructureit.refactorings.processors.PullUpMethod;
import restructureit.refactorings.processors.PushDownField;
import restructureit.refactorings.processors.PushDownMethod;

/**
 * Creates tasks based on input file.
 */
public final class TaskCreator {
	
	/**
	 * Holds all the project filePaths to perform the experiment on.
	 */
	private static List<String> projectFilePaths = new ArrayList<String>();
	
	/**
	 * Holds the names of the projects to perform the experiment on.
	 */
	private static List<String> projectNames = new ArrayList<String>();
	
	/**
	 * Holds the name of the processors to use in the experiment. (Applied in order specified)
	 */
	private static List<String> refactorings = new ArrayList<String>();
	
	/**
	 * List of tasks to perform experiments.
	 */
	private static List<ITask> tasks = new ArrayList<ITask>();
	
	/**
	 * Name of the experiment. Defaults to fileName
	 */
	private static String experimentName;
	
	/**
	 * Type of task to create. Refactor by default.
	 */
	private static String taskType = "Refactor";
	
	/**
	 * Default file path for refactored code.
	 */
	private static String defaultOutputPath = "sources/refactored/";
	
	/**
	 * File path to output refactored code.
	 */
	private static String outputPath = "";
	
	/**
	 * 
	 */
	private TaskCreator() {	
	}
	
	/**
	 * Creates a list of tasks based on the input file.
	 * @param filePath path to task creation file
	 * @return list of tasks
	 */
	public static List<ITask> createTasksFromFile(final String filePath) {
		File file = new File(filePath);
		
		if (file.exists()) {
			if (file.getName().endsWith(".txt")) {
				try {
					experimentName = file.getName().substring(0, file.getName().length() - 2);
					taskType = "Refactor";
					
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;

					while ((line = br.readLine()) != null) {
						String[] command = line.split(": ");
						
						if (command.length > 1) {
							
							switch (command[0]) {
								case "Project" : addProject(command[1]);
												 break;
								case "Experiment Name" : experimentName = command[1];
														 break;
								case "Refactoring" :  addRefactoring(command[1]);
													  break;
								case "Type" :	taskType = command[1];
												break;
								case "Output Path" : setOutputPath(command[1]);
													 break;
								case "Command" : try {
														processCommand(command[1]);
													 } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
														e.printStackTrace();
													 }
													 break;
							}
						}
					}

					br.close(); 
					} catch (IOException e) {
						
					System.out.println("\r\nEXCEPTION: Cannot read projects from text file.");
					System.exit(1);
				}
			}
		}
		
		List<ITask> allTasks = new ArrayList<ITask>();
		
		for (ITask task : tasks) {
			allTasks.add(task);
		}
		
		resetAllTaskParameters();
		
		return allTasks;
	}
	
	/**
	 * Add project to list of projects to be experimented on.
	 * @param filePath filePath to project
	 */
	private static void addProject(final String filePath) {
		File project = new File(filePath);
		
		if (project.isDirectory()) {
			projectFilePaths.add(filePath);
			projectNames.add(project.getName());
		}
	}
	
	/**
	 * Add refactoring to list to add to current task.
	 * @param refactoring refactoring type to add
	 */
	private static void addRefactoring(final String refactoring) {
		if (!refactorings.contains(refactoring)) {
			refactorings.add(refactoring);
		}
	}
	
	/**
	 * Set a different output filepath than the default.
	 * @param filePath filepath to output refactored code.
	 */
	private static void setOutputPath(final String filePath) {
		File outputDirectory = new File(filePath);
		
		if (!filePath.equals(defaultOutputPath)) {
			outputDirectory.mkdirs();
			outputPath = filePath;
		}
	}
	
	/**
	 * Set the refactorings of the task.
	 * @param task task
	 * @return task with added refactorings
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static ITask setRefactorings(final ITask task) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		for (int i = 0; i < refactorings.size(); i++) {
			switch (refactorings.get(i)) {
				case "CollapseHierarchy"     : task.addProcessor("restructureit.refactorings.processors.CollapseHierarchy");
											   break;
				case "EncapsulateCollection" : task.addProcessor("restructureit.refactorings.processors.EncapsulateCollection");
				   							   break;
				case "EncapsulateField"  	 : task.addProcessor("restructureit.refactorings.processors.EncapsulateField");
				   							   break;
				case "ExtractSuperClass"     : task.addProcessor("restructureit.refactorings.processors.ExtractSuperClass");
											   break;
				case "HideMethod"            : task.addProcessor("restructureit.refactorings.processors.HideMethod");
				   							   break;
				case "PullUpField"			 : task.addProcessor("restructureit.refactorings.processors.PullUpField");
				   							   break;
				case "PullUpMethod"          : task.addProcessor("restructureit.refactorings.processors.PullUpMethod");
				   							   break;
				case "PushDownField"		 : task.addProcessor("restructureit.refactorings.processors.PushDownField");
				   							   break;
				case "PushDownMethod"	     : task.addProcessor("restructureit.refactorings.processors.PushDownMethod");
				   							   break;
			}
		}
		
		return task;	
	}
	
	/**
	 * Process command from text file. Can indicate the end of a tasks configuration in the file.
	 * @param command command to perform
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static void processCommand(final String command) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		switch (command) {
			case "Create Task" : createTasks();
		}
	}
	
	/**
	 * Create tasks for currently loaded task.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static void createTasks() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		for (int i = 0; i < projectFilePaths.size(); i++) {
			ITask task;
			if (taskType.equals("Refactor")) {
				task = new RefactoringTask();
			} else {
				task = new QualityMeasurementTask();
			}
			
			//Set Experiment Name
			task.setExperimentName(experimentName);
			
			//Set Project Name
			task.setProjectName(projectNames.get(i));
			
			//Add input Project Path
			List<String> paths = new ArrayList<String>();
			paths.add(projectFilePaths.get(i));
			task.setInputSources(paths);
			paths.clear();
			
			//Set Output Path
			if (outputPath.equals("")) {
				String fileName = "";
				for (String refactor : refactorings) {
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
				outputPath = defaultOutputPath + String.format("%s/%s/%s", experimentName, projectNames.get(i), fileName);
				task.setOutputSource(outputPath);
			} else {
				task.setOutputSource(outputPath);
			}
			
			//set refactorings
			task = setRefactorings(task);
			
			//add task to list
			tasks.add(task);
			
			//create quality measurement task corresponding to refactoring task
			if (taskType.equals("Refactor")) {
				QualityMeasurementTask qualityMeasurementTask = new QualityMeasurementTask();
				
				//Set Experiment Name
				qualityMeasurementTask.setExperimentName(experimentName);
				
				//Set Project Name
				qualityMeasurementTask.setProjectName(projectNames.get(i));
				
				//Add input Project Path
				paths.add(outputPath);
				qualityMeasurementTask.setInputSources(paths);
				
				//Set Output Path
				qualityMeasurementTask.setOutputSource(outputPath);
				
				tasks.add(qualityMeasurementTask);
			}		
		}	
		
		resetTaskParameters();
	}
	
	//TODO experiment specific tasks
	/*
	private List<ITask> experimentOneSingleRefactor() {
		
	}
	
	private List<ITask> experimentTwoAllRefactorCombonations() {
		
	}*/
	
	/**
	 * Resets task parameters so a new task can be created.
	 */
	private static void resetTaskParameters() {
		refactorings.clear();
		taskType = "Refactor";
		outputPath = "";
	}
	
	/**
	 * Resets all task parameters so a new file can be read.
	 */
	private static void resetAllTaskParameters() {
		refactorings.clear();
		tasks.clear();
		projectFilePaths.clear();
		projectNames.clear();
		taskType = "Refactor";
		outputPath = "";
	}
	
}
