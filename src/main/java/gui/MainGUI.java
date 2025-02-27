package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Driver;
import domain.Pasajero;
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
	
    private Pasajero driver;
	private static final long serialVersionUID = 1L;
	private static short loggedIn=0; //0 not logged in, 1 logged in as a pasajero, 2 logged in as a driver
	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton jButtonRegister = null;
	private JButton jButtonLogIn = null;

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
	public MainGUI getMain() {
		return this;
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
	public MainGUI(Driver d) {
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
				try {
					jContentPane.remove(jButtonCreateQuery);
					jContentPane.remove(jButtonQueryQueries);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
				
				jContentPane.add(jButtonRegister);
				jContentPane.add(jButtonLogIn);
				break;
			case 1: // logged in como Driver
	
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
				try {
					jContentPane.remove(jButtonRegister);
					jContentPane.remove(jButtonLogIn);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
				
				jContentPane.add(jButtonCreateQuery);
				jContentPane.add(jButtonQueryQueries);
				
				break;
				
			case 2: // logged in como Pasajero
				try {
					jContentPane.remove(jButtonRegister);
					jContentPane.remove(jButtonLogIn);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(jButtonCreateQuery);
					jContentPane.remove(jButtonQueryQueries);
				}
				catch(Exception e) {}
				try {
					jContentPane.remove(panel);
				}
				catch(Exception e) {}
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
			break;
			case 1:
				jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
				jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide"));
			break;
			case 2:
			break;
		}
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.SelectOption"));
		
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.MainTitle")+ " - driver :"+((Driver)driver).getName());
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

