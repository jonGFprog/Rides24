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
	private ArrayList<Solicitud> selectionOrder = new ArrayList<Solicitud>();
	private JList<String> requestList= null;
	private DefaultListModel<String> model= new DefaultListModel<String>();
	private JScrollPane jScroll = null;
	private JPanel contentPane = null;
	private JButton jButtonAccept = null;
	private JButton jButtonDecline= null;
	
	
	public ViewRequests (Ride r) {
		
		BLFacade bL= MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));;
		if (bL.getAllRequests(r)!=null) {
			System.out.println(bL.getAllRequests(r).size());
		}
		System.out.println(bL.getAllRequests(r).size());
		for (Solicitud i : bL.getAllRequests(r)) {
			selectionOrder.add(i);
			model.addElement(i.toString());
		}
		
		jButtonAccept = new JButton();
		jButtonAccept.setText("Accept");
		jButtonAccept.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButtonAccept_ActionPerformed(e);
			}
		});
		
		jButtonDecline = new JButton();
		jButtonDecline.setText("Accept");
		jButtonDecline.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButtonDecline_ActionPerformed(e);
			}
		});
		
		requestList= new JList<String>();
		requestList.setModel(model);
		
		jScroll= new JScrollPane(requestList);
		contentPane.add(jScroll, BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		
		
	}
	
	private void jButtonAccept_ActionPerformed(ActionEvent e) {
		
	}
	
	private void jButtonDecline_ActionPerformed (ActionEvent e) {
		
	}
	

}
