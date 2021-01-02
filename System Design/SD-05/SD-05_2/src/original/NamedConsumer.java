package original;


public class NamedConsumer extends Actor implements Runnable {

	public NamedConsumer(String name, Queue queue, long time) {
		super(name, queue, time);

	}

	@Override
	protected void action(String name, Queue queue) {

		Product product = queue.getProduct();
		System.out.println(name + " got product; " + product);

	}

}