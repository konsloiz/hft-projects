package groupping;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;

public class Groupping {

	public static void main(String[] args) {
		Object[][] data = {{"A", 1}, {"A", 2}, {"B", 1}, {"B", 2}, {"B", 3}, {"C", 2}, {"C", 3}};
		
		Map<String, Set<Object>> map = Arrays.stream(data)
			.collect(
				groupingBy(r -> r[0].toString(),
						mapping(r -> r[1], toSet())));
		
		System.out.println(map);

		Map<Integer, Set<Object>> map2 = Arrays.stream(data)
				.collect(
					groupingBy(r -> (Integer)r[1],
							mapping(r -> r[0], toSet())));
			
			System.out.println(map2);

	}

}
