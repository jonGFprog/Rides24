package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Driver;
import domain.Oferta;
import domain.Pasajero;
import domain.Ride;
import domain.Solicitud;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.BorderLayout;

public class VerOfertasGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JList<Oferta> listaOfertas= null;
	private DefaultListModel<Oferta> model= new DefaultListModel<Oferta>();
	private JScrollPane jScroll = null;
	private JPanel contentPane = null;
	private JButton jButtonAccept = null;
	private JButton jButtonDecline= null;
	private Driver driver=null;
	
	
	
	
	public VerOfertasGUI (Driver d) {
		
		BLFacade logica= MainGUI.getBusinessLogic();
		driver= logica.getDriver(d.getEmail());
		for(Oferta o: driver.getOfertas()) {
			model.addElement(o);
		}
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();;
		
		
		jButtonAccept = new JButton();
		jButtonAccept.setText("Accept");
		jButtonAccept.setBounds(0, 213, 222, 48);
		jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
					if(listaOfertas.getSelectedValue()!=null) {
						logica.setEstadoOferta(listaOfertas.getSelectedValue(), "Aceptado");
						reload(logica);
					}
				}
			
		});
		contentPane.add(jButtonAccept);
		
		jButtonDecline = new JButton();
		jButtonDecline.setText("Decline");
		jButtonDecline.setBounds(221, 213, 213, 48);
		jButtonDecline.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(listaOfertas.getSelectedValue()!=null) {
					logica.setEstadoOferta(listaOfertas.getSelectedValue(), "Rechazado");
					reload(logica);
				}
			}
		});
		contentPane.add(jButtonDecline);
		contentPane.setLayout(null);
		
		jScroll= new JScrollPane();
		jScroll.setBounds(0, 0, 434, 213);
		contentPane.add(jScroll);
		
		listaOfertas= new JList<Oferta>();
		jScroll.setViewportView(listaOfertas);
		listaOfertas.setModel(model);
		
		setContentPane(contentPane);		
	}
	private void reload(BLFacade logica){
		model.clear();
		driver= logica.getDriver(driver.getEmail());
		for(Oferta o: driver.getOfertas()) {
			model.addElement(o);
		}
	}
}
