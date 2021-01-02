package de.stuttgart.hft.stock.server;

import de.stuttgart.hft.stock.communication.EntryPoint;

public class StartServerWithHazards {
	
	public static void main(String[] args) {
		System.out.println("Server with some Hazards\n");
		EntryPoint.setStability(0.9);
		StockServer server = new StockServer(); 
		server.start(8080, "/api", EntryPoint.class.getCanonicalName()); 
	}
}
