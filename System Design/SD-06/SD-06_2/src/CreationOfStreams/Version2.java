package CreationOfStreams;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Version2 {

	public static void main(String[] args) {

		List<Integer> list = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).limit(10)
			.map(s-> s*s)
			.boxed()
			.collect(Collectors.toList());
		
		System.out.println(list);
		
	}
}
