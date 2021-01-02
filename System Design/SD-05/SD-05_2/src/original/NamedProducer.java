package original;


public class NamedProducer extends Actor implements Runnable {

	public NamedProducer(String name, Queue queue, long time) {
		super(name, queue, time);
	}


	@Override
	protected void action(String name, Queue queue) {
		queue.putProduct(new Product(name));
		
	}

}