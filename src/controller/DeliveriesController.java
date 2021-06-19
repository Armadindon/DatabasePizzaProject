package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DeliveriesController {

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

    @FXML
    void addDelivery(ActionEvent event) {

    }

    @FXML
    void removeDelivery(ActionEvent event) {

    }

}
