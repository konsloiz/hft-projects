package view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class View extends JFrame implements PropertyChangeListener {
	
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		output.append(new Date() + ": " + evt.getNewValue() + "\n");
		
	}
}