package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;

@Entity
public class Pasajero {
	@XmlID
	@Id 
	private String email;
	private ArrayList<Solicitud> solicitudes=null;
	
	public Pasajero(String pEmail) {
		email=pEmail;
		solicitudes= new ArrayList<Solicitud>();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
