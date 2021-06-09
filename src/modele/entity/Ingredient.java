package modele.entity;

import java.util.List;
import java.util.Objects;

public class Ingredient {

	private int idIngredient;
	private String nomIngredient;
	private List<Pizza> whereUsed;

	public Ingredient(int idIngredient, String nomIngredient, List<Pizza> whereUsed) {
		super();
		this.idIngredient = idIngredient;
		this.nomIngredient = nomIngredient;
		this.whereUsed = whereUsed;
	}

	public Ingredient() {
		super();
	}

	public int getIdIngredient() {
		return idIngredient;
	}

	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}

	public String getNomIngredient() {
		return nomIngredient;
	}

	public void setNomIngredient(String nomIngredient) {
		this.nomIngredient = nomIngredient;
	}

	public List<Pizza> getWhereUsed() {
		return whereUsed;
	}

	public void setWhereUsed(List<Pizza> whereUsed) {
		this.whereUsed = whereUsed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idIngredient, nomIngredient, whereUsed);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ingredient))
			return false;
		Ingredient other = (Ingredient) obj;
		return idIngredient == other.idIngredient && Objects.equals(nomIngredient, other.nomIngredient)
				&& Objects.equals(whereUsed, other.whereUsed);
	}

	@Override
	public String toString() {
		return "Ingredient [idIngredient=" + idIngredient + ", nomIngredient=" + nomIngredient + ", whereUsed="
				+ whereUsed + "]";
	}

}
