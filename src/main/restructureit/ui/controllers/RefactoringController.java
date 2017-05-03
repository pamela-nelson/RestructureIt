package restructureit.ui.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import restructureit.Main;
import restructureit.utils.ITask;
import restructureit.utils.QualityMeasurementTask;
import restructureit.utils.RefactoringTask;
import restructureit.utils.TaskCreator;
import restructureit.utils.TaskManager;

/**
 * This class is the controller that manages the communication between the refactoring Page view
 * and the underlying system.
 */
public class RefactoringController {

	/**
	 * Input source text box showing selected directory.
	 */
	@FXML private TextField txtInputSource;
	/**
	 * Button that sets the Input Source directory.
	 */
	@FXML private Button btnInputSource;
	
	/**
	 * Output source text box showing selected directory.
	 */
	@FXML private TextField txtOutputSource;
	/**
	 * Button that sets the Output source directory.
	 */
	@FXML private Button btnOutputSource;
	
	/**
	 * Text field to set the experiment name.
	 */
	@FXML private TextField txtExperimentName;
	
	/**
	 * Config File text box showing file path selected.
	 */
	@FXML private TextField txtConfigFile;
	/**
	 * Button that sets the config file directory.
	 */
	@FXML private Button btnConfigFile; 
	
	/**
	 * Check box indicating whether collapse hierarchy refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbCollapseHierarchy;
	/**
	 * Check box indicating whether encapsulate Collection refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbEncapsulateCollection;
	/**
	 * Check box indicating whether encapsulate Field refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbEncapsulateField;
	/**
	 * Check box indicating whether extract super class refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbExtractSuperClass;
	/**
	 * Check box indicating whether hide method refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbHideMethod;
	/**
	 * Check box indicating whether pull up field refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbPullUpField;
	/**
	 * Check box indicating whether pull up method refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbPullUpMethod;
	/**
	 * Check box indicating whether push down field refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbPushDownField;
	/**
	 * Check box indicating whether push down method refactoring
	 * should be applied.
	 */
	@FXML private CheckBox cbPushDownMethod;
	
	/**
	 * Button that clears all settings.
	 */
	@FXML private Button btnClearSettings;
	
	/**
	 * Button that starts the refactoring.
	 */
	@FXML private Button btnRunRefactoring;
	
	/**
	 * Label that prompts the user.
	 */
	@FXML private Label lblPrompt;
	
	/**
	 * Indicates if experiment is user defined or experiment configuration.
	 */
	private String experimentType = "";
	
	/**
	 * 
	 */
	@FXML
	public void initialize() {
    }
	
	/**
	 * Set Input Source Directory.
	 */
	@FXML private void setInputSourceDirectory() {
		if (experimentType.equals("") || experimentType.equals("userDefined")) {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setInitialDirectory(new File("sources/original"));
			directoryChooser.setTitle("Choose the directory of the Project to Refactor");
			final File selectedDirectory = directoryChooser.showDialog(Main.stage);
			if (selectedDirectory != null) {
				txtInputSource.setText(selectedDirectory.getAbsolutePath());
				if (experimentType.equals("")) {
					experimentType = "userDefined";
					lblPrompt.setText("Creating user defined refactor");
				}
			}
		} else {
			lblPrompt.setText("Please clear settings and try again");
		}
	}
	
	/**
	 * Set Ouput Source Directory.
	 */
	@FXML private void setOuputSourceDirectory() {
		if (experimentType.equals("") || experimentType.equals("userDefined")) {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setInitialDirectory(new File("sources/refactored"));
			directoryChooser.setTitle("Choose the directory to output the refactored project");
			final File selectedDirectory = directoryChooser.showDialog(Main.stage);
			if (selectedDirectory != null) {
				txtOutputSource.setText(selectedDirectory.getAbsolutePath());
				if (experimentType.equals("")) {
					experimentType = "userDefined";
					lblPrompt.setText("Creating user defined refactor");
				}
			}
		} else {
			lblPrompt.setText("Please clear settings and try again");
		}
	}
	
	/**
	 * Set config file path.
	 */
	@FXML private void setConfigFile() {
		if (experimentType.equals("") || experimentType.equals("configurationFile")) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Experiment Configuration File");
			fileChooser.setInitialDirectory(new File("experiment_configuration/"));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
			File file = fileChooser.showOpenDialog(Main.stage);
			if (file != null) {
				txtConfigFile.setText(file.getAbsolutePath());
				if (experimentType.equals("")) {
					experimentType = "configurationFile";
					lblPrompt.setText("Creating refactor using configuration file");
				}
			}
		} else {
			lblPrompt.setText("Please clear settings and try again");
		}
	}
	
	/**
	 * Run refactoring.
	 */
	@FXML private void runRefactoring() {
		if (experimentType.equals("")) {
			lblPrompt.setText("Please complete refactoring details");
		} else if (experimentType.equals("configurationFile")) {
			TaskManager taskManager = new TaskManager();
			List<ITask> tasks = TaskCreator.createTasksFromFile(txtConfigFile.getText());
			taskManager.addTasks(tasks);
			lblPrompt.setText("Refactoring Please Wait");
			try {
				taskManager.executeTasks();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				lblPrompt.setText("Problem refactoring using provided file");
				e.printStackTrace();
			}
			clearSettings();
			lblPrompt.setText("Refactoring Completed");
		} else if (experimentType.equals("userDefined")) {
			if (txtInputSource.getText().equals("")) {
				lblPrompt.setText("Please set projects directory");
			} else if (txtOutputSource.getText().equals("")) {
				lblPrompt.setText("Please set output directory");
			} else if (txtExperimentName.equals("")) {
				lblPrompt.setText("Please specify an experiment name");
			} else if (getNumberOfSelectedRefactorings() == 0) {
				lblPrompt.setText("Please choose atleast one refactoring type to apply");
			} else {
				TaskManager taskManager = new TaskManager();
				
				RefactoringTask refactoringTask = new RefactoringTask();
				try {
					refactoringTask.addInputSource(txtInputSource.getText());
					refactoringTask.setOutputSource(txtOutputSource.getText());
					refactoringTask.setExperimentName(txtExperimentName.getText());
					String filePath = txtInputSource.getText().replace("\\", "/");
					String[] fileDirectory = filePath.split("/");
					refactoringTask.setProjectName(fileDirectory[fileDirectory.length - 1]);
					
					for (String refactor : getRefactorings()) {
						refactoringTask.addProcessor(refactor);
					}
					
					taskManager.addTask(refactoringTask);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					clearSettings();
					lblPrompt.setText("Problem creating refactoring task");
					e.printStackTrace();
				}
				
				QualityMeasurementTask qualityMeasurementTask = new QualityMeasurementTask();
				try {
					qualityMeasurementTask.addInputSource(txtOutputSource.getText());
					qualityMeasurementTask.setOutputSource(txtOutputSource.getText());
					qualityMeasurementTask.setExperimentName(txtExperimentName.getText());
					String filePath = txtInputSource.getText().replace("\\", "/");
					String[] fileDirectory = filePath.split("/");
					qualityMeasurementTask.setProjectName(fileDirectory[fileDirectory.length - 1]);
					
					taskManager.addTask(qualityMeasurementTask);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					clearSettings();
					lblPrompt.setText("Problem creating quality measurement task");
					e.printStackTrace();
				}
				
				lblPrompt.setText("Refactoring Please Wait");
				
				try {
					taskManager.executeTasks();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					lblPrompt.setText("Problem Refactoring code");
					e.printStackTrace();
				}
				clearSettings();
				lblPrompt.setText("Refactoring Completed");
			}
		}
	}
	
	/**
	 * Run refactoring.
	 */
	@FXML private void clearSettings() {
		txtInputSource.setText("");
		txtOutputSource.setText("");
		txtExperimentName.setText("");
		txtConfigFile.setText("");
		cbCollapseHierarchy.setSelected(false);
		cbEncapsulateCollection.setSelected(false);
		cbEncapsulateField.setSelected(false);
		cbExtractSuperClass.setSelected(false);
		cbHideMethod.setSelected(false);
		cbPullUpField.setSelected(false);
		cbPullUpMethod.setSelected(false);
		cbPushDownField.setSelected(false);
		cbPushDownMethod.setSelected(false);
		lblPrompt.setText("");
	}
	
	/**
	 * Number of selected refactorings.
	 * @return refactorings
	 */
	private int getNumberOfSelectedRefactorings() {
		int count = 0;
		
		if (cbCollapseHierarchy.isSelected()) {
			count++;
		}
		
		if (cbEncapsulateCollection.isSelected()) {
			count++;
		}
		
		if (cbEncapsulateField.isSelected()) {
			count++;
		}
		
		if (cbExtractSuperClass.isSelected()) {
			count++;
		}
		
		if (cbHideMethod.isSelected()) {
			count++;
		}
		
		if (cbPullUpField.isSelected()) {
			count++;
		}
		
		if (cbPullUpMethod.isSelected()) {
			count++;
		}
		
		if (cbPushDownField.isSelected()) {
			count++;
		}
		
		if (cbPushDownMethod.isSelected()) {
			count++;
		}
		
		return count;
	}
	
	/**
	 * Get refactoring processor names of selected refactorings.
	 * @return list of refactors
	 */
	private List<String> getRefactorings() {
		List<String> refactors = new ArrayList<String>();
		
		if (cbCollapseHierarchy.isSelected()) {
			refactors.add("restructureit.refactorings.processors.CollapseHierarchy");
		}
		
		if (cbPullUpField.isSelected()) {
			refactors.add("restructureit.refactorings.processors.PullUpField");
		}
		
		if (cbPullUpMethod.isSelected()) {
			refactors.add("restructureit.refactorings.processors.PullUpMethod");
		}
		
		if (cbPushDownField.isSelected()) {
			refactors.add("restructureit.refactorings.processors.PushDownField");
		}
		
		if (cbPushDownMethod.isSelected()) {
			refactors.add("restructureit.refactorings.processors.PushDownMethod");
		}
		
		if (cbExtractSuperClass.isSelected()) {
			refactors.add("restructureit.refactorings.processors.ExtractSuperClass");
		}
		
		if (cbEncapsulateField.isSelected()) {
			refactors.add("restructureit.refactorings.processors.EncapsulateField");
		}
		
		if (cbEncapsulateCollection.isSelected()) {
			refactors.add("restructureit.refactorings.processors.EncapsulateCollection");
		}
		
		if (cbHideMethod.isSelected()) {
			refactors.add("restructureit.refactorings.processors.HideMethod");
		}
		
		return refactors;
	}
}
