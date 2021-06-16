package controller;

import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConnectController {
	
	private Connection connection;

    @FXML
    private AnchorPane ap_connect;

    @FXML
    private TextField tf_login;

    @FXML
    private TextField tf_password;

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

}
