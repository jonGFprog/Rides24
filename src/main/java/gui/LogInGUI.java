package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;
import domain.Pasajero;
import exceptions.AccountAlreadyExistException;

public class LogInGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel RegisterLabel;
	private JLabel passwordLabel;
	private JButton btnLogIn;
	
	
	
	public LogInGUI(MainGUI main) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		emailField = new JTextField();
		emailField.setBounds(121, 79, 175, 30);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 156, 175, 30);
		contentPane.add(passwordField);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailLabel.setBounds(121, 49, 175, 20);
		contentPane.add(emailLabel);
		
		RegisterLabel = new JLabel("Log In");
		RegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		RegisterLabel.setBounds(153, 10, 143, 29);
		contentPane.add(RegisterLabel);
		
		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLabel.setBounds(121, 126, 175, 20);
		contentPane.add(passwordLabel);

		btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogIn.setBounds(121, 210, 175, 34);
		contentPane.add(btnLogIn);
		
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(facade.validPassword(emailField.getText(),String.valueOf(passwordField.getPassword()))) {
					Pasajero p;
					if(facade.getAccountType(emailField.getText())==1) {
						p=facade.getPasajaero(emailField.getText());
						main.setAccount(p);
					}
					else {
						p=facade.getDriver(emailField.getText());
						main.setAccount((Driver)p);
					}
					main.changeLoggedIn();
					close();
					
				}
				else {
					
				}
			}});
		
		setContentPane(contentPane);
	}
	private void close() {
		this.setVisible(false);
	}
}
