package eg08.control_with_view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class View extends JFrame implements PropertyChangeListener {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	
	private JTextArea outputArea;
	private JTextField timeField;
	private JButton runButton;
	
	private Controlled model;
	
	public View(){
		super("View");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		outputArea = new JTextArea();
		timeField = new JTextField(20);
		runButton  = new JButton("Start/Stop");
		
		model = new Controlled();
		model.addObserver(this);
		
		initializeWidgets();
		JPanel content = createWidgetLayout();		
		createWidgetInteraction();

		this.setContentPane(content);
			
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private void initializeWidgets() {
		timeField.setText(Long.toString(model.getSleepTime()));
	}

	private JPanel createWidgetLayout() {
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane pane = new JScrollPane(outputArea);
		panel.add(pane, BorderLayout.CENTER);
		
		JPanel control = new JPanel();
		control.add(runButton);
		control.add(timeField);
		
		panel.add(control, BorderLayout.SOUTH);	
		
		return panel;
	}

	private void createWidgetInteraction() {
		timeField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				String entry = timeField.getText();
				Long time = null;
				try {
					time = Long.parseLong(entry);
				} catch (NumberFormatException e) {}
				if(time != null && time >= 0)
					model.setSleepTime(time);				
			}			
		});
		
		runButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				if(model.isRunning())
					model.stop();
				else
					model.start();
			}
			
		});
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String value = evt.getNewValue().toString();
		outputArea.insert(value + "\n", 0);
	}

	
	public static void main(String[] args){
		new View();
	}

}
