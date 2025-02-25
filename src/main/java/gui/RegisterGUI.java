package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel RegisterLabel;
	private JLabel passwordLabel;
	private JLabel tipoUsuarioLabel;
	private JRadioButton rdbtnPasajero;
	private JRadioButton rdbtnDriver;
	private JButton btnCrearCuenta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nameField;
	private JLabel nameLabel;
	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
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
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(121, 49, 175, 20);
		contentPane.add(emailLabel);
		
		RegisterLabel = new JLabel("Crear Cuenta");
		RegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		RegisterLabel.setBounds(153, 10, 143, 29);
		contentPane.add(RegisterLabel);
		
		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//passwordLabel.setBounds(121, 187, 175, 20);
		contentPane.add(passwordLabel);
		
		rdbtnDriver = new JRadioButton("Conductor");
		buttonGroup.add(rdbtnDriver);
		//rdbtnDriver.setBounds(121, 268, 107, 21);
		contentPane.add(rdbtnDriver);
		
		rdbtnPasajero = new JRadioButton("Pasajero");
		buttonGroup.add(rdbtnPasajero);
		//rdbtnPasajero.setBounds(230, 268, 103, 21);
		contentPane.add(rdbtnPasajero);
		rdbtnPasajero.setSelected(true);
		
		tipoUsuarioLabel = new JLabel("Tipo de usuario:");
		//tipoUsuarioLabel.setBounds(10, 272, 105, 13);
		contentPane.add(tipoUsuarioLabel);
		
		btnCrearCuenta = new JButton("Crear cuenta");
		btnCrearCuenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		//btnCrearCuenta.setBounds(121, 295, 175, 34);
		contentPane.add(btnCrearCuenta);
		
		nameField = new JTextField();
		//nameField.setBounds(121, 147, 175, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		nameLabel = new JLabel("Nombre:");
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
		changeBounds();
		
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDriver.isSelected()) {
					//facade.
				}
				else {
					
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
			passwordLabel.setBounds(121, 126, 175, 20);
			passwordField.setBounds(121, 156, 175, 30);
			
			nameLabel.setVisible(false);
			nameField.setVisible(false);

		}
		
	}
}
