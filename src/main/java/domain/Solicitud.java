package domain;

import javax.persistence.Entity;

@Entity
public class Solicitud {
	private Pasajero pasajero;
	private Ride ride;
	
	public Solicitud(Pasajero pPasajero, Ride pRide) {
		pasajero=pPasajero;
		ride=pRide;
	}
	
	public Pasajero getPasajero() {
		return pasajero;
	}
	
	public Ride getRide() {
		return ride;
	}
	
	public String RideToString() {
		String res= null;
		res = this.getRide().toString();
		
		return res;
	}
	
	public void setRide(Ride r) {
		this.ride=r;
	}
}
