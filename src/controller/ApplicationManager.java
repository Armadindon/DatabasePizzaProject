package controller;

import java.sql.Connection;

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
	

}
