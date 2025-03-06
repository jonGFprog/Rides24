package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

@Entity
public class Pasajero {
	@XmlID
	@Id 
	private String email;
	private String password;
	@OneToOne(cascade=CascadeType.PERSIST)
	ArrayList<Solicitud> solicitudes;
	
	public Pasajero(String pEmail,String pPassword) {
		email=pEmail;
		password=pPassword;
		solicitudes= new ArrayList<Solicitud>();
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	
	public ArrayList<Solicitud> getSolicitudes(){
		return solicitudes;
	}
	
	public void setSolicitudes(ArrayList<Solicitud> pSolicitudes) {
		solicitudes=pSolicitudes;
	}
	
	public void addSolicitud(Solicitud pSolicitud) {
		solicitudes.add(pSolicitud);
	}
}
