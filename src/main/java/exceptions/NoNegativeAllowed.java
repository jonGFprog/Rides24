package exceptions;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoNegativeAllowed extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public NoNegativeAllowed() {
		
		this.setSize(200,200);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("           Amount must not be negative");
		panel.add(lblNewLabel);
		
		
		
	}
}
