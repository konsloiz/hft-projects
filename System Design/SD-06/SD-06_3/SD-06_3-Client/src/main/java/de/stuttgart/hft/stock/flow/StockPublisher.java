package de.stuttgart.hft.stock.flow;

import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import de.stuttgart.hft.stock.communication.StockServiceAccess;
import de.stuttgart.hft.stock.models.Stock;

public class StockPublisher implements Publisher<Stock> {

	private SubmissionPublisher<Stock> publisher;

	public StockPublisher(List<String> SYMBOLS) {
		System.out.println("Created ...");
		this.publisher = new SubmissionPublisher<>();
		new Thread(() -> fetch(SYMBOLS)).start();
	}

	private void fetch(List<String> symbols) {
		System.out.println("Ready to emit ...");
		try {
			while(true) {
				for(String symbol: symbols) {
					Stock stock = StockServiceAccess.fetch(symbol);
					if(stock == null) {
						publisher.close();
						return;
					}
					publisher.submit(stock);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			publisher.closeExceptionally(e);
		}
	}

	@Override
	public void subscribe(Subscriber<? super Stock> subscriber) {

		publisher.subscribe(subscriber);

	}
}