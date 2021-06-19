package modele.entity;

public class PizzaLivraison {
	private Pizza pizza;
	private Livraison livraison;
	private TaillePizza taille;
	private int quantite;
	
	public PizzaLivraison() {
	}
	
	public PizzaLivraison(Pizza pizza, Livraison livraison, TaillePizza taille, int quantite) {
		this.pizza = pizza;
		this.livraison = livraison;
		this.taille = taille;
		this.quantite = quantite;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Livraison getLivraison() {
		return livraison;
	}

	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}

	public TaillePizza getTaille() {
		return taille;
	}

	public void setTaille(TaillePizza taille) {
		this.taille = taille;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livraison == null) ? 0 : livraison.hashCode());
		result = prime * result + ((pizza == null) ? 0 : pizza.hashCode());
		result = prime * result + quantite;
		result = prime * result + ((taille == null) ? 0 : taille.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaLivraison other = (PizzaLivraison) obj;
		if (livraison == null) {
			if (other.livraison != null)
				return false;
		} else if (!livraison.equals(other.livraison))
			return false;
		if (pizza == null) {
			if (other.pizza != null)
				return false;
		} else if (!pizza.equals(other.pizza))
			return false;
		if (quantite != other.quantite)
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PizzaLivraison [pizza=" + pizza + ", livraison=" + livraison + ", taille=" + taille + ", quantite="
				+ quantite + "]";
	}

}
