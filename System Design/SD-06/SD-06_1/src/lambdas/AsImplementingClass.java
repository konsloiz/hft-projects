package lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsImplementingClass {

	public static void main(String[] args) {

		int n = 10;
		Random r = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(r.nextInt(n));
		System.out.println("Original list: " + list);

		list.sort(new naturalOrder());
		System.out.println("Natural Order Sorted list (implementing class):" + list);

		list.sort(new reverseOrder());
		System.out.println("Reverse Order Sorted list (implementing class):" + list);
		
		list.sort(new oddSmallerEven());
		System.out.println("OddSmallerEven Order Sorted list (implementing class):" + list);

	}
}