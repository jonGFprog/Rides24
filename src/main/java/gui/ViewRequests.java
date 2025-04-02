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
	
	
	
	public ViewRequests (Ride r) {
		
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
		if (selected>-1) {
			bL.setEstado("Aceptado", this.selectionOrder.get(selected));
		}
		
		reload();
		
		
	}
	
	private void jButtonDecline_ActionPerformed (ActionEvent e) {
		int selected = this.requestList.getSelectedIndex();
		BLFacade bL= MainGUI.getBusinessLogic();
		if (selected>-1) {
			bL.setEstado("Denegado", this.selectionOrder.get(selected));
		}
		
		reload();
	}
	
	public void reload() {
		Ride newRide= null;
		Driver d= null;
		BLFacade bL= MainGUI.getBusinessLogic();
		
		d = bL.getDriver(miRide.getDriver().getEmail());
		newRide= d.findSame(miRide);
		model.removeAllElements();
		while (selectionOrder.size()!= 0) {
			selectionOrder.removeFirst();
		}
		
		for (Solicitud i : bL.getAllRequests(newRide)) {
			selectionOrder.add(i);
			model.addElement(i.RequestToStringPlusState());
		}
		
		
		
		
	}
}
