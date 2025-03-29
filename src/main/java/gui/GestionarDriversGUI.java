package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Business;

import java.awt.GridLayout;
import java.util.ResourceBundle;

public class GestionarDriversGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane;
	private JButton jButtonRegistrarDriver = null;
	private JButton jButtonBorrarDriver = null;
	/**
	 * Create the frame.
	 */
	public GestionarDriversGUI(Business b) {
		BLFacade facade = MainGUI.getBusinessLogic();
		setBounds(100, 100, 450, 300);
		jContentPane = new JPanel();
		jContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(jContentPane);
		jContentPane.setLayout(new GridLayout(2, 1, 0, 0));	
		
		jButtonRegistrarDriver = new JButton();
		jButtonRegistrarDriver.setText(ResourceBundle.getBundle("Etiquetas").getString("GestionarDriversGUI.RegistrarDriver"));
		jButtonRegistrarDriver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new RegisterGUI();
				RegisterGUI.calledFromBussiness(b);
				a.setVisible(true);
			}
		});
		
		jButtonBorrarDriver = new JButton();
		jButtonBorrarDriver.setText(ResourceBundle.getBundle("Etiquetas").getString("GestionarDriversGUI.BorrarDriver"));
		jButtonBorrarDriver.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Business business=facade.getBussiness(b.getEmail());
				JFrame a = new DeleteDriverGUI(business);
				//DeleteDriverGUI.reloadList(business);
				a.setVisible(true);
			}
		});
		jContentPane.add(jButtonRegistrarDriver);
		jContentPane.add(jButtonBorrarDriver);
	}

}
