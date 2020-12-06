package de.stuttgart.hft.stock.samples;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.stuttgart.hft.stock.models.Stock;
import de.stuttgart.hft.stock.models.Stocks;

public class SampleProvider {
	
	private Random r = new Random();
	private Map<String, Stock> samples = getSamples();
	
	private Stocks readSamples() {		
		try(InputStream stream = SampleProvider.class.getClassLoader().getResourceAsStream("stock.json");) {
			ObjectMapper mapper = new ObjectMapper();
		    Stocks stocks = mapper.readValue(stream, Stocks.class);
		    return stocks;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Map<String, Stock> getSamples(){
		Stocks stocks = readSamples();
		Map<String, Stock> map = new HashMap<>();
		if(stocks != null)
			for(Stock stock : stocks.data)
				map.put(stock.symbol, stock);
		return map;
	}
	
	private double nextGaussian(double mean, double stdDev) {
		return r.nextGaussian() * stdDev + mean;
	}

	private Stock updateSample(Stock stock) {
		// create a new sample by changing some values by random
		stock.last_trade_time = LocalDateTime.now();
		double newPrice = nextGaussian(stock.price, (stock.day_high - stock.day_low)/2);
		newPrice = ((int)(newPrice * 100))/100.0;
		if(newPrice <= 0)
			newPrice = 0.1;
		
		stock.price = newPrice;
		if(newPrice > stock._52_week_high) stock._52_week_high = newPrice;
		if(newPrice > stock.day_high) stock.day_high = newPrice;

		if(newPrice < stock._52_week_low) stock._52_week_low = newPrice;
		if(newPrice < stock.day_low) stock.day_low = newPrice;
		
		return stock;
	}

	public Stock getSample(String symbol) {
		for(String key : samples.keySet())
			if(key.equalsIgnoreCase(symbol))
				return updateSample(samples.get(key));
		return null;
	}
	
	public static void main(String[] args) {
		SampleProvider sp = new SampleProvider();
		for(Stock s : sp.samples.values())
			System.out.println(s);
	}
}
