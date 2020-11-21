package eg02.lambda;

import java.util.Date;

public class Main1 {
	
	private static void printDates() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(new Date());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> printDates());	System.err.println("Thread created");
		t.start();									System.err.println("Thread started");
		Thread.sleep(500);
		Thread t2 = new Thread(() -> printDates());	System.err.println("Thread 2 created");
		t2.start();									System.err.println("Thread 2 started");
		Thread.sleep(3000);							System.err.println("main finished");	
	}

}
