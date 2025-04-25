package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Driver;
import domain.Vehiculo;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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

	
	public InfoVehiculoGUI(Vehiculo v) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelInfo = new JPanel();
		contentPane.add(panelInfo, BorderLayout.SOUTH);
		panelInfo.setLayout(new GridLayout(2, 2, 0, 0));
		
		
		
		lblMarca = new JLabel("Marca: "+ v.getMarca());
		panelInfo.add(lblMarca);
		
		lblModelo = new JLabel("Modelo: "+ v.getModelo());
		panelInfo.add(lblModelo);
		
		lblPlazas = new JLabel("Asientos: "+ v.getPlazas());
		panelInfo.add(lblPlazas);
		
		lblIdentificador = new JLabel("Identificador: "+v.getVehiculoNumber());
		panelInfo.add(lblIdentificador);
		this.setVisible(true);
		mostrarImagen(contentPane, v.getFoto());
		
	}
	
	
	
	public static void mostrarImagen(JPanel panelContenedor, BufferedImage imagenOriginal) {
	 	
	    int panelAncho = panelContenedor.getWidth();
	    int panelAlto = panelContenedor.getHeight();
	    
	    double imgRatio = (double)imagenOriginal.getWidth() / imagenOriginal.getHeight();
	    double panelRatio = (double)panelAncho / panelAlto;
	    
	    int nuevoAncho, nuevoAlto;
	    
	    if (panelRatio > imgRatio) {
	        nuevoAlto = panelAlto;
	        nuevoAncho = (int)(nuevoAlto * imgRatio);
	    } else {
	        nuevoAncho = panelAncho;
	        nuevoAlto = (int)(nuevoAncho / imgRatio);
	    }
		Image imagenRedimensionada = imagenOriginal.getScaledInstance(nuevoAncho,nuevoAlto,Image.SCALE_SMOOTH); 
		
		JLabel lblImg = new JLabel(new ImageIcon(imagenRedimensionada));
		panelContenedor.add(lblImg, BorderLayout.CENTER);
		
		panelContenedor.revalidate();
		panelContenedor.repaint();
	}
	
}
