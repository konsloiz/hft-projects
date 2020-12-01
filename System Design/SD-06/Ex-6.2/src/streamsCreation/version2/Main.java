package streamsCreation.version2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		
		List<Integer> list = IntStream.iterate(1,  n -> n+1)
		.limit(10)
		.map(n -> n*n)
		.boxed()
		.collect(Collectors.toList());
		
		System.out.println(list);
		
	}

}

