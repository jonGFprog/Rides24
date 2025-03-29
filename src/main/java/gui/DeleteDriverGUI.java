package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Business;
import domain.Driver;
import domain.Ride;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class DeleteDriverGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Driver> jListDrivers;
	private DefaultListModel<Driver> model = new DefaultListModel<Driver>();
	private JScrollPane scroll= null;
	private JButton jButtonSelect=null;


	/**
	 * Create the frame.
	 */
	public DeleteDriverGUI(Business b) {
	setBounds(100, 100, 450, 300);
		
		contentPane= new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jButtonSelect = new JButton("Delete");//Poner traduciones
	
		
		
		
		BLFacade businessLogic = MainGUI.getBusinessLogic();
		
		
		
		jListDrivers= new JList<Driver>();
		for(int i=0;i<30;i++) {
			for(Driver d: b.getDrivers()) {					
				model.addElement(d);
			}
		}
			
			
			
		
		
		jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
			}
		});
		
		jListDrivers.setModel(model);
		scroll = new JScrollPane(jListDrivers);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(jButtonSelect, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}

}
