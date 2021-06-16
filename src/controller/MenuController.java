package controller;

import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MenuController {
	
	private Connection c;

    @FXML
    private AnchorPane ap_menu;

    @FXML
    private Button bt_validateCommand;

    @FXML
    private Button bt_addPizza;

    @FXML
    private Button bt_removePizza;

    @FXML
    private Text t_totalPrice;

    @FXML
    private TableView<?> tv_pizzaCatalogue;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaList_name;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaList_price;

    @FXML
    private TableView<?> tv_pizzaCommand;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaCommand_number;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaCommand_name;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaCommand_length;

    @FXML
    private TableColumn<?, ?> tvColumn_pizzaCommand_price;

    @FXML
    private TableView<?> tv_pizzaIngredients;

    @FXML
    private TableColumn<?, ?> tvColumn_ingredients;
    
    public MenuController() {
		c = ApplicationManager.getInstance().getDatabaseConnection();
	}

}
