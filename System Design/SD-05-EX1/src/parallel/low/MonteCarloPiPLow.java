package parallel.low;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloPiPLow {
	
	private static int hits = 0;
	
	private static double random() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static void main(String[] args) throws InterruptedException {

		final int n = 1_000_000_000;
		final int p = 8;
		
		
		long start = System.currentTimeMillis();
		
		
		Thread[] t = new Thread [p];
		for (int k = 0; k < p; k++) {
			
			t[k] = new Thread( () -> {
				int hits = 0;
				for (int i = 0; i < n / p; i++) {
					double x = random();
					double y = random ();
					if (x * x + y * y <= 1)
						hits++;
				}
				
				synchronized(MonteCarloPiPLow.class) {
					MonteCarloPiPLow.hits += hits;
			}
				
			});
		}
		
		for (int k = 0; k < p; k++) t[k].start();
		for (int k = 0; k < p; k++) t[k].join();

		System.out.println("Calculation Time: "+ (System.currentTimeMillis()-start)/1000.0 + "ms");
		double pi = 4.0 * hits / n;
		
		System.out.println(n + " times " + "\nPi = " + pi + " error: " + String.format (Locale.US, "%f",(pi - Math.PI)));
		
	}

}
