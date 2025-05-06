package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Solicitud {
	
	@Id
    @GeneratedValue
    private Long id;
	private Pasajero pasajero;
	private Ride ride;
	private String estado;
	
	public Solicitud(Pasajero pPasajero, Ride pRide) {
	
		pasajero=pPasajero;
		ride=pRide;
		estado="Pendiente";
	}
	
	public Long getID() {
		return id;
	}
	
	public void setEstado(String e) {
		if(e.equals("Pendiente")||e.equals("Aceptado")||e.equals("Rechazado")||e.equals("Pagado")) {
			this.estado= e;
		}else {
			System.out.print(e+ "  no esta permitido");
		}
		
	}
	
	public String getEstado() {
		return this.estado;
	}
	
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero p) {
		pasajero= p;
	}
	
	public Ride getRide() {
		return ride;
	}
	
	public String RequestedRideToString() {
		String res= null;
		res = this.getRide().toString();
		
		return res;
	}
	
	public void setRide(Ride r) {
		this.ride=r;
	}
	
	public String toString() {
		String res= null;
		res= this.pasajero.getEmail();
		return res;
	}
	
	public Boolean itsSame(Solicitud s) {
		System.out.println(this.ride.itsSame(s.ride)&& this.pasajero.itsSame(s.pasajero));
		
		return this.ride.itsSame(s.ride)&& this.pasajero.itsSame(s.pasajero);
	}
	
	public String RequestedRideToStringPlusState() {
		String res= null;
		res = "Mail: " + pasajero.getEmail()+this.RequestedRideToString() + "|" + this.estado + "|";
		
		return res;
	}
	
	public String RequestToStringPlusState() {
		String res= null;
		res = this.pasajero.getEmail() + "|" + this.estado + "|";
		
		return res;
	}
	
	public Boolean isAceptado() {
		return estado.equals("Aceptado");
	}
}
