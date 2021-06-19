package controller;

import java.sql.Connection;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modele.entity.Pizza;
import modele.entityManager.PizzaManager;

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
    private TableView<Pizza> tv_pizzaCatalogue;

    @FXML
    private TableColumn<Pizza, String> tvColumn_pizzaList_name;

    @FXML
    private TableColumn<Pizza, String> tvColumn_pizzaList_price;

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
    
    public void initialize() {
		PizzaManager pm = new PizzaManager(c);
		List<Pizza> pizzas = pm.getAll();
		
		
		tv_pizzaCatalogue.setItems(FXCollections.observableList(pizzas));
		tvColumn_pizzaList_name.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
		tvColumn_pizzaList_price.setCellValueFactory(new PropertyValueFactory<>("prixPizza"));
    }


}
