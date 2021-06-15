package modele.entityManager;

import java.sql.Connection;
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

			List<Pizza> pizzas = new ArrayList<>();
			PizzaManager pm = new PizzaManager(connection);
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(
					"SELECT id_pizza FROM Comporter WHERE " + ID_COLUMN + " = " + livraison.getIdLivraison() + ";");

			while (results.next()) {
				pizzas.add(pm.getOneById(results.getInt(0)));
			}
			livraison.setPizzas(pizzas);
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

			List<Pizza> pizzas = new ArrayList<>();
			PizzaManager pm = new PizzaManager(connection);
			ResultSet results = stmt.executeQuery(
					"SELECT id_livraison FROM Comporter WHERE " + ID_COLUMN + " = " + entity.getIdLivraison() + ";");

			while (results.next()) {
				pizzas.add(pm.getOneById(results.getInt(0)));
			}

			// On supprime tous les éléments qui ne sont pas dans entity mais qui sont en
			// bdd
			List<Pizza> toDelete = entity.getPizzas().stream().filter((Pizza pizza) -> !pizzas.contains(pizza))
					.collect(Collectors.toList());

			for (Pizza pizza : toDelete) {
				stmt.executeUpdate("DELETE FROM Comporter WHERE " + ID_COLUMN + " = " + entity.getIdLivraison()
						+ " AND id_pizza = " + pizza.getIdPizza() + ";");
			}

			// On supprime tous les éléments qui sont dans entity mais qui sont pas en bdd
			List<Pizza> toAdd = entity.getPizzas().stream().filter((Pizza pizza) -> pizzas.contains(pizza))
					.collect(Collectors.toList());

			for (Pizza pizza : toAdd) {
				stmt.executeUpdate(
						"INSERT INTO Comporter VALUES (" + pizza.getIdPizza() + ", " + entity.getIdLivraison() + ");");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOne(Livraison entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES (" + entity.getDateCommande().toString() + ", "
					+ entity.getDateLivraison().toString() + ", " + entity.getClient().getIdClient() + ", "
					+ entity.getLivreur().getIdLivreur() + ", " + entity.getVehicule().getIdVehicule() + ", "
					+ entity.getAdresse().getIdAdresse() + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
