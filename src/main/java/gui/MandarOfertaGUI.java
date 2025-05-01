package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Business;
import domain.Driver;
import exceptions.OfertaAlreadyExistsException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class MandarOfertaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfEmail;
	private JLabel lblTitulo;
	private JButton btnEnviarOferta;
	private JLabel lblError;
	
	public MandarOfertaGUI(Business b) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Email del conductor:");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTitulo.setBounds(72, 69, 170, 20);
		contentPane.add(lblTitulo);
		
		btnEnviarOferta = new JButton("Enviar Oferta");
		btnEnviarOferta.setBounds(72, 150, 132, 27);
		contentPane.add(btnEnviarOferta);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(72, 101, 132, 27);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setBounds(72, 130, 366, 17);
		contentPane.add(lblError);
		
		btnEnviarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade logica= MainGUI.getBusinessLogic();
				lblError.setText("");
				Driver d= logica.getDriver(tfEmail.getText());
				if(d!=null) {
					try {
						logica.enviarOferta(b,d);
					} catch (OfertaAlreadyExistsException e1) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("MandarOfertaGUI.lblError2"));
					}
				}else {
					lblError.setText(tfEmail.getText()+ " " +ResourceBundle.getBundle("Etiquetas").getString("MandarOfertaGUI.lblError"));
				}				
				
			}});
		
	}
}
