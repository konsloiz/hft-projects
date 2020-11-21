package eg02.lambda;

import java.util.Date;

public class Main3 {
	
	private static void printDates() {
		for(int i = 0; i < 10; i++) {
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
		Thread.sleep(3000);							System.err.println("main is doing something else");
		t.join();									System.err.println("main finished");
	}

}
