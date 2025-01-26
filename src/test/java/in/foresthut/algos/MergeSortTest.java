package in.foresthut.algos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MergeSortTest {
	@Test
	void testMerge_whenTwoSortedListsAreGiven_shouldMerge() {
		int[] arr1 = { 1, 3, 7, 8 };
		int[] arr2 = { 2, 4, 5, 6 };
		int[] expected = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] actual = MergeSort.merge(arr1, arr2);
		assertArrayEquals(expected, actual);
	}

	@Test
	void testMerge_whenTwoSortedListsWithDuplicatesAreGiven_shouldMerge() {
		int[] arr1 = { 1, 3, 7, 8 };
		int[] arr2 = { 2, 4, 6, 8 };
		int[] expected = { 1, 2, 3, 4, 6, 7, 8, 8 };
		int[] actual = MergeSort.merge(arr1, arr2);
		assertArrayEquals(expected, actual);
	}

	@Test
	void testMergeSort_whenUnSortedArrayIsGiven_shouldSort() {
		int[] arr = { 3, 1, 4, 2 };
		int[] expected = { 1, 2, 3, 4 };
		int[] actual = MergeSort.mergeSort(arr);
		assertArrayEquals(expected, actual);
	}
}
