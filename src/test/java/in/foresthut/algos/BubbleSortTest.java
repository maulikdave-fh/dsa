package in.foresthut.algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class BubbleSortTest {

	@Test
	void test1() {
		int[] arr = { 4, 2, 6, 5, 1, 3 };
		BubbleSort.sort(arr);
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, arr);
	}

}
