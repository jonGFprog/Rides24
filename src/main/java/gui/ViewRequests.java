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
import domain.Pasajero;
import domain.Ride;
import domain.Solicitud;
import enviarCorreo.EnviarCorreo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.BorderLayout;

public class ViewRequests extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Solicitud> selectionOrder = new ArrayList<Solicitud>();
	private JList<String> requestList= null;
	private DefaultListModel<String> model= new DefaultListModel<String>();
	private JScrollPane jScroll = null;
	private JPanel contentPane = null;
	private JButton jButtonAccept = null;
	private JButton jButtonDecline= null;
	private Ride miRide= null;
	private MainGUI main= null;
	
	
	
	public ViewRequests (Ride r, MainGUI guardarmain) {
		main= guardarmain;
		BLFacade bL= MainGUI.getBusinessLogic();
		miRide= r;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();;
		if (bL.getAllRequests(r)!=null) {
			System.out.println(bL.getAllRequests(r).size());
		}
		System.out.println(bL.getAllRequests(r).size());
		for (Solicitud i : bL.getAllRequests(r)) {
			selectionOrder.add(i);
			model.addElement(i.RequestToStringPlusState());
		}
		
		jButtonAccept = new JButton();
		jButtonAccept.setText("Accept");
		jButtonAccept.setBounds(0, 213, 222, 48);
		jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
			
				jButtonAccept_ActionPerformed(e);
				}
			
		});
		contentPane.add(jButtonAccept);
		
		jButtonDecline = new JButton();
		jButtonDecline.setText("Decline");
		jButtonDecline.setBounds(221, 213, 213, 48);
		jButtonDecline.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButtonDecline_ActionPerformed(e);
			}
		});
		contentPane.add(jButtonDecline);
		contentPane.setLayout(null);
		
		jScroll= new JScrollPane();
		jScroll.setBounds(0, 0, 434, 213);
		contentPane.add(jScroll);
		
		requestList= new JList<String>();
		jScroll.setViewportView(requestList);
		requestList.setModel(model);
		
		setContentPane(contentPane);
		
		
		
		
		
		
	
		
		
		
		
	}
	
	private void jButtonAccept_ActionPerformed(ActionEvent e) {
		int selected = this.requestList.getSelectedIndex();
		BLFacade bL= MainGUI.getBusinessLogic();
		
		if (this.selectionOrder.get(selected).getEstado().equals("Pendiente")) {
			if (selected>-1) {
				main.setAccount(bL.setEstado("Aceptado", this.selectionOrder.get(selected)));
				EnviarCorreo mensaje= new EnviarCorreo(this.selectionOrder.get(selected).getPasajero().getEmail(),
						"Tu solicitud ("+ this.selectionOrder.get(selected).toString()+") ha sido aceptada.");
				
			}
			
			
			
			reload();
			
		}
		
		
		
	}
	
	private void jButtonDecline_ActionPerformed (ActionEvent e) {
		int selected = this.requestList.getSelectedIndex();
		BLFacade bL= MainGUI.getBusinessLogic();
		if (this.selectionOrder.get(selected).getEstado().equals("Pendiente")) {
			if (selected>-1) {
				main.setAccount(bL.setEstado("Rechazado", this.selectionOrder.get(selected)));
				bL.returnDeclinedSeat(this.selectionOrder.get(selected));
			}
			
			
			
			reload();
		}
		
		
	}
	
	public void reload() {
		Ride newRide= null;
		Driver d= null;
		BLFacade bL= MainGUI.getBusinessLogic();
		
		d = bL.getDriver(miRide.getDriver().getEmail());
		newRide= d.findSame(miRide);
		while(!model.isEmpty()) {
			model.remove(0);
		}
		
		while (selectionOrder.size()!= 0) {
			selectionOrder.remove(0);
			
		}
		System.out.println(bL.getAllRequests(newRide).size());
		for (Solicitud i : bL.getAllRequests(newRide)) {
			selectionOrder.add(i);
			model.addElement(i.RequestToStringPlusState());
		}
		
		
		
		
		
	}
}
