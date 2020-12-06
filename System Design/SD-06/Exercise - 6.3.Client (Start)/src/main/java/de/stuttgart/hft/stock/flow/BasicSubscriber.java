package de.stuttgart.hft.stock.flow;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class BasicSubscriber<T> implements Subscriber<T>{

	protected Subscription subscription;
	private String name;

	public BasicSubscriber() {
		this.name = "Subscriber";
	}

	public BasicSubscriber(String name) { 
	 this.name = "Subscriber " + name; 
	 }

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.out.println(name + ": subscribed");
		subscription.request(1);
	}

	@Override
	public void onNext(T item) {
		System.out.println(name + ": got " + item);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		System.out.println(name + ": error " + t.toString());
	}

	@Override
	public void onComplete() {
		System.out.println(name + ": done");
	}

	@Override
	public String toString() {
		return name;
	}
}
