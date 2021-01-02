package piCalculation.parallel.low;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo_Low {

	private static int hits = 0;

	private static double random() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static void main(String[] args) throws InterruptedException {
		final int n = 1_000_000_000;
		final int p = 8;
		
		long start = System.currentTimeMillis();
		
		Thread[] t = new Thread[p];
		for(int k = 0; k < p; k++)
			t[k] = new Thread(() ->  calculate(n, p));
		
		for(int k = 0; k < p; k++) t[k].start();
		for(int k = 0; k < p; k++) t[k].join();
		
		long stop = System.currentTimeMillis();
		double pi = 4.0 * hits / n;
		System.out.println(n + " times | " + (stop - start)/1000.0 + "ms : " + pi + " error: " + String.format(Locale.US, "%f", (pi - Math.PI)));
	}

	private static void calculate(final int n, final int p) {
		int hits = 0;
		for (int i = 0; i < n / p; i++) {
			double x = random();
			double y = random();
			if (x * x + y * y <= 1)
				hits++;
		}
		synchronized(MonteCarlo_Low.class) {
			MonteCarlo_Low.hits += hits;
		}
	}

}
