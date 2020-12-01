package streamsCreation.version4;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		
		ThreadLocalRandom.current().doubles()
		.limit(10)
		.forEach(System.out::println);

	}

}
