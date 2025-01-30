package in.foresthut.dp;

public class Fibonacci {
	static Integer[] memo = new Integer[100];

	public static Integer[] series() {
		return memo;
	}
	
	public static int fib(int n) {
		if (memo[n] != null)
			return memo[n];

		if (n == 0 || n == 1)
			return n;

		memo[n] = fib(n - 1) + fib(n - 2);
		return memo[n];
	}
}
