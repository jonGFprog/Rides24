package domain;

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
}
