package modele.entity;

import java.util.Objects;

public class Adresse {

	private int idAdresse;
	private String ville;
	private String codePostal;
	private String rue;
	private String numero;

	public Adresse(int idAdresse, String ville, String codePostal, String rue, String numero) {
		this.idAdresse = idAdresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.rue = rue;
		this.numero = numero;
	}

	public Adresse() {
		super();
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codePostal, idAdresse, numero, rue, ville);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Adresse))
			return false;
		Adresse other = (Adresse) obj;
		return Objects.equals(codePostal, other.codePostal) && idAdresse == other.idAdresse
				&& Objects.equals(numero, other.numero) && Objects.equals(rue, other.rue)
				&& Objects.equals(ville, other.ville);
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", ville=" + ville + ", codePostal=" + codePostal + ", rue=" + rue
				+ ", numero=" + numero + "]";
	}

}
