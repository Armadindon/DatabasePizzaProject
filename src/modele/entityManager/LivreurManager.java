package modele.entityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.entity.Livraison;
import modele.entity.Livreur;

public class LivreurManager implements EntityManager<Livreur> {

	private static String TABLE_NAME = "Livreur";
	private static String ID_COLUMN = "id_livreur";

	private Connection connection;

	public LivreurManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Livreur processLine(ResultSet rs) {
		Livreur livreur = new Livreur();
		AdresseManager am = new AdresseManager(connection);

		try {
			livreur.setIdLivreur(rs.getInt(ID_COLUMN));
			livreur.setNomLivreur(rs.getString("nom_livreur"));
			livreur.setPrenomLivreur(rs.getString("prenom_livreur"));
			livreur.setAdresse(am.getOneById(rs.getInt("id_adresse")));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return livreur;
	}

	@Override
	public List<Livreur> getAll() {
		List<Livreur> livreurs = new ArrayList<>();

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
				livreurs.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return livreurs;
	}

	@Override
	public Livreur getOneById(int id) {
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
	public void updateOne(Livreur entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "nom_livreur ='" + entity.getNomLivreur()
					+ "', prenom_livreur ='" + entity.getPrenomLivreur() + ", id_adresse ="
					+ entity.getAdresse().getIdAdresse() + " WHERE " + ID_COLUMN + "=" + entity.getIdLivreur() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOne(Livreur entity) {
		try {
			String SQL_Insert = "INSERT INTO " + TABLE_NAME + " VALUES ('" + entity.getNomLivreur() + "', '"
					+ entity.getPrenomLivreur() + "', " + entity.getAdresse().getIdAdresse() + ");";
			PreparedStatement stmt = connection.prepareStatement(SQL_Insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				entity.setIdLivreur(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
