package lambdas;

import java.util.function.IntUnaryOperator;

public class Factorial {
	
	public static void main(String[] args) {

		IntUnaryOperator factorial = n -> { 
			
			int fact = 1;
		    for (int i = 1; i <= n; i++) {
		        fact = fact * i;
		    }
		    return fact;
		};
		
		System.out.println(factorial.applyAsInt(5));
		
		

	}
}
