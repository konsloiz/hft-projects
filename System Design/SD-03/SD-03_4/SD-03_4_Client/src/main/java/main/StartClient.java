package main;

import communication.ServiceAccessor;
import view.BookView;

public class StartClient {
	
	public static void main(String[] args) {
		
		ServiceAccessor service = new ServiceAccessor();
		
		new BookView(service);
	}
}