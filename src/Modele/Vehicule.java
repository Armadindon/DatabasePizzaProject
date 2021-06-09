package Modele;

import java.util.Objects;

public class Vehicule {

	private int idVehicule;
	private String immatriculationVehicule;
	private TypeVehicule type;

	public Vehicule(int idVehicule, String immatriculationVehicule, TypeVehicule type) {
		super();
		this.idVehicule = idVehicule;
		this.immatriculationVehicule = immatriculationVehicule;
		this.type = type;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}

	public String getImmatriculationVehicule() {
		return immatriculationVehicule;
	}

	public void setImmatriculationVehicule(String immatriculationVehicule) {
		this.immatriculationVehicule = immatriculationVehicule;
	}

	public TypeVehicule getType() {
		return type;
	}

	public void setType(TypeVehicule type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idVehicule, immatriculationVehicule, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehicule))
			return false;
		Vehicule other = (Vehicule) obj;
		return idVehicule == other.idVehicule && Objects.equals(immatriculationVehicule, other.immatriculationVehicule)
				&& type == other.type;
	}

	@Override
	public String toString() {
		return "Vehicule [idVehicule=" + idVehicule + ", immatriculationVehicule=" + immatriculationVehicule + ", type="
				+ type + "]";
	}

}
