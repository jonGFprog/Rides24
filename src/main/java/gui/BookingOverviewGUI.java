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
import domain.Solicitud;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import java.awt.BorderLayout;

public class BookingOverviewGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList<String> BookingJList = null;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	
	private JTable bookingTable = new JTable();
	private DefaultTableModel tableModelRides= new DefaultTableModel();
	
	
	private ArrayList<String> BookingArrayList = new ArrayList<String>();
	private JPanel contentPane = null;
	private JScrollPane scroll= null;
	
	
	public BookingOverviewGUI(Pasajero d) {
		
		
		BLFacade businessLogic = MainGUI.getBusinessLogic();
		
		Pasajero miUser= businessLogic.getPasajaero(d.getEmail());
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		BookingJList= new JList<String>();
		
		System.out.println(businessLogic.getPRides(miUser).size());
		for(Solicitud i : businessLogic.getPRides(miUser)) {
			BookingArrayList.add(i.RequestedRideToStringPlusState());
		}
		
		System.out.println(BookingArrayList.size());
		for (int i=0; i<BookingArrayList.size(); i++) {
			model.add(i, BookingArrayList.get(i));;
			
		}
		contentPane.setLayout(new BorderLayout(0, 0));
		BookingJList.setModel(model);
		scroll= new JScrollPane(BookingJList);

		
		
		contentPane.add(scroll);
		
		setContentPane(contentPane);
		
		
		
		
		
		
		
		
	}

}
