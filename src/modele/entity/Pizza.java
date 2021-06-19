package modele.entity;

import java.util.List;
import java.util.Objects;

public class Pizza {

	private int idPizza;
	private String nomPizza;
	private double prixPizza;
	private List<Ingredient> ingredients;

	public Pizza(int idPizza, String nomPizza, double prixPizza, List<Ingredient> ingredients) {
		super();
		this.idPizza = idPizza;
		this.nomPizza = nomPizza;
		this.prixPizza = prixPizza;
		this.ingredients = ingredients;
	}

	public Pizza() {
		super();
	}

	public int getIdPizza() {
		return idPizza;
	}

	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public String getNomPizza() {
		return nomPizza;
	}

	public void setNomPizza(String nomPizza) {
		this.nomPizza = nomPizza;
	}

	public double getPrixPizza() {
		return prixPizza;
	}

	public void setPrixPizza(double prixPizza) {
		this.prixPizza = prixPizza;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPizza, ingredients, nomPizza, prixPizza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pizza))
			return false;
		Pizza other = (Pizza) obj;
		return  idPizza == other.idPizza
				&& Objects.equals(ingredients, other.ingredients) && Objects.equals(nomPizza, other.nomPizza)
				&& Double.doubleToLongBits(prixPizza) == Double.doubleToLongBits(other.prixPizza);
	}

	@Override
	public String toString() {
		return "Pizza [idPizza=" + idPizza + ", nomPizza=" + nomPizza + ", prixPizza=" + prixPizza + ", ingredients="
				+ ingredients + "]";
	}

}
