package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class MainController {

	
	public MainController() {}
	
    protected void sendData(ActionEvent event, String place) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.close();
		  
		try {
		      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/" + place + ".fxml"));
		        
		      Scene scene = new Scene(root);
		      stage.setScene(scene);
		      stage.show();
		} catch (IOException e) {
			System.err.println(String.format("Error: %s", e.getMessage()));
		}
	}
}
