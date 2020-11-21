package eg03.executor;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
	
	private static void printDates(String name, long millis) {
		while(true) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(name + ": " + new Date());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newCachedThreadPool();	System.err.println("Executor created");
		executor.execute(() -> printDates("1", 2000));			System.err.println("Thread 1 started");
		executor.execute(() -> printDates("2", 1500));			System.err.println("Thread 2 started");
		Thread.sleep(3000);										System.err.println("main finished");		
	}

}
