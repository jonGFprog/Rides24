package domain;

import javax.persistence.Entity;

@Entity
public class Solicitud {
	private Pasajero pasajero;
	private Ride ride;
	private String estado;
	
	public Solicitud(Pasajero pPasajero, Ride pRide) {
		pasajero=pPasajero;
		ride=pRide;
		estado="Pendiente";
	}
	
	public void setEstado(String e) {
		if(e.equals("Pendiente")||e.equals("Aceptado")||e.equals("Denegado")) {
			this.estado= e;
		}
		
	}
	
	public Pasajero getPasajero() {
		return pasajero;
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
		Boolean res= false;
		if (this.ride.itsSame(s.ride)&& this.pasajero.itsSame(s.pasajero)&& this.estado.equals(s.estado)) {
			res= true;
		}
		return res;
	}
	
	public String RequestedRideToStringPlusState() {
		String res= null;
		res = this.RequestedRideToString() + "|" + this.estado + "|";
		
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
