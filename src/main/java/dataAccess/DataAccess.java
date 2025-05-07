package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Business;
import domain.Driver;
import domain.Oferta;
import domain.Pasajero;
import domain.Ride;
import domain.Solicitud;
import domain.UsuarioRegistrado;
import domain.Vehiculo;
import exceptions.AccountAlreadyExistException;
import exceptions.OfertaAlreadyExistsException;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		/*
		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		   
		    //Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			driver1.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 4, 7);
			driver1.addRide("Donostia", "Gazteiz", UtilDate.newDate(year,month,6), 4, 8);
			driver1.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 4, 4);

			driver1.addRide("Donostia", "Iruña", UtilDate.newDate(year,month,7), 4, 8);
			
			driver2.addRide("Donostia", "Bilbo", UtilDate.newDate(year,month,15), 3, 3);
			driver2.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,25), 2, 5);
			driver2.addRide("Eibar", "Gasteiz", UtilDate.newDate(year,month,6), 2, 5);

			driver3.addRide("Bilbo", "Donostia", UtilDate.newDate(year,month,14), 1, 3);

			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);

			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}*/
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverEmail, Vehiculo vehiculo) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverEmail+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverEmail);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price, vehiculo);
			//next instruction can be obviated
			db.persist(driver); 
			db.persist(ride);
			db.getTransaction().commit();

			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
	 		 if (ride.getnPlaces()>0) {
	 			res.add(ride);
	 		 }
		   
		  }
	 	return res;
	}
	
	public int getAccountType(String email) {//0 not found, 1 Pasajero, 2 Driver, 3 Bussiness
		int accountType=0;
		db.getTransaction().begin();
		UsuarioRegistrado p=db.find(Driver.class, email);
		if(p==null) {
			p=db.find(Pasajero.class, email);
			if(p==null){
				p=db.find(Business.class, email);
				if(p!=null) {
					System.out.println(">> DataAccess: getAccountType=> isBussiness");
					accountType=3;
				}
			}
			else {
				System.out.println(">> DataAccess: getAccountType=> isPasajero");
				accountType=1;
			}
		}
		else {
			System.out.println(">> DataAccess: getAccountType=> isDriver");
			accountType=2;
		}
		db.getTransaction().commit();
		return accountType;
	}
	
	private UsuarioRegistrado accountExist(String email) {
		UsuarioRegistrado p=db.find(Pasajero.class, email);
		if(p==null) {
			p=db.find(Driver.class, email);
			if(p==null) {
				p=db.find(Business.class, email);
			}
		}
		return p;
	} 
	
	public Pasajero createPasajero(String email, String password) throws AccountAlreadyExistException { 
		db.getTransaction().begin();
		Pasajero p = (Pasajero)accountExist(email);
		if(p!=null) {
			db.getTransaction().commit();
			throw new AccountAlreadyExistException();
		}
		p=new Pasajero(email,password);
		System.out.println(">> DataAccess: createPasajero=> email= "+email+" password= "+password);
		db.persist(p);
		db.getTransaction().commit();
		return p;
	}
	
	public Pasajero getPasajero(String email) {
		System.out.println(">> DataAccess: getPasajero=> email= "+email);
		db.getTransaction().begin();
		Pasajero p=db.find(Pasajero.class, email); 
		db.getTransaction().commit();
		
		return p;		
	}
	
	public Business createBussiness(String email, String password) throws AccountAlreadyExistException { 
		db.getTransaction().begin();
		Business b = (Business)accountExist(email);
		if(b!=null) {
			db.getTransaction().commit();
			throw new AccountAlreadyExistException();
		}
		b=new Business(email,password);
		System.out.println(">> DataAccess: createBussiness=> email= "+email+" password= "+password);
		db.persist(b);
		db.getTransaction().commit();
		return b;
	}
	
	public Business getBussiness(String email) {
		System.out.println(">> DataAccess: getBussiness=> email= "+email);
		db.getTransaction().begin();
		Business b=db.find(Business.class, email); 
		db.getTransaction().commit();
		
		return b;		
	}
	
	public Driver createDriver(String email, String password, String name) throws AccountAlreadyExistException { 
		db.getTransaction().begin();
		Driver driver = (Driver)accountExist(email);
		if(driver!=null) { 
			db.getTransaction().commit();
			throw new AccountAlreadyExistException();
		}
		driver=new Driver(email,password,name);
		System.out.println(">> DataAccess: createDriver=> email= "+email+" password= "+password+" name= "+name);
		db.persist(driver);
		db.getTransaction().commit();
		return driver;
	}
	
	public Driver createDriver(String email, String password, String name, Business b) throws AccountAlreadyExistException { 
		db.getTransaction().begin();
		b=db.find(Business.class,b);
		Driver driver = (Driver)accountExist(email);
		if(driver!=null) { 
			db.getTransaction().commit();
			throw new AccountAlreadyExistException();
		}
		driver=new Driver(email,password,name);
		b.addDriver(driver);
		System.out.println(">> DataAccess: createDriver=> email= "+email+" password= "+password+" name= "+name);
		System.out.println(">> DataAccess: Driver "+driver.getEmail()+" creado y añadido al bussiness "+b.getEmail());
		db.persist(driver);
		db.persist(b);
		db.getTransaction().commit();
		return driver;
	}
	
	public Driver getDriver(String email) {
		System.out.println(">> DataAccess: getDriver=> email= "+email);
		db.getTransaction().begin();
		Driver driver=db.find(Driver.class, email);
		db.getTransaction().commit();
		
		return driver;		
	}
	
	public Business removeDriverBusiness(Driver d) {
		db.getTransaction().begin();
		Business b = d.getBussiness();
		b=db.find(Business.class, b);
		b.removeDriver(db.find(Driver.class, d));
		d.setHasBussiness(false);
		db.getTransaction().commit();
		return b;
	}
	
	public Vehiculo registrarVehiculo(File selectedFile,String marca,String modelo, String driverEmail, int pPlazas) {
		db.getTransaction().begin();
		
		Driver driver = db.find(Driver.class, driverEmail);
		Vehiculo v = driver.addVehiculo(new Vehiculo(selectedFile,marca,modelo,pPlazas,driver));
		db.persist(driver); 
		db.persist(v);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: Vehiculo "+v.getMarca()+" "+v.getModelo()+" con "+v.getPlazas()+" plazas registrado en el driver "+ driverEmail);
		return v;
	}
	
	public ArrayList<Vehiculo> getVehiculos(Driver d){
		db.getTransaction().begin();
		
		Driver driver = db.find(Driver.class,d);
		ArrayList<Vehiculo> v=driver.getVehiculos();
		System.out.println(">> DataAccess: getVehiculos de "+d.getEmail());
		db.getTransaction().commit();
		return v;
	}
	public boolean validPassword(String email, String password) {
		db.getTransaction().begin();
		UsuarioRegistrado p = accountExist(email);
		db.getTransaction().commit();
		if(p==null) {
			System.out.println(">> DataAccess: not valid password");
			return false;
		}
		if(!p.getPassword().equals(password)) {
			System.out.println(">> DataAccess: not valid password");
			return false;
		}
		System.out.println(">> DataAccess: valid password");
		return true;
	}
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4 AND r.nPlaces>0",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	

	public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	public List<Solicitud> getAllRequests(Ride r){
		List<Solicitud> res = null;
		open();
		TypedQuery<Solicitud> myQuery= db.createQuery("SELECT s FROM Solicitud s WHERE s.ride= :ride", Solicitud.class);
		myQuery.setParameter("ride", r);
		res= myQuery.getResultList();
		close();
		return res;
		
	}
	
	public List<Solicitud> getAllRequests(Driver r){
		List<Solicitud> res = null;
		open();
		TypedQuery <Solicitud> myQuery= db.createQuery("SELECT s FROM Solicitud s WHERE s.ride= :ride", Solicitud.class);
		myQuery.setParameter("ride", r);
		res= myQuery.getResultList();
		close();
		return res;
		
	}
	
	
	public Pasajero flightBooked(Pasajero c, Solicitud r) {
		open();
		Pasajero p=db.find(Pasajero.class, c);
		ArrayList<Solicitud> lista= new ArrayList<Solicitud>();
		//Solicitud dbSolicitud= db.find(Solicitud.class, r);
		Ride myRide= db.find(Ride.class, r.getRide());
		
		
		
		
		
		if (p==null) {
			System.out.println("Error, el pasajero no esta en la Base de Datos");
		}else {
			
			lista= p.getSolicitudes();
			lista.add(r);
			db.getTransaction().begin();
			p.setSolicitudes(lista);
			myRide.setBetMinimum(myRide.getnPlaces()-1);
			//dbSolicitud.setRide(myRide);
			db.getTransaction().commit();
			
			//System.out.println(db.find(Pasajero.class, p).getSolicitudes().getFirst().RideToString());
			
		}
		p= db.find(Pasajero.class, p);
		close();
		return p;
		
	}
	
	public Pasajero actualizarSolicitudes (Pasajero p, Solicitud antigua, String e) {
		
		Pasajero save= db.find(Pasajero.class, p);
		Solicitud copia = db.find(Solicitud.class, antigua);
		copia.setEstado(e);
		if (save!= null) {
			
			db.getTransaction().begin();
			copia.setEstado(e);
			db.getTransaction().commit();
			
		}else {
			System.out.println("Actualizar Solicitud no encuentra Pasajero");
		}
		
		
		return save;
	}
	
	public List<Ride> getDRides(Driver d){
		db.getTransaction().begin();
		Driver driver = db.find(Driver.class,d);
		List<Ride> v=driver.getRides();
		System.out.println(">> DataAccess: getDRides de "+d.getEmail());
		db.getTransaction().commit();
		return v;
		
	}
	
	public ArrayList<Driver> getBDrivers(Business b){
		db.getTransaction().begin();
		Business business = db.find(Business.class,b);
		ArrayList<Driver>  v=business.getDrivers();
		System.out.println(">> DataAccess: getBDrivers de "+b.getEmail());
		db.getTransaction().commit();
		return v;
	}
	
	public void enviarOferta(Business b, Driver d) throws OfertaAlreadyExistsException{
		db.getTransaction().begin();
		Business business = db.find(Business.class,b);
		Driver driver = db.find(Driver.class, d);
		Oferta oferta = new Oferta(d,b,"Pendiente");
		for(Oferta o: driver.getOfertas()) {
			if(o.equals(oferta)) {
				throw new OfertaAlreadyExistsException();
			}
		}
		business.addOferta(oferta);
		driver.addOferta(oferta);
		db.getTransaction().commit();
	}
	
	public void setEstadoOferta(Oferta o, String estado) {
		db.getTransaction().begin();
		Oferta oferta = db.find(Oferta.class, o);
		oferta.setEstado(estado);
		if(estado.equals("Aceptado")) {
			Business business = db.find(Business.class,oferta.getBusiness());
			Driver driver = db.find(Driver.class, oferta.getDriver());
			business.addDriver(driver);
			driver.setBussiness(business);
		}
		db.getTransaction().commit();
	}
	
	public Pasajero payRide(Solicitud s) {
		
		Driver driver= db.find(Driver.class, s.getRide().getDriver());
		Pasajero user= db.find(Pasajero.class, s.getPasajero());
		
		Driver dCopia= s.getRide().getDriver();
		Pasajero pCopia= s.getPasajero();
		
		Double saldoDriver=0.00;
		Double saldoUser=0.00;
		
		
		if (!dCopia.itsSame(pCopia)) {
			saldoDriver= dCopia.getSaldo() + s.getRide().getPrice();
			saldoUser= pCopia.getSaldo() - s.getRide().getPrice();
			System.out.println("Pre:");
			System.out.println(s.getRide().getPrice());
			System.out.println("Driver:");
			System.out.println(dCopia.getSaldo());
			System.out.println("User");
			System.out.println(pCopia.getSaldo());
			System.out.println("Post:");
			System.out.println("Driver:");
			System.out.println(saldoDriver);
			System.out.println("User");
			System.out.println(saldoUser);
			db.getTransaction().begin();
			driver.setSaldo(saldoDriver);
			user.setSaldo(saldoUser);
			db.getTransaction().commit();
		}else {
			System.out.println("El driver y el pasajero son el mismo usuario");
		}
		
		
		
		
		actualizarSolicitudes(user, s, "Pagado");
		
		return user;
	}
	
	public Pasajero addBalance (Pasajero p, Double d) {
		
		Pasajero user= db.find(Pasajero.class, p);
		db.getTransaction().begin();
		System.out.println(user.getSaldo());
		System.out.println(d);
		user.setSaldo(user.getSaldo()+d);
		System.out.println(user.getSaldo());
		db.getTransaction().commit();
		
		return user;
	}
	
	public Pasajero clearNullSolicitud(Pasajero p) {
		Pasajero user= db.find(Pasajero.class, p);
		
		for (Solicitud i : p.getSolicitudes()) {
			if (i.getEstado()==null) {
				i.setEstado("Pendiente");
				this.actualizarSolicitudes(p, i, "Pendiente");
			}
		}
		
		
		return user;
		
		
	}
	
	public void returnDeclinedSeat(Solicitud s) {
		Ride ride= db.find(Ride.class, s.getRide());
		Ride copia= s.getRide();
		
		db.getTransaction().begin();
		ride.setBetMinimum(copia.getnPlaces()+1);
		db.getTransaction().commit();
		
	}
	
	
	
	
	
}
