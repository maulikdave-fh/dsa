package in.foresthut.algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class QuickSortTest {

	@Test
	void test() {
		int[] arr = { 4, 6, 1, 7, 3, 2, 5 };
		QuickSort.sort(arr);
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7 }, arr);
	}

}
