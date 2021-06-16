package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    public ConnectController() {
    	
    }
    

    /***
     * Fonction affiliée au bouton de connexion
     * @param event
     */
    @FXML
    void validateConnection(ActionEvent event) {
    	System.out.println(tf_login.getText() + " " + tf_password.getText());
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
