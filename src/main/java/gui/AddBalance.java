package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Pasajero;
import domain.UsuarioRegistrado;
import exceptions.EmptyField;
import exceptions.NoNegativeAllowed;
import exceptions.NotEnoughMoney;

import javax.swing.JLabel;

public class AddBalance extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Pasajero guardarUser= null;
	private JLabel jLabelNumero= null;
	private JLabel jLabelNombre= null;
	private JLabel jLabelApellidos= null;
	private JLabel jLabelFechaCaducidad= null;
	private JLabel jLabelCCV= null;
	private JLabel jLabelCantidad= null;
	
	private JTextField jTextFieldNumero= null;
	private JTextField jTextFieldlNombre= null;
	private JTextField jTextFieldApellidos= null;
	private JTextField jTextFieldFechaCaducidad= null;
	private JTextField jTextFieldCCV= null;
	private JTextField jTextFieldCantidad= null;
	
	private JButton jButtonAccept= null;
	private BLFacade BL= null;
	
	
	public AddBalance(Pasajero user, MainGUI guardarmain) {
		
		this.setSize(495, 290);
		
		BL= MainGUI.getBusinessLogic();
		
		JPanel panel = new JPanel();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		jTextFieldNumero = new JTextField();
		jTextFieldNumero.setBounds(131, 44, 268, 20);
		panel.add(jTextFieldNumero);
		jTextFieldNumero.setColumns(10);
		
		jLabelNumero = new JLabel("Numero de Tarjeta:");
		jLabelNumero.setBounds(23, 44, 100, 20);
		panel.add(jLabelNumero);
		
		jLabelNombre = new JLabel("Nombre:");
		jLabelNombre.setBounds(23, 75, 57, 20);
		panel.add(jLabelNombre);
		
		jTextFieldlNombre = new JTextField();
		jTextFieldlNombre.setBounds(130, 75, 269, 20);
		panel.add(jTextFieldlNombre);
		jTextFieldlNombre.setColumns(10);
		
		jLabelApellidos = new JLabel("Apellidos:");
		jLabelApellidos.setBounds(23, 106, 57, 20);
		panel.add(jLabelApellidos);
		
		jLabelFechaCaducidad = new JLabel("Fecha de caducidad:");
		jLabelFechaCaducidad.setBounds(209, 137, 100, 20);
		panel.add(jLabelFechaCaducidad);
		
		jLabelCCV = new JLabel("CVC:");
		jLabelCCV.setBounds(22, 137, 46, 20);
		panel.add(jLabelCCV);
		
		jTextFieldApellidos = new JTextField();
		jTextFieldApellidos.setBounds(131, 106, 268, 20);
		panel.add(jTextFieldApellidos);
		jTextFieldApellidos.setColumns(10);
		
		jTextFieldFechaCaducidad = new JTextField();
		jTextFieldFechaCaducidad.setBounds(131, 137, 68, 20);
		panel.add(jTextFieldFechaCaducidad);
		jTextFieldFechaCaducidad.setColumns(10);
		
		jTextFieldCCV = new JTextField();
		jTextFieldCCV.setBounds(324, 137, 75, 20);
		panel.add(jTextFieldCCV);
		jTextFieldCCV.setColumns(10);
		
		jLabelCantidad = new JLabel("Cantidad:");
		jLabelCantidad.setBounds(23, 168, 57, 20);
		panel.add(jLabelCantidad);
		
		jTextFieldCantidad = new JTextField();
		jTextFieldCantidad.setBounds(131, 168, 68, 20);
		panel.add(jTextFieldCantidad);
		jTextFieldCantidad.setColumns(1);
		
		
		JLabel lblNewLabel_1 = new JLabel("  €");
		lblNewLabel_1.setBounds(199, 168, 46, 20);
		panel.add(lblNewLabel_1);
		
		jButtonAccept= new JButton("Aceptar");
		jButtonAccept.setLocation(23, 210);
		jButtonAccept.setSize(377, 20);
		jButtonAccept.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed (java.awt.event.ActionEvent e) {
				String s=jTextFieldCantidad.getText();
				//System.out.println(s);
				Boolean v= true;
				Double toAdd= 0.00;
				if (anyEmpty()) {
					try {
						toAdd=Double.parseDouble(s);
					}
					catch(Exception a) {
						v= false;
					}
					if (v) {
						if (toAdd>0.00) {
							guardarUser= BL.addBalance(user, toAdd);
						}else {
							JFrame a= new NoNegativeAllowed();
							
							a.setVisible(true);
						}
						
					}
					
					guardarmain.setAccount(guardarUser);
					guardarmain.updateBalance(guardarUser);
					
					
					
				}else {
					JFrame a= new EmptyField();
					
					a.setVisible(true);
				}
				
				setVisible(false);
			}
		});
		panel.add(jButtonAccept);
	}
	
	public Boolean anyEmpty() {
		Boolean ret= !jTextFieldNumero.getText().isEmpty();
		
		ret= ret&&!jTextFieldlNombre.getText().isEmpty();
		ret= ret&&!jTextFieldApellidos.getText().isEmpty();
		ret= ret&&!jTextFieldFechaCaducidad.getText().isEmpty();
		ret= ret&&!jTextFieldCCV.getText().isEmpty();
		ret= ret&&!jTextFieldCantidad.getText().isEmpty();
		
		return ret;
	}
}

	
	
