package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreCuentaField;
	private JPasswordField cuentaPasswordField;
	private JLabel RegisterLabel;
	private JLabel passwordLabel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombreCuentaField = new JTextField();
		nombreCuentaField.setBounds(121, 79, 175, 30);
		contentPane.add(nombreCuentaField);
		nombreCuentaField.setColumns(10);
		
		cuentaPasswordField = new JPasswordField();
		cuentaPasswordField.setBounds(121, 156, 175, 30);
		contentPane.add(cuentaPasswordField);
		
		JLabel nombreCuentaLabel = new JLabel("Nombre de la cuenta:");
		nombreCuentaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nombreCuentaLabel.setBounds(121, 49, 175, 20);
		contentPane.add(nombreCuentaLabel);
		
		RegisterLabel = new JLabel("Crear Cuenta");
		RegisterLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		RegisterLabel.setBounds(153, 10, 143, 29);
		contentPane.add(RegisterLabel);
		
		passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLabel.setBounds(121, 126, 175, 20);
		contentPane.add(passwordLabel);
		
		JRadioButton rdbtnDriver = new JRadioButton("Conductor");
		buttonGroup.add(rdbtnDriver);
		rdbtnDriver.setBounds(121, 212, 107, 21);
		contentPane.add(rdbtnDriver);
		
		JRadioButton rdbtnPasajero = new JRadioButton("Pasajero");
		buttonGroup.add(rdbtnPasajero);
		rdbtnPasajero.setBounds(230, 212, 103, 21);
		contentPane.add(rdbtnPasajero);
		
		JLabel lblNewLabel = new JLabel("Tipo de usuario:");
		lblNewLabel.setBounds(10, 216, 105, 13);
		contentPane.add(lblNewLabel);
	}
}
