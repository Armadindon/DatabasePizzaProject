package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class InvoiceController {

    @FXML
    private Text t_delivManName;

    @FXML
    private Text t_delivManForename;

    @FXML
    private Text t_vehiculeType;

    @FXML
    private Text t_vehiculeMatri;

    @FXML
    private Text t_clientName;

    @FXML
    private Text t_clientForename;

    @FXML
    private Text t_clientAddress;

    @FXML
    private Text t_dateDelivery;

    @FXML
    private Text t_dateLate;

    @FXML
    private Text t_totalPrice;

    @FXML
    private TableView<?> tv_pizzaTab;

    @FXML
    private TableColumn<?, ?> tvColumn_nbr;

    @FXML
    private TableColumn<?, ?> tvColumn_Pizza;

    @FXML
    private TableColumn<?, ?> tvColumn_length;

    @FXML
    private TableColumn<?, ?> tvColumn_price;

}
