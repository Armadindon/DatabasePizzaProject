package modele.entityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.entity.Adresse;
import modele.entity.Client;

public class ClientManager implements EntityManager<Client> {

	private static String TABLE_NAME = "Client";
	private static String ID_COLUMN = "id_client";

	private Connection connection;

	public ClientManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Client processLine(ResultSet rs) {
		Client client = new Client();
		AdresseManager am = new AdresseManager(connection);

		try {
			client.setIdClient(rs.getInt(ID_COLUMN));
			client.setNomClient(rs.getString("nom_client"));
			client.setPrenomClient(rs.getString("prenom_client"));
			client.setSoldeClient(rs.getDouble("solde_client"));
			client.setAdresse(am.getOneById(rs.getInt("id_adresse")));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return client;
	}

	@Override
	public List<Client> getAll() {
		List<Client> clients = new ArrayList<>();

		ResultSet results;

		try {
			Statement stmt = connection.createStatement();
			results = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		try {
			while (results.next()) {
				clients.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return clients;
	}

	@Override
	public Client getOneById(int id) {
		ResultSet results;

		try {
			Statement stmt = connection.createStatement();
			results = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=" + id + ";");
			results.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return processLine(results);
	}

	@Override
	public void deleteOneById(int id) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=" + id + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOne(Client entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "nom_client ='" + entity.getNomClient()
					+ "', prenom_client ='" + entity.getPrenomClient() + "', solde_client =" + entity.getSoldeClient()
					+ "id_adresse =" + entity.getAdresse().getIdAdresse() + " WHERE " + ID_COLUMN + "="
					+ entity.getIdClient() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addOne(Client entity) {
		try {
			String SQL_Insert = "INSERT INTO " + TABLE_NAME + "(nom_client,prenom_client,solde_client,id_adresse) VALUES ('" + entity.getNomClient() + "', '"
					+ entity.getPrenomClient() + "', " + entity.getSoldeClient() + ", " + entity.getAdresse().getIdAdresse() + ");";
			PreparedStatement stmt = connection.prepareStatement(SQL_Insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				entity.setIdClient(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
