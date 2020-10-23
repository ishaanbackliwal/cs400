/**
 * Main.java created by ishaanbackliwalApr 8, 2020
 *
 * Author: Ishaan Backliwal
 * Date: @date
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * List Collaborators: name, email@wisc.edu, lecture number
 * 
 * Other Credits: 
 * 
 * Known Bugs: 
 */
package application;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main - TODO Describe purpose of this user-defined type
 * @author backliwal ishaanbackliwal
 *
 */
public class Main extends Application {
	private List<String> args;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 400;
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			// save args example
			args = this.getParameters().getRaw();
				
			// Create a vertical box with Hello labels for each args
	        	VBox vbox = new VBox();
	        	for (String arg : args) {
	        		vbox.getChildren().add(new Label("hello "+arg));
	        	}
			// Main layout is Border Pane example (top,left,center,right,bottom)
	        	BorderPane root = new BorderPane();
	        	
	        // Top panel
	        root.setTop(new Label("CS400 My First JavaFX Program"));
			
			// ComboBox in left panel
			ComboBox<String> comboBox = new ComboBox<String>();
			comboBox.getItems().add("Choice 1");
	        comboBox.getItems().add("Choice 2");
	        comboBox.getItems().add("Choice 3");
			root.setLeft(comboBox);
			
			// ImageView in center panel
			ImageView imageView = new ImageView();
			Image image = new Image("face.png");
			imageView.setImage(image);
			root.setCenter(imageView);
			
			// Button in bottom panel
			Button button = new Button("done");
			root.setBottom(button);
			
			// RadioButtons in right panel
			CheckBox checkBox = new CheckBox("Check 1");
			root.setRight(checkBox);
			
			Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			
			primaryStage.setTitle("CS400 My First JavaFX Program");
        	primaryStage.setScene(mainScene);
        	primaryStage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
