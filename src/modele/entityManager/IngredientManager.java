package modele.entityManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modele.entity.Adresse;
import modele.entity.Client;
import modele.entity.Ingredient;
import modele.entity.Pizza;

public class IngredientManager implements EntityManager<Ingredient> {

	private static String TABLE_NAME = "Ingredient";
	private static String ID_COLUMN = "id_ingredient";

	private Connection connection;

	public IngredientManager(Connection c) {
		this.connection = c;
	}

	@Override
	public Ingredient processLine(ResultSet rs) {
		Ingredient ingredient = new Ingredient();
		// TODO: Ajouter les pizza utilisant les ingredients

		try {
			ingredient.setIdIngredient(rs.getInt(ID_COLUMN));
			ingredient.setNomIngredient(rs.getString("nom_ingredient"));
		
			List<Pizza> pizzas = new ArrayList<>();
			PizzaManager pm = new PizzaManager(connection);
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery(
					"SELECT id_pizza FROM Garnir WHERE " + ID_COLUMN + " = " + ingredient.getIdIngredient() + ";");

			while (results.next()) {
				pizzas.add(pm.getOneById(results.getInt(0)));
			}
			ingredient.setWhereUsed(pizzas);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return ingredient;
	}

	@Override
	public List<Ingredient> getAll() {
		List<Ingredient> ingredients = new ArrayList<>();

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
				ingredients.add(processLine(results));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return ingredients;
	}

	@Override
	public Ingredient getOneById(int id) {
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
	public void updateOne(Ingredient entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE " + TABLE_NAME + " SET " 
			+ "nom_ingredient ='" + entity.getNomIngredient()
			+ "' WHERE " + ID_COLUMN + "=" + entity.getIdIngredient()
			+ ";");
			
			List<Pizza> pizzas = new ArrayList<>();
			PizzaManager pm = new PizzaManager(connection);
			ResultSet results = stmt.executeQuery(
					"SELECT id_pizza FROM Garnir WHERE " + ID_COLUMN + " = " + entity.getIdIngredient() + ";");

			while (results.next()) {
				pizzas.add(pm.getOneById(results.getInt(0)));
			}

			// On supprime tous les éléments qui ne sont pas dans entity mais qui sont en
			// bdd
			List<Pizza> toDelete = entity.getWhereUsed().stream()
					.filter((Pizza pizza) -> !pizzas.contains(pizza)).collect(Collectors.toList());
			
			for(Pizza pizza : toDelete) {
				stmt.executeUpdate("DELETE FROM Garnir WHERE "+ID_COLUMN+" = "+entity.getIdIngredient()+" AND id_pizza = " + pizza.getIdPizza()+";");
			}
			
			// On supprime tous les éléments qui sont dans entity mais qui sont pas en bdd
			List<Pizza> toAdd = entity.getWhereUsed().stream()
					.filter((Pizza pizza) -> pizzas.contains(pizza)).collect(Collectors.toList());
			
			for(Pizza pizza : toAdd) {
				stmt.executeUpdate("INSERT INTO Garnir VALUES ("+pizza.getIdPizza()+", " + entity.getIdIngredient()+");");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addOne(Ingredient entity) {
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO " + TABLE_NAME + " VALUES ('" + entity.getNomIngredient() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
