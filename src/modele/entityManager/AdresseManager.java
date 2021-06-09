package modele.entityManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.entity.Adresse;

public class AdresseManager implements EntityManager<Adresse> {

	private static String TABLE_NAME = "Adresse";
	private static String ID_COLUMN = "id_adresse";

	private Connection connection;

	public AdresseManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Adresse processLine(ResultSet rs) {
		Adresse adresse = new Adresse();

		try {
			adresse.setIdAdresse(rs.getInt(ID_COLUMN));
			adresse.setVille(rs.getString("ville_adresse"));
			adresse.setCodePostal(rs.getString("codePostal_adresse"));
			adresse.setRue(rs.getString("rue_adresse"));
			adresse.setNumero(rs.getString("numero_adresse"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return adresse;
	}

	@Override
	public List<Adresse> getAll() {

		List<Adresse> adresses = new ArrayList<>();

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
				adresses.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return adresses;
	}

	@Override
	public Adresse getOneById(int id) {
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
	public void updateOne(Adresse entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "ville_adresse =" + entity.getIdAdresse()
					+ "codePostal_adresse =" + entity.getCodePostal() + "rue_adresse =" + entity.getRue()
					+ "numero_adresse =" + entity.getNumero() + " WHERE " + ID_COLUMN + "=" + entity.getIdAdresse()
					+ ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
