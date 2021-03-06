package de.stuttgart.hft.stock.flow;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Predicate;

public class FilterProcessor<T> extends SubmissionPublisher<T> implements Processor<T, T> {

	private Predicate<? super T> predicate;
	private Subscription subscription;

	public FilterProcessor(Predicate<? super T> predicate) {
		super();
		this.predicate = predicate;
	}

	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	public void onNext(T item) {
		if (predicate.test(item)) {
			submit(item);
		}
		subscription.request(1);
	}

	public void onError(Throwable t) {
		System.out.println("Transformer: error " + t.toString());
	}

	public void onComplete() {
		close();
	}
}