package in.foresthut.algos;

import java.util.Arrays;

public class MergeSort {

	static int[] mergeSort(int[] arr) {
		if (arr.length == 1) return arr;
		
		int midIndex = arr.length / 2;
		int[] subLeft = mergeSort(Arrays.copyOfRange(arr, 0, midIndex));
		int[] subRight = mergeSort(Arrays.copyOfRange(arr, midIndex, arr.length));
		
		return merge(subLeft, subRight);
	}

	static int[] merge(int[] arr1, int[] arr2) {
		int[] merged = new int[arr1.length + arr2.length];

		for (int i = 0, j = 0, index = 0; index < merged.length; index++) {
			if (i < arr1.length && j < arr2.length) {
				if (arr1[i] < arr2[j]) {
					merged[index] = arr1[i++];
				} else if (arr2[j] < arr1[i]) {
					merged[index] = arr2[j++];
				} else {
					merged[index++] = arr1[i++];
					merged[index] = arr2[j++];
				}
			} else {
				if (i < arr1.length) {
					merged[index] = arr1[i++];
				} else if (j < arr2.length) {
					merged[index] = arr2[j++];
				}
			}
		}
		return merged;
	}
}
