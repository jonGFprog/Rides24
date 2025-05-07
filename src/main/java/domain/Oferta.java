package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Oferta implements Serializable{
	@Id 
	@GeneratedValue
	private Integer ofertaNumber;	
	private Driver driver;
	private Business business;
	private String estado;
	
	public Oferta(Driver pDriver, Business pBusiness, String pEstado) {
		setDriver(pDriver);
		setBusiness(pBusiness);
		setEstado(pEstado);
	}
	

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String e) {
		if(e.equals("Pendiente")||e.equals("Aceptado")||e.equals("Rechazado")) {
			this.estado= e;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta o = (Oferta) obj;	
		return this.getBusiness().getEmail().equals(o.getBusiness().getEmail())&&this.getDriver().getEmail().equals(o.getDriver().getEmail());
	}
	
	public String toString() {
		return "Oferta de: "+ business.getEmail()+ " Estado: "+estado;
	}
}
