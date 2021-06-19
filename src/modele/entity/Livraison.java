package modele.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Livraison {

	private int idLivraison;
	private Date dateCommande;
	private Date dateLivraison;
	private Client client;
	private Livreur livreur;
	private Vehicule vehicule;
	private Adresse adresse;

	public Livraison(int idLivraison, Date dateCommande, Date dateLivraison, Client client, Livreur livreur,
			Vehicule vehicule, Adresse adresse) {
		super();
		this.idLivraison = idLivraison;
		this.dateCommande = dateCommande;
		this.dateLivraison = dateLivraison;
		this.client = client;
		this.livreur = livreur;
		this.vehicule = vehicule;
		this.adresse = adresse;
	}

	public Livraison() {
		super();
	}

	public int getIdLivraison() {
		return idLivraison;
	}

	public void setIdLivraison(int idLivraison) {
		this.idLivraison = idLivraison;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, client, dateCommande, dateLivraison, idLivraison, livreur, vehicule);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Livraison))
			return false;
		Livraison other = (Livraison) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(client, other.client)
				&& Objects.equals(dateCommande, other.dateCommande)
				&& Objects.equals(dateLivraison, other.dateLivraison) && idLivraison == other.idLivraison
				&& Objects.equals(livreur, other.livreur) && Objects.equals(vehicule, other.vehicule);
	}

	@Override
	public String toString() {
		return "Livraison [idLivraison=" + idLivraison + ", dateCommande=" + dateCommande + ", dateLivraison="
				+ dateLivraison + ", client=" + client + ", livreur=" + livreur + ", vehicule=" + vehicule
				+ ", adresse=" + adresse + "]";
	}

}
