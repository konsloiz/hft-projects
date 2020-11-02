package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class View extends JFrame {
	
	private JTextArea output = new JTextArea();
	
	public View() {
		super("Server View");
		this.setPreferredSize(new Dimension(400, 300));		
		this.setContentPane(new JScrollPane(output));		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		this.pack();
		this.setVisible(true);
	}
}