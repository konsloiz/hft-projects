package streamsCreation.version3;


import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		Stream.generate(() -> ThreadLocalRandom.current().nextDouble())
			.limit(10)
			.forEach(System.out::println);

	}

}
