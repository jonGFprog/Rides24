package businessLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.Solicitud;
import domain.Business;
import domain.Driver;
import domain.Pasajero;
import exceptions.RideMustBeLaterThanTodayException;
import gui.MainGUI;
import exceptions.AccountAlreadyExistException;
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
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
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
	
	public Driver getDriver(String email) {
		dbManager.open();
		Driver driver=dbManager.getDriver(email);		
		dbManager.close();
		return driver;
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
		List<Ride> res = new ArrayList<Ride>();
		res= d.getRides();
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

}

