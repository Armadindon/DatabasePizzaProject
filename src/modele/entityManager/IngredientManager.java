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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addOne(Ingredient entity) {
		try {
			String SQL_Insert = "INSERT INTO " + TABLE_NAME + "(nom_ingredient) VALUES ('" + entity.getNomIngredient() + "');";
			PreparedStatement stmt = connection.prepareStatement(SQL_Insert, Statement.RETURN_GENERATED_KEYS);
			stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if(generatedKeys.next()) {
				entity.setIdIngredient(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
