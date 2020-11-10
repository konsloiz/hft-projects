package app;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;

//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


@SuppressWarnings("serial")
public class Displayer extends JFrame{
	
	private static final int MIN_HEIGHT = 400;
	private static final int MIN_WIDTH = 600;
	
	private static final int FIELD_HEIGHT = 25;
	private static final int FIELD_WIDTH = 200;

	private Channel channel;
	
	private JTextArea text;
	private JTextField timeField;
	

	private class UpdateThread extends Thread {
		
		private final long SLEEP_TIME = 500;
		
	    public void run() {
	    	while (true){	    		
		    	//Sleeping
		        try {
		          Thread.sleep(SLEEP_TIME);
		        } catch (InterruptedException x) { // ignore
		        }		        
			    //Updating
		        timeField.setText(new Date().toString());
		        
				List<String> messages = channel.getMessages();
				if(messages != null && !messages.isEmpty())
					for(String msg : messages)
						text.append("\t" + msg + "\n");

	    	}
	    }
	}

	public Displayer(){
		super("Displayer");
		
		channel = new Channel();
		
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// Output-Pane for displaying Messages		
		text = new JTextArea();		
		JScrollPane textHolder = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
														ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(textHolder, BorderLayout.CENTER);
		
		JPanel control = new JPanel();

//		// Button for triggering the Channel
//		JButton display = new JButton("Display");
//		display.addActionListener(event ->{
//					text.append("Button pressed:\n");
//					List<String> messages = channel.getMessages();
//					if(messages != null && !messages.isEmpty())
//						for(String msg : messages)
//							text.append("\t" + msg + "\n");
//					else
//						text.append("\tno messages\n");
//			});
//		control.add(display);
		
		// Field for displaying time
		timeField = new JTextField("Current Time");
		timeField.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		control.add(timeField);
		UpdateThread updater = new UpdateThread();
		updater.start();
		
		panel.add(control, BorderLayout.SOUTH);

		this.setContentPane(panel);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();

		this.setLocationByPlatform(true);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new Displayer();
	}

}
