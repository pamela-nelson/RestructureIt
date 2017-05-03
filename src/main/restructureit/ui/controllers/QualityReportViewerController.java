package restructureit.ui.controllers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import restructureit.Main;

/**
 * This class is the controller that manages the communication between the quality report viewer Page
 * and the underlying system.
 */
public class QualityReportViewerController {

	/**
	 * Text field showing the file path of the currently viewed 
	 * Quality report.
	 */
	@FXML public TextField txtQualityReportFilePath;
	
	/**
	 * Button allowing selection of a quality report to view.
	 */
	@FXML public Button btnQualityReport;
	
	/**
	 * Text Area to display the Quality report text file.
	 */
	@FXML public TextArea txtQualityReport;
	
	/**
	 * 
	 */
	@FXML
	public void initialize() {
    }
	
	/**
	 * Opens file browser allowing selection of a quality report then displays
	 * it on the screen.
	 */
	@FXML public void openQualityReport() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Quality Report to view");
		fileChooser.setInitialDirectory(new File("results/"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
		File file = fileChooser.showOpenDialog(Main.stage);
		if (file != null) {
			txtQualityReportFilePath.setText(file.getAbsolutePath());
			txtQualityReport.setText("");
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;

				while ((line = br.readLine()) != null) {
		        	txtQualityReport.appendText(line + "\n");
		        }
		        br.close();
		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
