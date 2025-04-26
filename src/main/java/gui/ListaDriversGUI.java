package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Business;
import domain.Driver;

public class ListaDriversGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Driver> jListDrivers;
	private DefaultListModel<Driver> model = new DefaultListModel<Driver>();
	private JScrollPane scroll= null;
	private JButton jButtonSelect=null;
	
	public ListaDriversGUI(Business b) {
		setBounds(100, 100, 450, 300);
		contentPane= new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jButtonSelect = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ListaDriversGUI.selectButton"));
	
		BLFacade businessLogic = MainGUI.getBusinessLogic();
		
		jListDrivers= new JList<Driver>();
		
		model.clear();
		for(Driver d: businessLogic.getBDrivers(b)) {					
			model.addElement(d);
		}
		
		jListDrivers.setModel(model);
		scroll = new JScrollPane(jListDrivers);
		contentPane.add(scroll, BorderLayout.CENTER);
		contentPane.add(jButtonSelect, BorderLayout.SOUTH);
		setContentPane(contentPane);
		
		jButtonSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(jListDrivers.getSelectedValue()!=null) {
					JFrame a = new CreateRideGUI(jListDrivers.getSelectedValue());
					a.setVisible(true);
				}
			}
		});
	}

}
