package restructureit;

import javafx.application.Application;
import javafx.stage.Stage;
import restructureit.utils.SpoonLauncher;
import spoon.support.compiler.FileSystemFolder;

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
		launcher.addTemplate(new FileSystemFolder("src/main/restructureit/refactorings/utils/templates"));
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
