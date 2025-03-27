package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlID;

@Entity
public class Pasajero extends UsuarioRegistrado {
	
	@OneToOne(cascade=CascadeType.PERSIST)
	ArrayList<Solicitud> solicitudes;
	
	public Pasajero(String pEmail,String pPassword) {
		super(pEmail,pPassword);
		solicitudes= new ArrayList<Solicitud>();
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
