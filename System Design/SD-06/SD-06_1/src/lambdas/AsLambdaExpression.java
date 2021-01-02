package lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AsLambdaExpression {

	public static void main(String[] args) {

		int n = 10;
		Random r = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(r.nextInt(n));
		System.out.println("Original list: " + list);

		list.sort((o1, o2) -> (o1 - o2));

		System.out.println("Natural Order Sorted list (lambda-expression):" + list);

		list.sort((o1, o2) -> (o2 - o1));

		System.out.println("Reversed Order Sorted list (lambda-expression):" + list);
		
		list.sort((o1, o2) -> (o2%2 - o1%2));

		System.out.println("oddSmallerEven Order Sorted list (lambda-expression):" + list);


	}
}
