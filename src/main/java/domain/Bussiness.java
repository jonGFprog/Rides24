package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Bussiness extends UsuarioRegistrado {
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Driver> drivers;
	
	public Bussiness(String pEmail,String pPassword) {
		super(pEmail,pPassword);
		drivers= new ArrayList<Driver>();
	}
	
	public void addDriver(Driver d) {
		drivers.add(d);
	}
}
