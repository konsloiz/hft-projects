package refactored;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		Queue queue = new Queue();
		
		ExecutorService executor = Executors.newCachedThreadPool();
	
		NamedProducer producer1 = new NamedProducer("P1", queue, 1000);
		executor.execute(producer1);
		NamedProducer producer2 = new NamedProducer("P2", queue, 1000);
		executor.execute(producer2);
		NamedConsumer consumer1 = new NamedConsumer("C1", queue, 2000);
		executor.execute(consumer1);
		NamedConsumer consumer2 = new NamedConsumer("C2", queue, 2000);
		executor.execute(consumer2);
		
	}
}