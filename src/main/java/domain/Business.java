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
	
	public Business(String pEmail,String pPassword) {
		super(pEmail,pPassword);
		setDrivers(new ArrayList<Driver>());
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
		drivers.remove(d);
	}
	
}
