package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.entity.Livraison;
import modele.entity.Pizza;
import modele.entity.PizzaLivraison;
import modele.entity.TaillePizza;
import modele.entityManager.LivraisonManager;
import modele.entityManager.PizzaLivraisonManager;

public class DeliveriesController extends MainController {

	@FXML
	private AnchorPane ap_deliveries;

	@FXML
	private TableView<Livraison> tv_Deliveries;

	@FXML
	private TableColumn<Livraison, String> tvColumn_livraison;

	@FXML
	private TableColumn<Livraison, String> tvColumn_dateCommande;

	@FXML
	private TableColumn<Livraison, String> tvColumn_dateLivraison;

	@FXML
	private TableView<PizzaLivraison> tv_pizzaCommand;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizza_number;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizza_name;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizza_length;

	@FXML
	private TableColumn<PizzaLivraison, String> tvColumn_pizza_price;

	@FXML
	private Button bt_addLivraison;

	@FXML
	private Button bt_removeLivraison;

	private Connection c;
	private ObservableList<Livraison> listDeliveries;
	private ObservableList<Pizza> listPizzas;

	private LivraisonManager lm;
	private Livraison l;
	private PizzaLivraisonManager plm;

	public DeliveriesController() {
		c = ApplicationManager.getInstance().getDatabaseConnection();
	}

	public void initialize() {
		lm = new LivraisonManager(c);

		listDeliveries = FXCollections.observableList(lm.getAll());
		tv_Deliveries.setItems(listDeliveries);

		tvColumn_livraison.setCellValueFactory(new PropertyValueFactory<>("idLivraison"));
		tvColumn_dateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
		tvColumn_dateLivraison.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));

		tvColumn_pizza_name.setCellValueFactory(
				(cellData) -> new SimpleStringProperty(cellData.getValue().getPizza().getNomPizza()));
		tvColumn_pizza_price.setCellValueFactory((cellData) -> new SimpleStringProperty(
				(cellData.getValue().getQuantite() * applyPriceChangeByPizzaWidth(cellData.getValue().getTaille(),
						cellData.getValue().getPizza().getPrixPizza())) + ""));

		tvColumn_pizza_number
				.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getQuantite() + ""));
		tvColumn_pizza_length
				.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getTaille() + ""));
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

	@FXML
	void addDelivery(ActionEvent event) {
		sendData(event, "Menu");
	}

	@FXML
	void removeDelivery(ActionEvent event) {
		if (l != null) {
			listDeliveries.remove(l);
			lm.deleteOneById(l.getIdLivraison());
		}
	}

	@FXML
	void getSpecificLivraison(MouseEvent event) {
		l = tv_Deliveries.getSelectionModel().getSelectedItem();

		// TODO: � revoir pour la gestion de s�lection de la livraison
		plm = new PizzaLivraisonManager(c);
		List<PizzaLivraison> pls = plm.getAll().stream()
				.filter((item) -> item.getLivraison().getIdLivraison() == l.getIdLivraison())
				.collect(Collectors.toList());
		
		tv_pizzaCommand.setItems(FXCollections.observableList(pls));
	}

}
