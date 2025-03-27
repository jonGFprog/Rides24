package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import exceptions.AccountAlreadyExistException;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel registerLabel;
	private JLabel passwordLabel;
	private JLabel tipoUsuarioLabel;
	private JRadioButton rdbtnPasajero;
	private JRadioButton rdbtnDriver;
	private JRadioButton rdbtnBussiness;
	private JButton btnCrearCuenta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nameField;
	private JLabel nameLabel;
	
	public RegisterGUI() {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		emailField = new JTextField();
		emailField.setBounds(121, 79, 175, 30);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		//passwordField.setBounds(121, 217, 175, 30);
		contentPane.add(passwordField);
		
		JLabel emailLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.emailLabel")); 
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(121, 49, 175, 20);
		contentPane.add(emailLabel);
		
		registerLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.registerLabel")); 
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		registerLabel.setBounds(153, 10, 143, 29);
		contentPane.add(registerLabel);
		
		passwordLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.passwordLabel")); 
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//passwordLabel.setBounds(121, 187, 175, 20);
		contentPane.add(passwordLabel);
		
		rdbtnDriver = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.rdbtnDriver")); 
		buttonGroup.add(rdbtnDriver);
		//rdbtnDriver.setBounds(121, 268, 107, 21);
		contentPane.add(rdbtnDriver);
		
		rdbtnPasajero = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.rdbtnPasajero")); 
		buttonGroup.add(rdbtnPasajero);
		//rdbtnPasajero.setBounds(230, 268, 103, 21);
		
		rdbtnBussiness = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.rdbtnBussiness")); 
		buttonGroup.add(rdbtnBussiness);
		//rdbtnDriver.setBounds(121, 268, 107, 21);
		contentPane.add(rdbtnBussiness);
		
		contentPane.add(rdbtnPasajero);
		rdbtnPasajero.setSelected(true);
		
		tipoUsuarioLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.tipoUsuarioLabel")); 
		//tipoUsuarioLabel.setBounds(10, 272, 105, 13);
		contentPane.add(tipoUsuarioLabel);
		
		btnCrearCuenta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.btnCrearCuenta")); 
		btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//btnCrearCuenta.setBounds(121, 295, 175, 34);
		contentPane.add(btnCrearCuenta);
		
		nameField = new JTextField();
		//nameField.setBounds(121, 147, 175, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		nameLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.nameLabel")); 
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//nameLabel.setBounds(121, 124, 175, 20);
		contentPane.add(nameLabel);
		
		
		
		rdbtnDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBounds();
			}});
		rdbtnPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBounds();
			}});
		rdbtnBussiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeBounds();
			}});
		changeBounds();
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDriver.isSelected()) {
					try {
						facade.createDriver(emailField.getText(),String.valueOf(passwordField.getPassword()) ,nameField.getText() );
						close();
					} catch (AccountAlreadyExistException e1) {
						btnCrearCuenta.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.btnCrearCuenta.notValid"));
					}
				}
				else if(rdbtnPasajero.isSelected()){
					try {
						facade.createPasajero(emailField.getText(), String.valueOf(passwordField.getPassword()) );
						close();
					} catch (AccountAlreadyExistException e1) {
						btnCrearCuenta.setText("Email no valido");
					}
				}
				else {
					try {
						facade.createBussiness(emailField.getText(),String.valueOf(passwordField.getPassword()));
						close();
					} catch (AccountAlreadyExistException e1) {
						btnCrearCuenta.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI.btnCrearCuenta.notValid"));
					}
				}
			}});
	}
	
	private void changeBounds() {
		if(rdbtnDriver.isSelected()) {
			setBounds(100, 100, 450, 387);
			btnCrearCuenta.setBounds(121, 295, 175, 34);
			tipoUsuarioLabel.setBounds(10, 272, 105, 13);
			rdbtnDriver.setBounds(121, 268, 107, 21);
			rdbtnPasajero.setBounds(230, 268, 103, 21);
			rdbtnBussiness.setBounds(333, 268, 103, 21);
			passwordLabel.setBounds(121, 187, 175, 20);
			passwordField.setBounds(121, 217, 175, 30);			
			nameField.setBounds(121, 147, 175, 30);
			nameLabel.setBounds(121, 124, 175, 20);
			
			nameLabel.setVisible(true);
			nameField.setVisible(true);
		}
		else {
			setBounds(100, 100, 450, 337);
			btnCrearCuenta.setBounds(121, 256, 175, 34);
			tipoUsuarioLabel.setBounds(10, 216, 105, 13);
			rdbtnDriver.setBounds(121, 212, 107, 21);
			rdbtnPasajero.setBounds(230, 212, 103, 21);
			rdbtnBussiness.setBounds(333, 212, 103, 21);
			passwordLabel.setBounds(121, 126, 175, 20);
			passwordField.setBounds(121, 156, 175, 30);
			
			nameLabel.setVisible(false);
			nameField.setVisible(false);

		}
		
	}
	private void close() {
		this.setVisible(false);
	}
}
