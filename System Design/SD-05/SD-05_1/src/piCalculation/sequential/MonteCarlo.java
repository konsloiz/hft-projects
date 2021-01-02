package piCalculation.sequential;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarlo {

	static double pi = Math.PI;

	public static void main(String[] args) {

		int n = 1000000000;
		int hits = 0;

		long start = System.currentTimeMillis();

		for (int i = 0; i < n; i++) {
			double x = random();
			double y = random();
			if (x * x + y * y <= 1)
				hits++;
		}

		long end = System.currentTimeMillis();

		double calculated_pi = 4.0 * hits / n;

		long calculation_time = (end - start) / 1000;

		double calc_differnce = calculated_pi - pi;

		System.out.println("PI = " + pi);
		System.out.println("Calculated PI = " + calculated_pi);
		System.out.println("Error = " + String.format(Locale.US, "%f", calc_differnce));
		System.out.println("Calculation Time = " + calculation_time + " sec");

	}

	private static double random() {

		return ThreadLocalRandom.current().nextDouble();
	}

}
