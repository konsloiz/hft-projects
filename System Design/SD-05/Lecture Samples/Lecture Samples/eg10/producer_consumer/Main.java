package eg10.producer_consumer;

public class Main {
	
	public static void main(String[] args){
		Queue queue = new Queue();
		
		NamedProducer producer1 = new NamedProducer("One", queue);
		new Thread(producer1).start();
		NamedProducer producer2 = new NamedProducer("Two", queue);
		new Thread(producer2).start();
		NamedProducer producer3 = new NamedProducer("Three", queue);
		new Thread(producer3).start();
		NamedConsumer consumer1 = new NamedConsumer("One", queue);
		new Thread(consumer1).start();
		NamedConsumer consumer2 = new NamedConsumer("Two", queue);
		new Thread(consumer2).start();
	}
}
