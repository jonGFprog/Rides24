package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Business;
import domain.Driver;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class DeleteDriverGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> jListDrivers;
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JScrollPane scroll= null;


	/**
	 * Create the frame.
	 */
	public DeleteDriverGUI(Business b) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		for(int i=0;i<30;i++) {
		for(Driver d: b.getDrivers()) {
			String s="Email: "+ d.getEmail()+" Nombre: "+d.getName();
			
			model.addElement(s);
		}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		jListDrivers = new JList<String>();
		contentPane.add(jListDrivers);
		contentPane.add(scrollPane);
		scroll = new JScrollPane(jListDrivers);
		
		jListDrivers.setModel(model);
	}

}
