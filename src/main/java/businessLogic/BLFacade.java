package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Ride;
import domain.Solicitud;
import domain.Business;
import domain.Driver;
import domain.Pasajero;
import exceptions.RideMustBeLaterThanTodayException;
import gui.MainGUI;
import exceptions.AccountAlreadyExistException;
import exceptions.RideAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
	
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	
	public Pasajero createPasajero(String email, String password) throws AccountAlreadyExistException ;
	
	public Pasajero getPasajaero(String email);
	
	public Business createBussiness(String email, String password) throws AccountAlreadyExistException ;
	
	public Business getBussiness(String email);
	
	public Driver createDriver(String email, String password,String name) throws AccountAlreadyExistException;
	
	public Driver createDriver(String email, String password, String name, Business b) throws AccountAlreadyExistException;
	
	public Driver getDriver(String email);
	
	public void removeDriverBusiness(Driver d);
	
	public boolean validPassword(String email, String password);
	
	public int getAccountType(String email);
	
	public ArrayList<Solicitud> getPRides(Pasajero p);
	
	public Pasajero bookRide(Ride r, Pasajero p);
	
	public void setPasajeroMain(Pasajero p, MainGUI main);
	
	public List<Ride> getDRides (Driver d);
	
	public List<Solicitud> getAllRequests (Ride r);
	
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	
}
