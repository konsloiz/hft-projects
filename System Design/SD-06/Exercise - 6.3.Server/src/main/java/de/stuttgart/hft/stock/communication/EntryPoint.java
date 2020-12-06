package de.stuttgart.hft.stock.communication;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.stuttgart.hft.stock.models.Stock;
import de.stuttgart.hft.stock.models.Stocks;
import de.stuttgart.hft.stock.samples.SampleProvider;

@Path("v1") // Root resource 
public class EntryPoint { 
 
	private static double stability = 1.0;
	private static int gone = 0;

	private SampleProvider sample = new SampleProvider();
	
	public static void setStability(double stability) {
		if(stability < 0 || stability > 1) {
			System.err.println("Stability must be between 0 and 1.0 - set to 1.0.");
			stability = 1.0;
		}
		EntryPoint.stability = stability;
	}

	@GET 
	@Path("stock") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response stocks(
				@QueryParam("symbol") String symbolStr, 
				@QueryParam("api_token") String apiKey) {
		
		if (gone()) return goneResponse();
		if (bad()) return badResponse();
				
		String[] symbols = new String[0];
		if(symbolStr != null)
			symbols = symbolStr.split(",\\s*");
		List<Stock> stockList = new ArrayList<>();
		for(String symbol : symbols) {
			Stock stock = sample.getSample(symbol);
			if(stock != null)
				stockList.add(stock);
		}
		
		Stocks stocks = new Stocks();
		stocks.symbols_requested = symbols.length;
		stocks.symbols_returned = stockList.size();
		stocks.data = stockList.toArray(new Stock[stocks.symbols_returned]);

		return Response.ok(stocks).build(); 
	}

	private boolean gone() {
		if(gone > 0) {
			gone--;
			return true;
		}
		
		// gone == 0 
		if(Math.random() > stability) {
			gone = 10;
			return true;
		}
		
		return false;
	}
	
	private boolean bad() {
		return Math.random() > stability;
	}
	
	private Response goneResponse() {
		return Response.status(Status.GONE).build();
	}

	private Response badResponse() {
		return Response.ok(new Stocks("Something went wrong")).build();
	} 
} 