package sequential;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {

		final int n = 1_000_000_000;
		int hits = 0;
		long start = System.currentTimeMillis();

		for (int i = 0; i < n; i++) {
			double x = ThreadLocalRandom.current().nextDouble();
			double y = ThreadLocalRandom.current().nextDouble();
			if (x * x + y * y <= 1)
				hits++;
		}
		
		System.out.println("Calculation Time: "+ (System.currentTimeMillis()-start)/1000.0 + "ms");
		double pi = 4.0 * hits / n;
		
		System.out.println(n + " times " + "\nPi = " + pi + " error: " + String.format (Locale.US, "%f",(pi - Math.PI)));
		
		
	}

}
