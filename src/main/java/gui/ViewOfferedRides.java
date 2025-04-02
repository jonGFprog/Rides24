package gui;


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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.BorderLayout;

public class ViewOfferedRides extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList<String> rideList= new JList<String>();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JButton jButtonSelect= null;
	
	
	private ArrayList<Ride> selectionOrder= new ArrayList<Ride>();
	private ArrayList<String> BookingArrayList = new ArrayList<String>();
	private JPanel contentPane = null;
	private JScrollPane scroll= null;
	
	
	public ViewOfferedRides (Driver d) {
		
		setBounds(100, 100, 450, 300);
		
		contentPane= new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jButtonSelect = new JButton("Select");
	
		
		
		
		BLFacade businessLogic = MainGUI.getBusinessLogic();
		
		System.out.println(businessLogic.getDRides(d).size());
		for(Ride i : businessLogic.getDRides(d)) {
			selectionOrder.add(i);
			BookingArrayList.add(i.toString());
		}
		
		System.out.println(BookingArrayList.size());
		for (int i=0; i<BookingArrayList.size(); i++) {
			model.add(i, BookingArrayList.get(i));;
			
		}
		
		jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (rideList.getSelectedIndex()>-1) {
					JFrame a = new ViewRequests(selectionOrder.get(rideList.getSelectedIndex()));
					
					a.setVisible(true);
				}
				
			}
		});
		
		rideList.setModel(model);
		scroll = new JScrollPane(rideList);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(jButtonSelect, BorderLayout.SOUTH);
		setContentPane(contentPane);
		
		
		
		
		
	}


}
