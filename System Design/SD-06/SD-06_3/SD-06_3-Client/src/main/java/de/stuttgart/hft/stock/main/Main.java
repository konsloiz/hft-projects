package de.stuttgart.hft.stock.main;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

import de.stuttgart.hft.stock.flow.FilterProcessor;
import de.stuttgart.hft.stock.flow.StockPublisher;
import de.stuttgart.hft.stock.flow.StockSubscriber;
import de.stuttgart.hft.stock.models.Stock;

public class Main {

	public static void main(String[] args) {

		List<String> symbols = Arrays.asList("GOOGL", "AMZN", "INTC");
		Publisher<Stock> feed = new StockPublisher(symbols);
		Subscriber<Stock> subscriber = new StockSubscriber<>();
		Processor<Stock, Stock> processor = new FilterProcessor<>(s -> s.price >= 1000);
		
		feed.subscribe(processor);
		processor.subscribe(subscriber);
		

	}

}
