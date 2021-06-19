package modele.entityManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.entity.Livreur;
import modele.entity.PizzaLivraison;
import modele.entity.TaillePizza;

public class PizzaLivraisonManager implements EntityManager<PizzaLivraison> {

	private static String TABLE_NAME = "Comporter";
	private static String ID_COLUMN = "id_livraison";

	private Connection connection;

	public PizzaLivraisonManager(Connection c) {
		this.connection = c;
	}

	@Override
	public PizzaLivraison processLine(ResultSet rs) {
		PizzaLivraison pizzaLivraison = new PizzaLivraison();
		PizzaManager pm = new PizzaManager(connection);
		LivraisonManager lm = new LivraisonManager(connection);

		try {
			pizzaLivraison.setLivraison(lm.getOneById(rs.getInt(ID_COLUMN)));
			pizzaLivraison.setPizza(pm.getOneById(rs.getInt("id_pizza")));
			pizzaLivraison.setQuantite(rs.getInt("quantite"));
			pizzaLivraison.setTaille(TaillePizza.valueOf(rs.getString("taille_pizza")));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return pizzaLivraison;
	}

	@Override
	public List<PizzaLivraison> getAll() {
		List<PizzaLivraison> pizzasLivraisons = new ArrayList<>();

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
				pizzasLivraisons.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return pizzasLivraisons;
	}

	@Override
	public PizzaLivraison getOneById(int id) {
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
	@Deprecated
	public void deleteOneById(int id) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=" + id + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOneByIds(int idPizza, int idLivraison) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + "=" + idLivraison
					+ " AND id_pizza = " + idPizza + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOne(PizzaLivraison entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(
					"UPDATE " + TABLE_NAME + " SET " + "id_livraison = " + entity.getLivraison().getIdLivraison()
							+ ", id_pizza = " + entity.getPizza().getIdPizza() + ", quantite = " + entity.getQuantite()
							+ ", taille_pizza = '" + entity.getTaille().toString() + "' ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOne(PizzaLivraison entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES (" + entity.getPizza().getIdPizza() + ", "
					+ entity.getLivraison().getIdLivraison() + ", " + entity.getQuantite() + ", '"
					+ entity.getTaille().toString() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
