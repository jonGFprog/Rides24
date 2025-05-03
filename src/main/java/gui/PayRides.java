package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import businessLogic.BLFacade;
import domain.Pasajero;
import domain.Ride;
import domain.UsuarioRegistrado;
import domain.Solicitud;

public class PayRides extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> rideList= new JList<String>();
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JButton jButtonSelect= null;
	
	
	private ArrayList<Solicitud> selectionOrder= new ArrayList<Solicitud>();
	private ArrayList<String> BookingArrayList = new ArrayList<String>();
	private JPanel contentPane = null;
	private JScrollPane scroll= null;
	
	private Pasajero updatedUser;
	
	
	public PayRides(Pasajero user, MainGUI guardarMain) {
		setBounds(100, 100, 450, 300);
		
		contentPane= new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jButtonSelect = new JButton("Pay");
	
		
		
		
		BLFacade businessLogic = MainGUI.getBusinessLogic();
		
		System.out.println(user.getSolicitudesAceptadas().size());
		for(Solicitud i : user.getSolicitudesAceptadas()) {
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
					updatedUser= businessLogic.payRide(selectionOrder.get(rideList.getSelectedIndex()));
					guardarMain.setDriver(user);
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
