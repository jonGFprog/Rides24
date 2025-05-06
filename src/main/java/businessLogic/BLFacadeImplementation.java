package businessLogic;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.swing.JFrame;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Solicitud;
import domain.UsuarioRegistrado;
import domain.Vehiculo;
import domain.Business;
import domain.Driver;
import domain.Oferta;
import domain.Pasajero;
import exceptions.RideMustBeLaterThanTodayException;
import gui.MainGUI;
import exceptions.AccountAlreadyExistException;
import exceptions.NotEnoughMoney;
import exceptions.OfertaAlreadyExistsException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		    dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail,Vehiculo vehiculo ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail, vehiculo);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		
		return dates;
	}
	
	public Pasajero createPasajero(String email, String password) throws AccountAlreadyExistException {
		dbManager.open();
		Pasajero pasajero=dbManager.createPasajero(email,password);		
		dbManager.close();
		return pasajero;
		
	}
	
	public Pasajero getPasajaero(String email) {
		dbManager.open();
		Pasajero pasajero=dbManager.getPasajero(email);		
		dbManager.close();
		return pasajero;
	}
	
	public Driver createDriver(String email, String password,String name) throws AccountAlreadyExistException {
		dbManager.open();
		Driver driver=dbManager.createDriver(email,password,name);		
		dbManager.close();
		return driver;
		
	}
	
	public Driver createDriver(String email, String password, String name, Business b)throws AccountAlreadyExistException {
		dbManager.open();
		Driver driver=dbManager.createDriver(email,password,name,b);		
		dbManager.close();
		return driver;
	}

	
	public Driver getDriver(String email) {
		dbManager.open();
		Driver driver=dbManager.getDriver(email);		
		dbManager.close();
		return driver;
	}
	
	public Business removeDriverBusiness(Driver d) {
		dbManager.open();
		Business b = dbManager.removeDriverBusiness(d);
		dbManager.close();
		return b;
	}
	
	public Business createBussiness(String email, String password) throws AccountAlreadyExistException {
		dbManager.open();
		Business b=dbManager.createBussiness(email,password);		
		dbManager.close();
		return b;
		
	}
	
	public Business getBussiness(String email) {
		dbManager.open();
		Business b=dbManager.getBussiness(email);		
		dbManager.close();
		return b;
	}
	
	public ArrayList<Driver> getBDrivers(Business b){
		dbManager.open();
		ArrayList<Driver> aDevolver=dbManager.getBDrivers(b);
		dbManager.close();
		return aDevolver;
	}
	
	public boolean validPassword(String email, String password) {
		dbManager.open();
		boolean aDevolver=dbManager.validPassword(email,password);		
		dbManager.close();
		return aDevolver;
	}
	
	public int getAccountType(String email) {
		dbManager.open();
		int aDevolver=dbManager.getAccountType(email);
		dbManager.close();
		return aDevolver;
	}
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}
	
	public ArrayList<Solicitud> getPRides(Pasajero p){
		ArrayList<Solicitud> res = new ArrayList<Solicitud>();
		res= p.getSolicitudes();
		
		return res;
	}
	
	public Pasajero bookRide(Ride r, Pasajero p) {
		Solicitud miSolicitud= new Solicitud(p, r);
		Pasajero res= dbManager.flightBooked(p, miSolicitud);
		return res;
		
	}
	
	public List<Ride> getDRides(Driver d) {
		dbManager.open();
		List<Ride> res=dbManager.getDRides(d);
		dbManager.close();
		return res;
	}
	
	public List<Solicitud> getAllRequests (Ride r){
		List<Solicitud> res = null;
		res = dbManager.getAllRequests(r);
		return res;
	}
	
	
	
	

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    
    public void setPasajeroMain(Pasajero d, MainGUI main) {
    	main.setDriver(d);
    }
    
    public Pasajero setEstado (String e, Solicitud antigua) {
    	
    	Pasajero p= null;
    	dbManager.open();
    	p= dbManager.actualizarSolicitudes(antigua.getPasajero(), antigua, e);
    	dbManager.close();
    	
    	return p;
    	
    }

    public Vehiculo registrarVehiculo(File selectedFile,String marca,String modelo, String driverEmail, int pPlazas) {
    	dbManager.open();
		Vehiculo v =dbManager.registrarVehiculo(selectedFile,marca,modelo,driverEmail, pPlazas);
		dbManager.close();
		return v;
    }
    
	public ArrayList<Vehiculo> getVehiculos(Driver d){
		
		dbManager.open();
		ArrayList<Vehiculo> v =dbManager.getVehiculos(d);
		dbManager.close();
		return v;
	}
	
	public void enviarOferta(Business b, Driver d) throws OfertaAlreadyExistsException{
		dbManager.open();
		dbManager.enviarOferta(b,d);
		dbManager.close();
	}
	
	public void setEstadoOferta(Oferta o,String estado) {
		dbManager.open();
		dbManager.setEstadoOferta(o,estado);
		dbManager.close();
	}
	
	public Pasajero payRide(Solicitud s) {
		Pasajero user= null;
		if (s.getPasajero().getSaldo()<s.getRide().getPrice()) {
			JFrame a= new NotEnoughMoney();
			
			a.setVisible(true);
		}else {
			dbManager.open();
			user= dbManager.payRide(s);
			dbManager.close();
		}
		
		
		return user;
	}
	
	public Pasajero addBalance (Pasajero p, Double d) {
		Pasajero user= p;
		
		dbManager.open();
		user= dbManager.addBalance(user, d);
		dbManager.close();
		
		
		
		return user;
	}
	
	public Pasajero clearNull (Pasajero p){
		
		Pasajero x= null;
		
		dbManager.open();
		x= dbManager.clearNullSolicitud(p);
		dbManager.close();
		
		return x;
		
	}
}

