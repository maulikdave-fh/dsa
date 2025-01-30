package in.foresthut.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FibonacciTest {
	@Test
	void testSeven() {
		assertEquals(13, Fibonacci.fib(7));
	}
	
	@Test
	void testFourty() {
		assertEquals(102334155, Fibonacci.fib(40));
	}
}
