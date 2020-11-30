package comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main4 {

	public static void main(String[] args) {
		
		int n = 10;
		Random r = new Random();
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < n; i++)
		list.add(r.nextInt(n));
		System.out.println(list);
		
		list.sort((o1, o2) -> o2-o1);
		System.out.println(list);
	}
}
