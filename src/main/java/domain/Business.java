package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Business extends UsuarioRegistrado {
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Driver> drivers;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Oferta> ofertas;
	
	public Business(String pEmail,String pPassword) {
		super(pEmail,pPassword);
		setDrivers(new ArrayList<Driver>());
		ofertas=new ArrayList<Oferta>();
	}
	
	public void addDriver(Driver d) {
		getDrivers().add(d);
		d.setHasBussiness(true);
		d.setBussiness(this);
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<Driver> drivers) {
		this.drivers = drivers;
	}
	
	public void removeDriver(Driver d) {
		System.out.println("Driver eliminado de "+getEmail()+": "+drivers.remove(d));
	}
	
	public ArrayList<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public void addOferta(Oferta pOferta) {
		ofertas.add(pOferta);
	}
}
