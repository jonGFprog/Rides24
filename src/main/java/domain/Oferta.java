package domain;

import javax.persistence.Entity;

@Entity
public class Oferta {
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
		if(e.equals("Pendiente")||e.equals("Aceptado")||e.equals("Denegado")) {
			this.estado= e;
		}
	}
}
