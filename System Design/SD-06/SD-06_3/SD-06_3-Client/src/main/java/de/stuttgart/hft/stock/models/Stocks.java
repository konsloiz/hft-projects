package de.stuttgart.hft.stock.models;

public class Stocks{

	public String message;
	
	public int symbols_requested, symbols_returned;
	public Stock[] data;
			
	public Stocks() {}

	public Stocks(String message) {
		this.message = message;
	}
}
