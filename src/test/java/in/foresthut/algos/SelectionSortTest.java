package in.foresthut.algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SelectionSortTest {

	@Test
	void test() {
		int[] arr = { 4, 2, 6, 5, 1, 3 };
		SelectionSort.sort(arr);
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, arr);
	}

}
