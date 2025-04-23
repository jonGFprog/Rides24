package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Driver;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class InfoVehiculoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelInfo;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblPlazas;
	private JLabel lblIdentificador;
	
	public InfoVehiculoGUI(Driver d) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelInfo = new JPanel();
		contentPane.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new GridLayout(2, 2, 0, 0));
		
		lblMarca = new JLabel("Marca");
		panelInfo.add(lblMarca);
		
		lblModelo = new JLabel("Modelo");
		panelInfo.add(lblModelo);
		
		lblPlazas = new JLabel("Asientos");
		panelInfo.add(lblPlazas);
		
		lblIdentificador = new JLabel("Identificador");
		panelInfo.add(lblIdentificador);
	}

}
