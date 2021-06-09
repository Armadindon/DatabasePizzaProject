package modele.entity;

import java.util.Objects;

public class Client {
	private int idClient;
	private String nomClient;
	private String prenomClient;
	private double soldeClient;
	private Adresse adresse;

	public Client(int idClient, String nomClient, String prenomClient, double soldeClient) {
		super();
		this.idClient = idClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.soldeClient = soldeClient;
	}

	public Client() {
		super();
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public double getSoldeClient() {
		return soldeClient;
	}

	public void setSoldeClient(double soldeClient) {
		this.soldeClient = soldeClient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, idClient, nomClient, prenomClient, soldeClient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		return Objects.equals(adresse, other.adresse) && idClient == other.idClient
				&& Objects.equals(nomClient, other.nomClient) && Objects.equals(prenomClient, other.prenomClient)
				&& Double.doubleToLongBits(soldeClient) == Double.doubleToLongBits(other.soldeClient);
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient
				+ ", soldeClient=" + soldeClient + ", adresse=" + adresse + "]";
	}

}
