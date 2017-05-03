package restructureit;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * @author Pamela
 *
 */
public class Main extends Application {
	
	/**
	 * Javafx stage.
	 */
	public static Stage stage;
	
	/**
	 * @param primaryStage stage
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 */
	public void start(final Stage primaryStage) {
		
		try {
			Parent main = FXMLLoader.load(getClass().getResource("ui/views/MainPage.fxml"));
			Scene scene = new Scene(main);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Restructure It");
			Image icon = new Image(getClass().getResourceAsStream("ui/images/icon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			stage = primaryStage;
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//			try {
//				QualityMeasurementTask qualityMeasurementTask = new QualityMeasurementTask();
//				qualityMeasurementTask.addInputSource("sources/original/mango");
//				qualityMeasurementTask.setOutputSource("/");
//				qualityMeasurementTask.setExperimentName("Original");
//				qualityMeasurementTask.setProjectName("Mango");
//				qualityMeasurementTask.processCode();
//			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			System.out.println("done");
			
		
		
		
		
//		RefactoringTask launcher = new RefactoringTask();
//		launcher.addInputSource("sources/original/mango");
//		launcher.setOutputSource("results/");
//		launcher.addProcessor("restructureit.qualitymeasurement.processors.PolymorphismCalculator");
//		//launcher.addProcessor("restructureit.refactorings.utils.processors.UpdateFieldAccessReferences2");
//		//launcher.addProcessor("restructureit.refactorings.processors.PullUpMethod");
//		launcher.processCode();
//		
//		System.out.println("done");
	}
	
	/**
	 * 
	 * @param args arguments
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
