package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import modele.entity.Ingredient;
import modele.entity.Pizza;
import modele.entity.PizzaLivraison;
import modele.entity.TaillePizza;
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
	private TableView<PizzaLivraison> tv_pizzaCommand;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizzaCommand_number;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizzaCommand_name;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizzaCommand_length;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizzaCommand_price;

	@FXML
	private TableView<Ingredient> tv_pizzaIngredients;

	@FXML
	private TableColumn<Ingredient, String> tvColumn_ingredients;

	private ObservableList<Pizza> selectedPizzas;
	private ObservableList<PizzaLivraison> pizzasLivraisons;

	public MenuController() {
		c = ApplicationManager.getInstance().getDatabaseConnection();

		selectedPizzas = FXCollections.observableList(new ArrayList<>());
		pizzasLivraisons = FXCollections.observableList(new ArrayList<>());
	}

	@FXML
	public void clickPizza(MouseEvent event) {
		bt_addPizza.setDisable(false);
		Pizza p = tv_pizzaCatalogue.getSelectionModel().getSelectedItem();

		tv_pizzaIngredients.setItems(FXCollections.observableList(p.getIngredients()));
	}

	@FXML
	void addPizza(ActionEvent event) {
		Pizza p = tv_pizzaCatalogue.getSelectionModel().getSelectedItem();

		ChoiceDialog<TaillePizza> choiceTaille = new ChoiceDialog<>(TaillePizza.HUMAINE, TaillePizza.NAINE,
				TaillePizza.OGRESSE);
		choiceTaille.setHeaderText("Veuillez choisir une taille de pizza");
		choiceTaille.setTitle("Taille de la pizza");
		TaillePizza taille = choiceTaille.showAndWait().orElse(TaillePizza.HUMAINE);

		PizzaLivraison pl = new PizzaLivraison();
		pl.setPizza(p);
		pl.setTaille(taille);

		if (!pizzasLivraisons.contains(pl)) { // On vérifie que cette taille n'est pas déjà présente
			pizzasLivraisons.add(pl);
			selectedPizzas.add(p);
			t_totalPrice.setText(computeTotalPrice() + " €");
		} else {
			// TODO: Gestion du nombre de pizzas
		}
	}

	@FXML
	void removePizza(ActionEvent event) {
		// On determine quelle pizza a été supprimée
		PizzaLivraison pl = tv_pizzaCommand.getSelectionModel().getSelectedItem();

		selectedPizzas.remove(pl.getPizza());
		pizzasLivraisons.remove(pl);
		if (selectedPizzas.isEmpty())
			bt_removePizza.setDisable(true);
	}

	@FXML
	void selectedPizzaClicked(MouseEvent event) {
		bt_removePizza.setDisable(false);
	}

	public void initialize() {
		// On désactive les boutons pour éviter les clics sans avoir rien selectionné
		bt_addPizza.setDisable(true);
		bt_removePizza.setDisable(true);

		// On get les pizzas de la bdd
		PizzaManager pm = new PizzaManager(c);
		List<Pizza> pizzas = pm.getAll();
		tv_pizzaCatalogue.setItems(FXCollections.observableList(pizzas));

		// On set les CellValuesFactories
		tvColumn_pizzaList_name.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
		tvColumn_pizzaList_price.setCellValueFactory(new PropertyValueFactory<>("prixPizza"));
		tvColumn_ingredients.setCellValueFactory(new PropertyValueFactory<>("nomIngredient"));

		tvColumn_pizzaCommand_name.setCellValueFactory(
				(cellData) -> new SimpleStringProperty(cellData.getValue().getPizza().getNomPizza()));
		tvColumn_pizzaCommand_price.setCellValueFactory(
				(cellData) -> new SimpleStringProperty(applyPriceChangeByPizzaWidth(cellData.getValue().getTaille(),
						cellData.getValue().getPizza().getPrixPizza()) + ""));
		// TODO: Gérer le nombre de pizzas une fois que la modif en bdd sera effectuée
		tvColumn_pizzaCommand_number.setCellValueFactory((cellData) -> new SimpleStringProperty(1 + ""));
		tvColumn_pizzaCommand_length.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getTaille() + ""));

		// On met la liste des pizzas séléctionnées en place
		tv_pizzaCommand.setItems(pizzasLivraisons);
	}

	public double applyPriceChangeByPizzaWidth(TaillePizza pizza, double basePrice) {
		switch (pizza) {
		case HUMAINE:
			return basePrice;
		case NAINE:
			return basePrice * 0.75;
		case OGRESSE:
			return basePrice * 1.25;
		}
		return 0;
	}

	public double computeTotalPrice() {
		double sum = 0;
		for (Pizza p : selectedPizzas) {
			Optional<PizzaLivraison> pl = getAssociatedPizzaLivraison(p);
			if (pl.isPresent())
				sum += applyPriceChangeByPizzaWidth(getAssociatedPizzaLivraison(p).get().getTaille(), p.getPrixPizza());
		}
		return sum;
	}

	public Optional<PizzaLivraison> getAssociatedPizzaLivraison(Pizza p) {
		return pizzasLivraisons.stream().filter((pl) -> pl.getPizza().getIdPizza() == p.getIdPizza()).findFirst();
	}

	public Optional<Pizza> getAssociatedPizza(PizzaLivraison pl) {
		return selectedPizzas.stream().filter((p) -> pl.getPizza().getIdPizza() == p.getIdPizza()).findFirst();
	}

}
