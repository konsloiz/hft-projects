package eg03.executor;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main2 {
	
	private static String printDates(String name, long millis) {
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(name + ": " + new Date());
		}
		return name + " says <done>";
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();	System.err.println("Executor created");
		Future<String> future = executor.submit(() -> printDates("1", 500));			System.err.println("Thread 1 started");
		Thread.sleep(3000);	
		System.err.println("Doing something");
		
		String result = future.get();
		System.err.println("Result from thread: " + result);
		System.err.println("main finished");	
		executor.shutdown();
	}
}
