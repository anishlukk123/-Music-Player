package application;
	

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;



public class Main extends Application {
	
	public void start(Stage primaryStage) {
	try {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("scene1.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public static void main(String[] args) {
	
		launch(args);
	}
}