package de.stuttgart.hft.stock.main;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

import de.stuttgart.hft.stock.flow.BasicSubscriber;
import de.stuttgart.hft.stock.flow.ColdPublisher;
import de.stuttgart.hft.stock.flow.TransformerFilter;
import de.stuttgart.hft.stock.models.Stock;

public class MainTransform {


	public static void main(String[] args) throws InterruptedException {
		System.out.println("Exercise 3.4: Cold Publisher with Error Handling and Filter");

		List<String> symbols = Arrays.asList("GOOGL", "AMZN", "INTC");
		Publisher<Stock> feed = new ColdPublisher(symbols);
		
		Processor<Stock, Stock> processor 
			= new TransformerFilter<>(s -> s.price >= 1000);
		
		Subscriber<Stock> subscriber = new BasicSubscriber<>();
		
		feed.subscribe(processor);
		processor.subscribe(subscriber);
	}

}
