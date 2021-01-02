package piCalculation;


import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.stream.Stream;

public class MonteCarloPi {
	
	static DoubleSupplier next = () -> ThreadLocalRandom.current().nextDouble();
	
	static int n = 100_000_000;
	static int hits = 0;
	
	static class Point{
		double x, y;
		Point(){
			x = next.getAsDouble();
			y = next.getAsDouble();
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		// do your calculation here
		Stream.generate(() -> new Point())
			.limit(n)
			.map(p -> p.x * p.x + p.y * p.y)
			.filter(z -> z <= 1)
			.forEach(h -> hits++);

		long stop = System.currentTimeMillis();

		double pi = 4.0 * hits / n;
		double error = Math.abs(Math.PI - pi);
		System.out.println((stop-start)/1000.0 + "ms , PI: " + pi + " error: " + String.format(Locale.US, "%f", error));
	}
}
