package original;

public class Main {
	public static void main(String[] args) {
		Queue queue = new Queue();
		NamedProducer producer1 = new NamedProducer("P1", queue);
		new Thread(producer1).start();
		NamedProducer producer2 = new NamedProducer("P2", queue);
		new Thread(producer2).start();
		NamedConsumer consumer1 = new NamedConsumer("C1", queue);
		new Thread(consumer1).start();
		NamedConsumer consumer2 = new NamedConsumer("C2", queue);
		new Thread(consumer2).start();
	}
}