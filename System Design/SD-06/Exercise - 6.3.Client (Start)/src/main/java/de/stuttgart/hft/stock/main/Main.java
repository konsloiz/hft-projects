package de.stuttgart.hft.stock.main;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

import de.stuttgart.hft.stock.flow.BasicSubscriber;
import de.stuttgart.hft.stock.flow.StockPublisher;
import de.stuttgart.hft.stock.models.Stock;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		List<String> symbols = Arrays.asList("GOOGL","AMZN", "INTC"); 
		
		Publisher<Stock> feed = new StockPublisher(symbols);
		Subscriber<Stock> subscriber = new BasicSubscriber<>();
		
		feed.subscribe(subscriber);
	}

}
