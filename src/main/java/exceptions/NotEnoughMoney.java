package exceptions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class NotEnoughMoney extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public NotEnoughMoney() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insuficient Balance");
		lblNewLabel.setBounds(174, 89, 90, 57);
		panel.add(lblNewLabel);
		
		
		
	}

}
