package eg10.producer_consumer;


public class NamedProducer implements Runnable {
	
	@SuppressWarnings("unused")
	private String name;
	private Queue queue;

	public NamedProducer(String name, Queue queue) {
		super();
		this.name = name;
		this.queue = queue;
	}



	@Override
	public void run() {
		while(true){
			queue.putMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	
}
