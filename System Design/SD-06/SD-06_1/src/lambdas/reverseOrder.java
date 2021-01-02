package lambdas;

import java.util.Comparator;

public class reverseOrder implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return o2-o1;
	}

}
