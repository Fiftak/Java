package gui;

import ctlr.CTLMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIMain  extends Application {
	public static void main(String[] args) {
		Application.launch(args);
		
	} 

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/resources/fx_main.fxml"));
			Parent root = myLoader.load();
			CTLMain controller = myLoader.getController();
			controller.setStage(primaryStage);
			controller.setTitle();
	        Scene scene = new Scene(root);
	        primaryStage.setResizable(false);
	        primaryStage.setScene(scene);
	        primaryStage.show(); 
		
	}
	 
}
