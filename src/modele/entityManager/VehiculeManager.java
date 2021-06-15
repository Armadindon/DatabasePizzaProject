package modele.entityManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.entity.Client;
import modele.entity.Pizza;
import modele.entity.TypeVehicule;
import modele.entity.Vehicule;

public class VehiculeManager implements EntityManager<Vehicule> {

	private static String TABLE_NAME = "Vehicule";
	private static String ID_COLUMN = "id_vehicule";

	private Connection connection;

	public VehiculeManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Vehicule processLine(ResultSet rs) {
		Vehicule vehicule = new Vehicule();
		AdresseManager am = new AdresseManager(connection);

		try {
			vehicule.setIdVehicule(rs.getInt(ID_COLUMN));
			vehicule.setImmatriculationVehicule(rs.getString("immatricule_vehicule"));
			vehicule.setType(TypeVehicule.valueOf(rs.getString("type_vehicule")));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return vehicule;
	}

	@Override
	public List<Vehicule> getAll() {
		List<Vehicule> vehicules = new ArrayList<>();

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
				vehicules.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return vehicules;
	}

	@Override
	public Vehicule getOneById(int id) {
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
	public void updateOne(Vehicule entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "immatricule_vehicule ='"
					+ entity.getImmatriculationVehicule() + "', type_vehicule ='" + entity.getType().toString()
					+ "' WHERE " + ID_COLUMN + "=" + entity.getIdVehicule() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOne(Vehicule entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES ('" + entity.getImmatriculationVehicule() + "', '"
					+ entity.getType() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
