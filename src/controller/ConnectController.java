package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConnectController {

	private Connection connection;
	
    @FXML
    private AnchorPane ap_connect;

    @FXML
    private TextField tf_bdd;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_login;

    @FXML
    private Button bt_connect;

    /***
     * Fonction affili�e au bouton de connexion
     * @param event
     */
    @FXML
    void validateConnection(ActionEvent event) {
    	databaseConnection("org.mariadb.jdbc.Driver", tf_bdd.getText(), tf_password.getText(), tf_login.getText());
    
    	sendData(event);
    }
    
    @FXML
    private void sendData(ActionEvent event) {
	      Node node = (Node) event.getSource();
	      Stage stage = (Stage) node.getScene().getWindow();
	      stage.close();
	      
	      try {
		        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Menu.fxml"));
		        
		        stage.setUserData(connection);
		        Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
	      } catch (IOException e) {
	    	  System.err.println(String.format("Error: %s", e.getMessage()));
	      }
    }
    
    public void databaseConnection(String driver, String url, String user, String pwd) {
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de trouver le driver");
            e.printStackTrace();
        } catch (SQLException exp) {
        	System.out.println("SQLException: " + exp.getMessage());
            System.out.println("SQLState: " + exp.getSQLState());
            System.out.println("VendorError: " + exp.getErrorCode());
        }
	}

}
