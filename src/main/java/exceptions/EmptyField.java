package exceptions;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmptyField extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public EmptyField() {
		
		this.setSize(200,200);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("           All fields are mandatory");
		panel.add(lblNewLabel);
		
		
		
	}

}
