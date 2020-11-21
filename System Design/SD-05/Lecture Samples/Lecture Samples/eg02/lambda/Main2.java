package eg02.lambda;

import java.util.Date;

public class Main2 {
	
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(() -> {while(true) {sleep(2000); System.out.println(new Date());}});			
												System.err.println("Thread created");
		t.start();								System.err.println("Thread started");
		sleep(3000);							System.err.println("main finished");		
	}

}
