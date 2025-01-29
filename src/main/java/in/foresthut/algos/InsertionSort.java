package in.foresthut.algos;

import java.util.Arrays;

public class InsertionSort {

	public static void sort(int[] arr) {
		System.out.println(Arrays.toString(arr));
		for (int i = 1; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[minIndex] < arr[j]) {
					int temp = arr[minIndex];
					arr[minIndex] = arr[j];
					arr[j] = temp;
					minIndex = j;
				}
			}
		}
	}
}
