package controller;

import java.sql.Connection;
import java.util.List;

import modele.entity.Pizza;
import modele.entity.PizzaLivraison;
import modele.entity.Livraison;

public final class ApplicationManager {
	
	
	/**
	 * Gestion du singleton
	 */
	private ApplicationManager() {}
	
	private static ApplicationManager INSTANCE = null;
	
	public static ApplicationManager getInstance() {
		if(INSTANCE == null) INSTANCE = new ApplicationManager();		
		return INSTANCE;
	}
	
	/**
	 * Données du singleton
	 */
	private Connection databaseConnection;

	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	public void setDatabaseConnection(Connection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}
	
	private Livraison currentDelivery;
	
	public Livraison getCurrentDelivery() {
		return currentDelivery;
	}
	
	public void setCurrentDelivery(Livraison delivery) {
		this.currentDelivery = delivery;
	}
	
	private double deliveryPrice;
	
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	
	public void setDeliveryPrice(double price) {
		this.deliveryPrice = price;
	}
	
	private List<PizzaLivraison> deliveryPizzaList;
	
	public List<PizzaLivraison> getDeliveryPizzaList() {
		return deliveryPizzaList;
	}

	public void setDeliveryPizzaList(List<PizzaLivraison> list) {
		this.deliveryPizzaList = list;
	}
}
