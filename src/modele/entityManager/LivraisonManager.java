package modele.entityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modele.entity.Adresse;
import modele.entity.Ingredient;
import modele.entity.Livraison;
import modele.entity.Pizza;

public class LivraisonManager implements EntityManager<Livraison> {

	private static String TABLE_NAME = "Livraison";
	private static String ID_COLUMN = "id_livraison";

	private Connection connection;

	public LivraisonManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Livraison processLine(ResultSet rs) {
		Livraison livraison = new Livraison();

		try {
			livraison.setIdLivraison(rs.getInt(ID_COLUMN));
			livraison.setDateCommande(rs.getDate("dateCommande_livraison"));
			livraison.setDateCommande(rs.getDate("dateLivraison_livraison"));

			ClientManager cm = new ClientManager(connection);
			livraison.setClient(cm.getOneById(rs.getInt("id_client")));

			LivreurManager lm = new LivreurManager(connection);
			livraison.setLivreur(lm.getOneById(rs.getInt("id_livreur")));

			VehiculeManager vm = new VehiculeManager(connection);
			livraison.setVehicule(vm.getOneById(rs.getInt("id_vehicule")));

			AdresseManager am = new AdresseManager(connection);
			livraison.setAdresse(am.getOneById(rs.getInt("id_adresse")));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return livraison;
	}

	@Override
	public List<Livraison> getAll() {
		List<Livraison> livraisons = new ArrayList<>();

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
				livraisons.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return livraisons;
	}

	@Override
	public Livraison getOneById(int id) {
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
			stmt.executeUpdate("DELETE FROM Comporter WHERE " + ID_COLUMN + "=" + id + ";");
			stmt.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=" + id + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateOne(Livraison entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "dateCommande_livraison ='"
					+ entity.getDateCommande().toString() + "', dateLivraison_livraison = " + entity.getDateLivraison()
					+ "', id_client = " + entity.getClient().getIdClient() + "', id_livreur = "
					+ entity.getLivreur().getIdLivreur() + "', id_vehicule = " + entity.getVehicule().getIdVehicule()
					+ "', id_adresse = " + entity.getAdresse().getIdAdresse() + "WHERE " + ID_COLUMN + "="
					+ entity.getIdLivraison() + ";");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOne(Livraison entity) {
		try {
			String SQL_Insert = "INSERT INTO " + TABLE_NAME
					+ "(dateCommande_livraison,id_client,id_livreur,id_vehicule,id_adresse) VALUES ('"
					+ entity.getDateCommande() + "', " + entity.getClient().getIdClient() + ", "
					+ entity.getLivreur().getIdLivreur() + ", " + entity.getVehicule().getIdVehicule() + ", "
					+ entity.getAdresse().getIdAdresse() + ");";

			System.out.println(SQL_Insert);
			PreparedStatement stmt = connection.prepareStatement(SQL_Insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				entity.setIdLivraison(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
