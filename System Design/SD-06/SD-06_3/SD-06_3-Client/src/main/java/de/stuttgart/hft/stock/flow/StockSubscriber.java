package de.stuttgart.hft.stock.flow;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class StockSubscriber<T> implements Subscriber<T> {
	protected Subscription subscription;
	private String name;

	public StockSubscriber() {
		this.name = "Subscriber";
	}

	public StockSubscriber(String name) {
		this.name = "Subscriber " + name;
	}

	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println(name + ": subscribed");
		subscription.request(1);
	}

	public void onNext(T item) {
		System.out.println(name + ": got " + item);
		subscription.request(1);
	}

	public void onError(Throwable t) {
		System.out.println(name + ": error " + t.toString());
	}

	public void onComplete() {
		System.out.println(name + ": done");
	}

	public String toString() {
		return name;
	}
}