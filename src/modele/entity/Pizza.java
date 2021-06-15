package modele.entity;

import java.util.List;
import java.util.Objects;

public class Pizza {

	private int idPizza;
	private String nomPizza;
	private double prixPizza;
	private TaillePizza taillePizza;
	private List<Ingredient> ingredients;
	private List<Livraison> commandes;

	public Pizza(int idPizza, String nomPizza, double prixPizza, TaillePizza taillePizza, List<Ingredient> ingredients,
			List<Livraison> commandes) {
		super();
		this.idPizza = idPizza;
		this.nomPizza = nomPizza;
		this.prixPizza = prixPizza;
		this.taillePizza = taillePizza;
		this.ingredients = ingredients;
		this.commandes = commandes;
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

	public TaillePizza getTaillePizza() {
		return taillePizza;
	}

	public void setTaillePizza(TaillePizza taillePizza) {
		this.taillePizza = taillePizza;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Livraison> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Livraison> commandes) {
		this.commandes = commandes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commandes, idPizza, ingredients, nomPizza, prixPizza, taillePizza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pizza))
			return false;
		Pizza other = (Pizza) obj;
		return Objects.equals(commandes, other.commandes) && idPizza == other.idPizza
				&& Objects.equals(ingredients, other.ingredients) && Objects.equals(nomPizza, other.nomPizza)
				&& Double.doubleToLongBits(prixPizza) == Double.doubleToLongBits(other.prixPizza)
				&& taillePizza == other.taillePizza;
	}

	@Override
	public String toString() {
		return "Pizza [idPizza=" + idPizza + ", nomPizza=" + nomPizza + ", prixPizza=" + prixPizza + ", taillePizza="
				+ taillePizza + ", ingredients=" + ingredients + ", commandes=" + commandes + "]";
	}

}
