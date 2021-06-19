package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DeliveriesController extends MainController {

    @FXML
    private AnchorPane ap_deliveries;

    @FXML
    private TableView<?> tv_pizzaTab;

    @FXML
    private TableColumn<?, ?> tvColumn_id;

    @FXML
    private TableColumn<?, ?> tvColumn_livraison;

    @FXML
    private TableColumn<?, ?> tvColumn_dateCommande;

    @FXML
    private TableColumn<?, ?> tvColumn_dateLivraison;

    @FXML
    private Button bt_addPizza;

    @FXML
    private Button bt_removePizza;
    
    public DeliveriesController() {
    	super("Menu");
    }

    @FXML
    void addDelivery(ActionEvent event) {
    	sendData(event);
    }

    @FXML
    void removeDelivery(ActionEvent event) {

    }

}
