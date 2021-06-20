package controller;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.entity.Livraison;
import modele.entity.PizzaLivraison;
import modele.entity.TaillePizza;

public class InvoiceController extends MainController {

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
    private TableView<PizzaLivraison> tv_pizzaTab;

    @FXML
    private TableColumn<PizzaLivraison, String> tvColumn_nbr;

    @FXML
    private TableColumn<PizzaLivraison, String> tvColumn_Pizza;

    @FXML
    private TableColumn<PizzaLivraison, String> tvColumn_length;

    @FXML
    private TableColumn<PizzaLivraison, String> tvColumn_price;
    
    @FXML
    private Button bt_goback;
    
    private Livraison l;
    private double price;
	private ObservableList<PizzaLivraison> pizzasLivraisons;
    
    public InvoiceController() {
    	l = ApplicationManager.getInstance().getCurrentDelivery();
    	price = ApplicationManager.getInstance().getDeliveryPrice();
    	
		pizzasLivraisons = FXCollections.observableList(new ArrayList<>());
		pizzasLivraisons.setAll(ApplicationManager.getInstance().getDeliveryPizzaList());
    }
    
    public void initialize() {
    	t_delivManName.setText(l.getLivreur().getNomLivreur());
    	t_delivManForename.setText(l.getLivreur().getPrenomLivreur());
    	
    	t_vehiculeType.setText("" + l.getVehicule().getType());
    	t_vehiculeMatri.setText(l.getVehicule().getImmatriculationVehicule());
    	
    	t_clientName.setText(l.getClient().getNomClient());
    	t_clientForename.setText(l.getClient().getPrenomClient());
    	
    	t_clientAddress.setText(l.getAdresse().getRue() + ", " + l.getAdresse().getRue() + "\n" + l.getAdresse().getCodePostal() + " " + l.getAdresse().getVille());
    	
    	t_dateDelivery.setText("" + l.getDateCommande());
    	t_dateLate.setText( (l.getDateLivraison() != null ? l.getDateLivraison() + "" : "-") );
    	
    	t_totalPrice.setText(price + " €");
    	
		// On set les CellValuesFactories
		tvColumn_Pizza.setCellValueFactory(
				(cellData) -> new SimpleStringProperty(cellData.getValue().getPizza().getNomPizza()));
		tvColumn_price.setCellValueFactory((cellData) -> new SimpleStringProperty(
				(cellData.getValue().getQuantite() * applyPriceChangeByPizzaWidth(cellData.getValue().getTaille(),
						cellData.getValue().getPizza().getPrixPizza())) + ""));

		tvColumn_nbr.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getQuantite() + ""));
		tvColumn_length.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().getTaille() + ""));
    	
    	tv_pizzaTab.setItems(pizzasLivraisons);
    }
    
    @FXML
    void goBack(ActionEvent event) {
    	sendData(event, "Deliveries");
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
	
}
