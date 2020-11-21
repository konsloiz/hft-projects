package eg11.producer_consumer_with_view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Queue  extends Observable {
	
	private int maxqueue;
	private List<Product> products = new ArrayList<Product>();
	
	public Queue(int maxqueue) {
		super();
		this.maxqueue = maxqueue;
	}

	public List<Product> getProducts() {
		return products;
	}

	//called by the Producer
	public synchronized void putProduct(Product product) {
		while(products.size() >= maxqueue)
			try {
				wait();
			} catch (InterruptedException e) {}
		products.add(0, product);
		this.setChanged();
		this.notifyObservers();
		notifyAll();
	}

	//called by the Consumer
	public synchronized Product getProduct() {
		while(products.size() == 0)
			try {
				notifyAll();
				wait();
			} catch (InterruptedException e) {}
		Product product = products.remove(products.size() - 1);
		this.setChanged();
		this.notifyObservers();
		notifyAll();
		return product;
	}



}
