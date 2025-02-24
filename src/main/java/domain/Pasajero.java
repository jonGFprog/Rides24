package domain;

import java.util.ArrayList;

public class Pasajero {

	private String nombreCuenta=null;
	private ArrayList<Solicitud> solicitudes=null;
	
	public Pasajero(String pNombreCuenta) {
		nombreCuenta=pNombreCuenta;
		solicitudes= new ArrayList<Solicitud>();
	}
	
	public String getNombreCuenta() {
		return nombreCuenta;
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
