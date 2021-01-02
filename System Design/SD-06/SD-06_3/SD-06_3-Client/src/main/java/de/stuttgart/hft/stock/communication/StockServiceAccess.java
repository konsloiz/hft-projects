package de.stuttgart.hft.stock.communication;

import java.util.function.Supplier;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.stuttgart.hft.stock.models.Stock;
import de.stuttgart.hft.stock.models.Stocks;

public final class StockServiceAccess {
	
	// See: https://www.worldtradingdata.com/
	// Sample call: https://api.worldtradingdata.com/api/v1/stock?symbol=SNAP,TWTR,VOD.L&api_token=YourApiKey
	
//	private static final String PATH = "https://api.worldtradingdata.com/api";
//	private static final String API_KEY = "Get your own key at: www.worldtradingdata.com"
	private static final String PATH = "http://localhost:8080/api";
	private static final String API_KEY = "will be ignored for local access";	

	private StockServiceAccess() {}

	private static Supplier<WebTarget> target = () -> ClientBuilder.newClient()
			.target(PATH)
			.path("v1");

	public static Stock fetch(String symbol) {
		Response response = target.get().path("stock")
				.queryParam("symbol", symbol.toUpperCase())
				.queryParam("api_token", API_KEY)
				.request().get();
		
		if(response.getStatus() != Status.OK.getStatusCode()) {
			System.err.println(response);
			return null;
		}
		
		Stocks stocks = response.readEntity(Stocks.class);
		if(stocks.data == null || stocks.data.length == 0)
			throw new RuntimeException(stocks.message);
		
		return stocks.data[0];
	}

	
	public static final String[] SYMBOLS = {"AAPL", "MSFT","NOK","IBM","HPQ","GOOGL","BB"};
	// Apple, Microsoft, Nokia, IBM, Hewlet-Packard, Google, BlackBerry

//	public static final String[] SYMBOLS = {"GOOGL"};
//	public static final String[] SYMBOLS = {"GOOGL","AMZN", "INTC"};
	// Google, Amazon, Intel

	public static void main(String[] args) {
		while(true)
			for(String symbol : SYMBOLS) {
				Stock stock = StockServiceAccess.fetch(symbol);
				System.out.println(stock);
			}
    }
}
