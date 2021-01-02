package de.stuttgart.hft.stock.server;

import de.stuttgart.hft.stock.communication.EntryPoint;

public class StartServer {
	
	public static void main(String[] args) {
		System.out.println("Regular Server\n");
		StockServer server = new StockServer(); 
		server.start(8080, "/api", EntryPoint.class.getCanonicalName()); 
	}
}
