package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Driver;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;

import javax.swing.JSpinner;

public class RegistrarVehiculoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMarca;
	private JTextField textModelo;
	private File selectedFile=null;
	private JButton btnRegistrarVehiculo;
	private JLabel lblTitulo;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblPlazas;
	private JSpinner sprPlazas;
	private JLabel lblImgInfo;
	
	private JButton btnSelectFoto;
	
	public RegistrarVehiculoGUI(Driver d) {
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegistrarVehiculo = new JButton("Registrar vehiculo");
		btnRegistrarVehiculo.setBounds(141, 249, 152, 27);
		contentPane.add(btnRegistrarVehiculo);
		
		lblTitulo = new JLabel("Registrar vehiculo");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 19));
		lblTitulo.setBounds(129, 46, 195, 35);
		contentPane.add(lblTitulo);
		
		textMarca = new JTextField();
		textMarca.setColumns(10);
		textMarca.setBounds(141, 93, 153, 21);
		contentPane.add(textMarca);
		
		textModelo = new JTextField();
		textModelo.setColumns(10);
		textModelo.setBounds(141, 126, 153, 21);
		contentPane.add(textModelo);
		
		lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(49, 93, 74, 17);
		contentPane.add(lblMarca);
		
		lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(49, 128, 83, 17);
		contentPane.add(lblModelo);
		
		btnSelectFoto = new JButton("Seleccionar foto");
		btnSelectFoto.setBounds(141, 202, 153, 27);
		contentPane.add(btnSelectFoto);
		
		lblPlazas = new JLabel("nº de plazas:");
		lblPlazas.setBounds(49, 163, 83, 17);
		contentPane.add(lblPlazas);
		
		sprPlazas = new JSpinner();
		sprPlazas.setBounds(141, 159, 152, 22);
		contentPane.add(sprPlazas);
		
		lblImgInfo = new JLabel("");
		lblImgInfo.setFont(new Font("Dialog", Font.BOLD, 10));
		lblImgInfo.setBounds(141, 230, 297, 17);
		contentPane.add(lblImgInfo);
		
		
		JFileChooser fc= new JFileChooser();
		fc.setDialogTitle("Elige la foto de tu vehiculo");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".png")|| f.isDirectory();
            }
            
            public String getDescription() {
                return "Archivos de imagen (*.png)";
            }
        });
		
		btnSelectFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnValue = fc.showOpenDialog(btnSelectFoto);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
				    selectedFile = fc.getSelectedFile();
				    lblImgInfo.setText(fc.getName(selectedFile));;
				}
			}});
		
		btnRegistrarVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sprPlazas.commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				if(!((Integer)sprPlazas.getValue()<=0||selectedFile==null||textMarca.getText().isBlank()||textModelo.getText().isBlank())) {	
					
					facade.registrarVehiculo(selectedFile,textMarca.getText(),textModelo.getText(), d.getEmail(),(Integer)sprPlazas.getValue());
				}
				else {
					lblTitulo.setText("Faltan datos");
				}
			}});
	}
}
