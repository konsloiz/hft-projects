package src.de.stuttgart.hft.sd.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceAccess {

	private String doRequest(String request) {
		
		String response = null;
		HttpURLConnection connection = null;
	
	    try {
	        URL requestURL = new URL(request);
	        connection = (HttpURLConnection) requestURL.openConnection();
	        
	        connection.setRequestMethod("GET");
	        connection.setUseCaches(false);
	        connection.connect();
	                   
	        boolean httpOk = connection.getResponseCode() == HttpURLConnection.HTTP_OK;
			try(InputStream stream = (httpOk ? connection.getInputStream() : connection.getErrorStream());
				Reader reader = new InputStreamReader(stream); 
				BufferedReader buffer = new BufferedReader(reader)){
	        	
				StringBuilder sb = new StringBuilder();
				String line = buffer.readLine();
				while(line != null) {
					sb.append(line).append('\n');
					line = buffer.readLine();
				}
				if(httpOk) 
					response = sb.substring(0, sb.length() - 1);
				else {
	                System.err.println("Bad Response: " + response + "\n");
	                response = null;
				}
				
	        } catch (IOException e) {
	            System.err.println("Error: " + e.getMessage());
	        }
	
	
	    } catch (IOException e) {
	        System.err.println("Error: " + e.getMessage());
	    } finally {
	        if (connection != null)
	            connection.disconnect();
	    }
	    
	    return response;
	}

	public JSONObject requestObject(String request) {
		String result = this.doRequest(request);
		if(result == null) return null;
		return new JSONObject(result);
	}

	public JSONArray requestArray(String request) {
		String result = this.doRequest(request);
		if(result == null) return null;
		return new JSONArray(result);
	}
}
