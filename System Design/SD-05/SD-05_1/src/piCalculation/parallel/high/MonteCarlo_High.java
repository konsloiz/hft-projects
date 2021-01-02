package piCalculation.parallel.high;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo_High {

	private static double random() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final int n = 1_000_000_000;
		final int p = 8;

		long start = System.currentTimeMillis();

		ExecutorService executor = Executors.newCachedThreadPool();

		List<Future<Integer>> t = new ArrayList<>();

		for (int k = 0; k < p; k++) {

			t.add(executor.submit(() -> calculate(n, p)));
		}

		int hits = 0;
		for (Future<Integer> f : t) {
			hits += f.get();
		}

		executor.shutdown();
		long stop = System.currentTimeMillis();
		double pi = 4.0 * hits / n;
		System.out.println(n + " times | " + (stop - start) / 1000.0 + "ms : " + pi + " error: "
				+ String.format(Locale.US, "%f", (pi - Math.PI)));
	}

	private static int calculate(final int n, final int p) {
		int hits = 0;
		for (int i = 0; i < n / p; i++) {
			double x = random();
			double y = random();
			if (x * x + y * y <= 1)
				hits++;
		}

		return hits;
	}

}
