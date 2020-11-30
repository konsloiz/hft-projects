package function_declaration;

import java.util.function.IntUnaryOperator;

public class Main {

	
	private static IntUnaryOperator factorial;

	public static void main(String[] args) {
	
	// factorial = n -> { int n_factorial = 1; for(int i=1; i<=n; i++) {n_factorial = n_factorial * i;} return n_factorial;};
	
	int n = 5;
	// System.out.println("The factorial of number " + n + " = " + factorial.applyAsInt(n));
	
	factorial = k -> ( k == 0 ? 1 : k * factorial.applyAsInt(k-1));

	System.out.println("The factorial of number " + n + " = " + factorial.applyAsInt(n));
	

	}
}
