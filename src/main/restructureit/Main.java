package restructureit;

import javafx.application.Application;
import javafx.stage.Stage;
import restructureit.utils.SpoonLauncher;

/**
 * 
 * @author Pamela
 *
 */
public class Main extends Application {
	
	/**
	 * @param primaryStage stage
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void start(final Stage primaryStage) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		SpoonLauncher launcher = new SpoonLauncher();
		launcher.addInputSource("src/test/resources/restructureit/test/refactorings/encapsulatefieldtests/");
		launcher.setOutputSource("results/");
		launcher.addRefactoring("restructureit.refactorings.EncapsulateField");
		launcher.addRefactoring("restructureit.refactorings.utils.processors.UpdateFieldAccessReferences");
		launcher.processRefactoring();
		launcher.outputProcessedCode();
		
		System.out.println("done");
	}
	
	/**
	 * 
	 * @param args arguments
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
