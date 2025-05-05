package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Business;
import domain.Driver;
import domain.Pasajero;
import domain.UsuarioRegistrado;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
    private UsuarioRegistrado driver;
	private static final long serialVersionUID = 1L;
	private static short loggedIn=0; //0 not logged in, 1 logged in as a pasajero, 2 logged in as a driver, 3 logged in as a bussiness
	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonRegister = null;
	private JButton jButtonLogIn = null;
	private JButton jButtonBookingOverview = null;
	private JButton jButtonRequestReservation = null;
	private JButton jButtonViewRequests= null;
	private JButton jButtonVerOfertas= null;
	private JButton jButtonPayRides= null;
	
	private JButton jButtonGestionarDrivers= null;
	private JButton jButtonRegistrarVehiculo=null;
	private JButton jButtonCreateRideBusiness=null;
	private MainGUI guardarMain = this.getMain();
	
	
	private JLabel jMailSaldo = null;
	private JPanel lablePanel= null;
	
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	public void setAccount(Pasajero p) {
		driver = p;
		loggedIn = 1;
	}
	
	public void setAccount(Driver d) {
		driver = d;
		loggedIn = 2;
	}
	public void setAccount(Business b) {
		driver = b;
		loggedIn = 3;
	}
	public MainGUI getMain() {
		return this;
	}
	public void setDriver(Pasajero d) {
		driver= d;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * This is the default constructor
	 */
	public MainGUI(UsuarioRegistrado d) {
		super();

		driver=d;
		
		// this.setSize(271, 295);
		this.setSize(495, 290);
		jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabelSelectOption.setForeground(Color.BLACK);
		jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		
		rdbtnNewRadioButton = new JRadioButton("English");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();				}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: "+Locale.getDefault());
				paintAgain();
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		
		
		jMailSaldo= new JLabel(this.driver.getEmail()+"   | Mi saldo: "+ this.driver.getSaldo().toString());
		
		lablePanel= new JPanel();
		lablePanel.add(jMailSaldo);
	
		panel = new JPanel();
		panel.add(rdbtnNewRadioButton_1);
		panel.add(rdbtnNewRadioButton_2);
		panel.add(rdbtnNewRadioButton);
		
		
		
		jContentPane = new JPanel();
		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		
		changeLoggedIn();
		
		
		
		
		
		setContentPane(jContentPane);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle") + " - driver :"+((Driver)driver).getName());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		
	}
	public void changeLoggedIn(){
		System.out.println("loggedIn = "+loggedIn);
		try {
			jContentPane.remove(jLabelSelectOption);
		}
		catch(Exception e) {}
		jContentPane.add(jLabelSelectOption);
		switch(loggedIn) {
			case 0: // no logged in
				jButtonRegister = new JButton();
				jButtonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new RegisterGUI();
						a.setVisible(true);
					}
				});
				
				jButtonLogIn = new JButton();
				jButtonLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.LogIn"));
				jButtonLogIn.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new LogInGUI(getMain());
						a.setVisible(true);
					}
				});
				
				jContentPane.add(jButtonRegister);
				jContentPane.add(jButtonLogIn);
				break;
			case 1: // logged in como Pasajero
				jContentPane.setLayout(new GridLayout(6, 1, 0, 0));
				jButtonQueryQueries = new JButton();
				jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
				jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new FindRidesGUI();
	
						a.setVisible(true);
					}
				});
				
				
				jButtonRequestReservation = new JButton();
				jButtonRequestReservation.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RequestReservation"));
				jButtonRequestReservation.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new RequestReservationGUI((Pasajero)driver, guardarMain);
						
						a.setVisible(true);
					}
				});
				
				jButtonBookingOverview = new JButton();
				jButtonBookingOverview.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.BookingOverview"));
				jButtonBookingOverview.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						JFrame a = new BookingOverviewGUI((Pasajero)driver); 
							
						a.setVisible(true);
						
						
					}
				});
				
				jButtonPayRides = new JButton();
				jButtonPayRides.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.PayRides"));
				jButtonPayRides.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						JFrame a = new PayRides((Pasajero)driver, guardarMain); 
							
						a.setVisible(true);
						
						
					}
				});
				
				try {
					jContentPane.remove(jButtonRegister);
					jContentPane.remove(jButtonLogIn);
				}
				catch(Exception e) {}
				/*try {
					jContentPane.remove(jButtonCreateQuery);
					jContentPane.remove(jButtonQueryQueries);
				}
				catch(Exception e) {}*/
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
				jContentPane.add(jMailSaldo);
				jContentPane.add(jButtonRequestReservation);
				jContentPane.add(jButtonQueryQueries);
				jContentPane.add(jButtonBookingOverview);
				
				break;
	
				
				
			case 2: // logged in como Driver
				jContentPane.setLayout(new GridLayout(10, 1, 0, 0));
				jButtonCreateQuery = new JButton();
				jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
				jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new CreateRideGUI((Driver)driver);
						a.setVisible(true);
					}
				});
				
				jButtonQueryQueries = new JButton();
				jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
				jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new FindRidesGUI();
	
						a.setVisible(true);
					}
				});
				jButtonBookingOverview = new JButton();
				jButtonBookingOverview.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.BookingOverview"));
				jButtonBookingOverview.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						
						JFrame a = new BookingOverviewGUI((Pasajero)driver); 
							
						a.setVisible(true);
						
						
					}
				});
				
				
				jButtonRequestReservation = new JButton();
				jButtonRequestReservation.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RequestReservation"));
				jButtonRequestReservation.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new RequestReservationGUI((Pasajero)driver, guardarMain);
						
						a.setVisible(true);
					}
				});
				
				
				jButtonViewRequests = new JButton();
				jButtonViewRequests.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.ViewRequests"));
				jButtonViewRequests.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new ViewOfferedRides((Driver)driver);
						
						a.setVisible(true);
					}
				});
				jButtonRegistrarVehiculo = new JButton();
				jButtonRegistrarVehiculo.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RegistrarVehiculo"));
				jButtonRegistrarVehiculo.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new RegistrarVehiculoGUI((Driver)driver);
						
						a.setVisible(true);
					}
				});
				jButtonVerOfertas = new JButton();
				jButtonVerOfertas.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerOfertas"));
				jButtonVerOfertas.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new VerOfertasGUI((Driver)driver);
						
						a.setVisible(true);
					}
				});
				try {
					jContentPane.remove(jButtonRegister);
					jContentPane.remove(jButtonLogIn);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
				
				this.setSize(695, 290);
				//jContentPane.add(jMailSaldo); Business no necesita saldo
				jContentPane.add(jButtonCreateQuery);
				jContentPane.add(jButtonQueryQueries);
				jContentPane.add(jButtonRequestReservation);
				jContentPane.add(jButtonBookingOverview);
				jContentPane.add(jButtonViewRequests);
				jContentPane.add(jButtonVerOfertas);
				jContentPane.add(jButtonRegistrarVehiculo);
				
				break;
				
			case 3: // logged in como Bussiness
				jContentPane.setLayout(new GridLayout(4, 1, 0, 0));

				jButtonGestionarDrivers = new JButton();
				jButtonGestionarDrivers.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.GestionarDrivers"));
				jButtonGestionarDrivers.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new GestionarDriversGUI((Business)driver);
						a.setVisible(true);
					}
				});
				
				jButtonCreateRideBusiness = new JButton();
				jButtonCreateRideBusiness.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
				jButtonCreateRideBusiness.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						JFrame a = new ListaDriversGUI((Business)driver);
						a.setVisible(true);
					}
				});
				
				try {
					jContentPane.remove(jButtonRegister);
					jContentPane.remove(jButtonLogIn);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
				jContentPane.add(jButtonGestionarDrivers);
				jContentPane.add(jButtonCreateRideBusiness);
				
			break;
		}
		
		jContentPane.add(panel);
		SwingUtilities.updateComponentTreeUI(getMain());
	}
	
	private void paintAgain() {
		switch(loggedIn) {
			case 0:
				jButtonRegister.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.Register"));
				jButtonLogIn.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.LogIn"));
				this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle"));
			break;
			case 1:
				jMailSaldo.setText(this.driver.getEmail()+"   | Mi saldo: "+ this.driver.getSaldo().toString());
				jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
				jButtonRequestReservation.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RequestReservation"));
				this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle"));
			break;
			case 2:
				jMailSaldo.setText(this.driver.getEmail()+"   | Mi saldo: "+ this.driver.getSaldo().toString());
				jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
				jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
				jButtonViewRequests.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.ViewRequests"));
				jButtonBookingOverview.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.BookingOverview"));
				jButtonRequestReservation.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RequestReservation"));
				jButtonRegistrarVehiculo.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.RegistrarVehiculo"));
				jButtonVerOfertas.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.VerOfertas"));
				this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ " - driver :"+((Driver)driver).getName());
			break;
			case 3:
				jButtonGestionarDrivers.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.GestionarDrivers"));
				jButtonCreateRideBusiness.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
			break;
		}
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

