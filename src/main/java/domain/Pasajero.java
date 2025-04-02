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
	
	public Solicitud getThisSolicitud(Solicitud s) {
		Solicitud res= null;
		if(this.solicitudes==null) {
			System.out.println("Null list");
		}
		for (int i=0; i<this.solicitudes.size();i++) {
			System.out.println(this.solicitudes.get(i).itsSame(s));
			if (this.solicitudes.get(i).itsSame(s)) {
				res= this.solicitudes.get(i);
			}
		}
		System.out.println(res);
		return res;
	}
	public Boolean itsSame(Pasajero p) {
		Boolean res= false;
		if(this.getEmail().equals(p.getEmail())) {
			res= true;
		}
		return res;
	}
	
}
