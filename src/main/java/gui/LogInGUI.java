package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

public class LogInGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JLabel RegisterLabel;
	private JLabel passwordLabel;
	private JButton btnLogIn;
	
	
	
	public LogInGUI() {
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
		
		setContentPane(contentPane);
	}

}
