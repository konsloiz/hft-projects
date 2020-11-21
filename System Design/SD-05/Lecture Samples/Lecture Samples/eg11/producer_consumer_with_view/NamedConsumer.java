package eg11.producer_consumer_with_view;

public class NamedConsumer extends Actor {
	
	public static final long START_TIME = 2000;

	public NamedConsumer(String name, long sleepTime, Queue queue) {
		super(name, sleepTime, queue);
	}

	@Override
	public void act() {
		Product product = this.getQueue().getProduct();
//		System.out.println(name + " got message; " + product.getMessage());
		this.getProducts().add(0, product);
		this.setChanged();
		this.notifyObservers(this.getSleepTime());
	}	
}
