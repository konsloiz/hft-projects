package eg11.producer_consumer_with_view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


@SuppressWarnings("serial")
public class ActorCanvas extends Canvas{
	
	private QueueView parent;
	private Actor actor;

	public ActorCanvas(String title, Actor actor, List<Product> products, QueueView parent){
		this(title, actor, products, TOP, parent);
	}
	
	public ActorCanvas(String title, Actor actor, List<Product> products, int alignment, QueueView parent){
		super(title, actor, products, alignment);
		this.actor = actor;
		this.parent = parent;
		this.addMouseListener();

	}
	
	private void addMouseListener(){
		
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(parent.getSelectedCanvas() != null)
					parent.getSelectedCanvas().setBackground(Color.WHITE);
				if(parent.getSelectedCanvas() != ActorCanvas.this){
					parent.setSelectedCanvas(ActorCanvas.this);
					parent.setSelectedActor(actor);
					setBackground(Color.LIGHT_GRAY);
				} else {
					parent.setSelectedCanvas(null);
					parent.setSelectedActor(null);
				}
				if(parent.getSelectedActor() == null)
					parent.setButtons(false, false, false);
				else if(parent.getSelectedActor().isRunning())
					parent.setButtons(false, true, true);
				else
					parent.setButtons(true, false, true);
			}
		});			

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString(Long.toString(actor.getSleepTime()) + " ms", 20, 35);
	}
	
	

}
