package eg11.producer_consumer_with_view;

import java.awt.Color;
import java.util.Date;

public class NamedProducer extends Actor {
	
	public static final long START_TIME = 1000;
	private Color color;
	
	public NamedProducer(String name, long sleepTime, Queue queue, Color color) {
		super(name, sleepTime, queue);
		this.color = color;
	}
	
	@Override
	public void act() {
			Product product = new Product(new Date().toString(), color);
			this.getProducts().add(product);
			this.setChanged();
			this.notifyObservers();
			try {
				Thread.sleep(this.getSleepTime());
//				Thread.sleep(Math.min(this.getSleepTime() / 2, 500));
			} catch (InterruptedException e) {}
			this.getProducts().remove(product);
			this.setChanged();
			this.notifyObservers(this.getSleepTime());
			
			this.getQueue().putProduct(product);
	}


}
