package streamsCreation.version1;

import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		
		//IntStream sqr = IntStream.range(1, 11);
		//sqr.map(s ->s*s).boxed().peek(l -> System.out.println(l)).reduce(0, (s,l) -> s + l);
		
		IntStream.iterate(1, n -> n+1)
			.limit(10)
			.map(n -> n*n)
			.forEach (n -> System.out.println(n));
		
	}

}