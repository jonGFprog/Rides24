package domain;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	public void removeSolicitud (Integer index) {
		
		solicitudes.remove(index);
	}
	
	public Solicitud getThisSolicitud(Solicitud s) {
		Solicitud res= null;
		if(this.solicitudes==null) {
			System.out.println("Null list");
		}
		for (int i=0; i<this.solicitudes.size();i++) {
			System.out.println(this.solicitudes.get(i).itsSame(s));
			if (this.solicitudes.get(i).equals(s)) {
				res= this.solicitudes.get(i);
			}
		}
		System.out.println(res);
		return res;
	}
	public Boolean itsSame(Pasajero p) {
	
		return this.getEmail().equals(p.getEmail());
	}
	
	public ArrayList<Solicitud> getSolicitudesAceptadas(){
		ArrayList<Solicitud> result= new ArrayList<Solicitud>();
		for(Solicitud i : solicitudes) {
			if (i.isAceptado()) {
				result.add(i);
			}
		}
		
		return result;
	}
	
}
