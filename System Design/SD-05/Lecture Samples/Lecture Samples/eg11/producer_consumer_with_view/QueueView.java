package eg11.producer_consumer_with_view;

import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;
import static java.awt.Color.MAGENTA;
import static java.awt.Color.ORANGE;
import static java.awt.Color.PINK;
import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class QueueView extends JFrame{
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int FIELD_LENGTH = 10;
	
	private static int producerNo = 0;
	private static int consumerNo = 0;
	private static final Color[] COLORS = {YELLOW, RED, ORANGE, BLUE, GREEN, PINK, MAGENTA};
	
	private JTextField input;

	private JButton start, stop, time;

	private Queue queue;		
	
	private ActorCanvas selectedCanvas = null;
	private Actor selectedActor = null;


	public QueueView(){
		super("TestApplication");
		
		queue = new Queue(50);
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel(new BorderLayout());
		
		final JPanel producers = new JPanel(new GridLayout(1, 2));
		final JPanel consumers = new JPanel(new GridLayout(1, 2));

		JPanel scenarios = new JPanel(new GridLayout(3, 1));
		scenarios.add(producers);
		scenarios.add(new Canvas("Queue", queue, queue.getProducts(), Canvas.MIDDLE));
		scenarios.add(consumers);
		
		panel.add(scenarios, BorderLayout.CENTER);
		

		JPanel control = new JPanel();
		control.setBorder(BorderFactory.createTitledBorder("Control"));
		
		JButton addProducer = new JButton("Add Producer");
		addProducer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {			
				NamedProducer producer = new NamedProducer("A Producer", NamedProducer.START_TIME, queue, COLORS[producerNo % COLORS.length]);
				producerNo++;
				producers.add(new ActorCanvas("Producer " + producerNo, producer, producer.getProducts(), QueueView.this));
				producers.revalidate();
			}			
		});
		
		control.add(addProducer);
		
		
		JButton addConsumer = new JButton("Add Consumer");
		addConsumer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {			
				NamedConsumer consumer = new NamedConsumer("A Consumer", NamedConsumer.START_TIME, queue);
				consumerNo++;
				consumers.add(new ActorCanvas("Consumer " + consumerNo, consumer, consumer.getProducts(), QueueView.this));
				consumers.revalidate();
			}			
		});
		
		control.add(addConsumer);

		
		start = new JButton("Start");
		control.add(start);
		stop = new JButton("Stop");
		control.add(stop);	

		//control.add(new JLabel("Time"));

		input = new JTextField(FIELD_LENGTH);
		//input.setText(Long.toString(Actor.START_TIME));
		time = new JButton("Set Time");
		control.add(time);
		
		time.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(selectedActor != null)
					try{
						long sleepTime = Long.parseLong(input.getText());
						selectedActor.setSleepTime(sleepTime);
					}catch(NumberFormatException e){
					}
			}			
		});
		control.add(input);
		
		
		panel.add(control, BorderLayout.SOUTH);
		
		this.setContentPane(panel);

		start.setEnabled(false);

		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				selectedActor.start();
				start.setEnabled(false);
				stop.setEnabled(true);
			}			
		});
		

		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				selectedActor.stop();
				stop.setEnabled(false);
				start.setEnabled(true);
			}			
		});
		
		
		panel.add(control, BorderLayout.SOUTH);
				
		this.setContentPane(panel);
		
		this.pack();  //Realize the components.
	    this.setVisible(true);
	}

	public ActorCanvas getSelectedCanvas() {
		return selectedCanvas;
	}

	public void setSelectedCanvas(ActorCanvas selectedCanvas) {
		this.selectedCanvas = selectedCanvas;
	}

	public Actor getSelectedActor() {
		return selectedActor;
	}

	public void setSelectedActor(Actor selectedActor) {
		this.selectedActor = selectedActor;
		if(selectedActor == null)
			input.setText("");
		else
			input.setText(Long.toString(selectedActor.getSleepTime()));
	}
	
	public void setButtons(boolean start, boolean stop, boolean time){
		this.start.setEnabled(start);
		this.stop.setEnabled(stop);
		this.time.setEnabled(time);
	}

	public static void main(String[] args){
		new QueueView();
	}
}
