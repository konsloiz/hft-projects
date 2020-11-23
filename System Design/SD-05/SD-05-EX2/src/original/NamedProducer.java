package original;

import java.util.Date;

public class NamedProducer implements Runnable {
	private String name;
	private Queue queue;

	public NamedProducer(String name, Queue queue) {
		super();
		this.name = name;
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			queue.putMessage(new Date().toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}