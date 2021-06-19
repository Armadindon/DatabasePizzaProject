package modele.entityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modele.entity.Ingredient;
import modele.entity.Livraison;
import modele.entity.Pizza;

public class PizzaManager implements EntityManager<Pizza> {

	private static String TABLE_NAME = "Pizza";
	private static String ID_COLUMN = "id_pizza";

	private Connection connection;

	public PizzaManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Pizza processLine(ResultSet rs) {
		Pizza pizza = new Pizza();
		AdresseManager am = new AdresseManager(connection);

		try {
			pizza.setIdPizza(rs.getInt(ID_COLUMN));
			pizza.setNomPizza(rs.getString("nom_pizza"));
			pizza.setPrixPizza(rs.getDouble("prix_pizza"));

			List<Ingredient> ingredients = new ArrayList<>();
			IngredientManager igm = new IngredientManager(connection);
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(
					"SELECT id_ingredient FROM Garnir WHERE " + ID_COLUMN + " = " + pizza.getIdPizza() + ";");

			while (results.next()) {
				ingredients.add(igm.getOneById(results.getInt(1)));
			}
			pizza.setIngredients(ingredients);

			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return pizza;
	}

	@Override
	public List<Pizza> getAll() {
		List<Pizza> pizzas = new ArrayList<>();

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
				pizzas.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return pizzas;
	}

	@Override
	public Pizza getOneById(int id) {
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
	public void updateOne(Pizza entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " + "nom_pizza ='" + entity.getNomPizza()
					+ "', prix_pizza =" + entity.getPrixPizza()
					+ " WHERE " + ID_COLUMN + "=" + entity.getIdPizza() + ";");

			List<Ingredient> ingredients = new ArrayList<>();
			IngredientManager igm = new IngredientManager(connection);
			ResultSet results = stmt.executeQuery(
					"SELECT id_ingredient FROM Garnir WHERE " + ID_COLUMN + " = " + entity.getIdPizza() + ";");

			while (results.next()) {
				ingredients.add(igm.getOneById(results.getInt(0)));
			}

			// On supprime tous les éléments qui ne sont pas dans entity mais qui sont en
			// bdd
			List<Ingredient> toDelete = entity.getIngredients().stream()
					.filter((Ingredient ing) -> !ingredients.contains(ing)).collect(Collectors.toList());
			
			for(Ingredient ing : toDelete) {
				stmt.executeUpdate("DELETE FROM Garnir WHERE "+ID_COLUMN+" = "+entity.getIdPizza()+" AND id_ingredient = " + ing.getIdIngredient()+";");
			}
			
			// On supprime tous les éléments qui sont dans entity mais qui sont pas en bdd
			List<Ingredient> toAdd = entity.getIngredients().stream()
					.filter((Ingredient ing) -> ingredients.contains(ing)).collect(Collectors.toList());
			
			for(Ingredient ing : toAdd) {
				stmt.executeUpdate("INSERT INTO Garnir VALUES ("+entity.getIdPizza()+", " + ing.getIdIngredient()+");");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addOne(Pizza entity) {
		try {
			String SQL_Insert = "INSERT INTO " + TABLE_NAME + "(nom_pizza, prix_pizza) VALUES ('" + entity.getNomPizza() + "', '"
					+ entity.getPrixPizza() + "');";
			PreparedStatement stmt = connection.prepareStatement(SQL_Insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				entity.setIdPizza(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
