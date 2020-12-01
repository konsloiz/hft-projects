package grouping.solution0;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {

	public static void main(String[] args) {
		
		Object[][] data = {{"A", 1}, {"A", 2}, {"B", 1}, {"B", 2}, {"B", 3}, {"C", 2}, {"C", 3}};
		
		Map<String, Set<Object>> map = Arrays.stream(data)
			.collect(
			Collectors.groupingBy(r -> r[0].toString(), 
					Collectors.mapping(r-> r[1], Collectors.toSet())));
			
		System.out.print(map);
	}

}
