package comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main2 {

	public static void main(String[] args) {
		
		int n = 10;
		Random r = new Random();
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++)
		list.add(r.nextInt(n));
		System.out.println(list);
		
		list.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
			
		});
		
		System.out.println(list);
	}
}
