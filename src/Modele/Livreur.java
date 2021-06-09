package Modele;

import java.util.Objects;

public class Livreur {

	private int idLivreur;
	private String nomLivreur;
	private String prenomLivreur;
	private Adresse adresse;

	public Livreur(int idLivreur, String nomLivreur, String prenomLivreur, Adresse adresse) {
		super();
		this.idLivreur = idLivreur;
		this.nomLivreur = nomLivreur;
		this.prenomLivreur = prenomLivreur;
		this.adresse = adresse;
	}

	public int getIdLivreur() {
		return idLivreur;
	}

	public void setIdLivreur(int idLivreur) {
		this.idLivreur = idLivreur;
	}

	public String getNomLivreur() {
		return nomLivreur;
	}

	public void setNomLivreur(String nomLivreur) {
		this.nomLivreur = nomLivreur;
	}

	public String getPrenomLivreur() {
		return prenomLivreur;
	}

	public void setPrenomLivreur(String prenomLivreur) {
		this.prenomLivreur = prenomLivreur;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, idLivreur, nomLivreur, prenomLivreur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Livreur))
			return false;
		Livreur other = (Livreur) obj;
		return Objects.equals(adresse, other.adresse) && idLivreur == other.idLivreur
				&& Objects.equals(nomLivreur, other.nomLivreur) && Objects.equals(prenomLivreur, other.prenomLivreur);
	}

	@Override
	public String toString() {
		return "Livreur [idLivreur=" + idLivreur + ", nomLivreur=" + nomLivreur + ", prenomLivreur=" + prenomLivreur
				+ ", adresse=" + adresse + "]";
	}

}
