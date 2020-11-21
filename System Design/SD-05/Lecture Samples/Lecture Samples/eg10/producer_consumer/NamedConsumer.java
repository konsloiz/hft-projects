package eg10.producer_consumer;

public class NamedConsumer implements Runnable {
	
	private String name;
	private Queue queue;

	public NamedConsumer(String name, Queue queue) {
		super();
		this.name = name;
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true){
			String message = queue.getMessage();
			System.out.println(name + " got message; " + message);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {}
		}
	}
	
}
