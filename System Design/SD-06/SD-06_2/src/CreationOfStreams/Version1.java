package CreationOfStreams;


import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;


public class Version1 {

	public static void main(String[] args) {

		IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).limit(10)
			.map(s-> s*s)
			.boxed()
			.forEach(s-> System.out.println(s));
		
	}

}
