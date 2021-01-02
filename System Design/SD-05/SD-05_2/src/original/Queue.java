package original;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	public static final int MAXQUEUE = 5;
	private List<Product> products = new ArrayList<>();

//called by the Producer
	public synchronized void putProduct(Product product) {
		while (products.size() >= MAXQUEUE)
			try {
				wait();
			} catch (InterruptedException e) {
			}
		products.add(product);
		notifyAll();
	}

//called by the Consumer
	public synchronized Product getProduct() {
		while (products.size() == 0)
			try {
				notifyAll();
				wait();
			} catch (InterruptedException e) {
			}
		Product product = products.remove(0);
		notifyAll();
		return product;
	}
}