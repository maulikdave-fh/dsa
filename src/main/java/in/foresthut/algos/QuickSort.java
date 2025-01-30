package in.foresthut.algos;

public class QuickSort {

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int left, int right) {
		if (left < right) {
			int pivotIndex = pivot(arr, left, right);
			sort(arr, left, pivotIndex - 1);
			sort(arr, pivotIndex + 1, right);
		}
	}

	private static void swap(int[] arr, int fIndex, int sIndex) {
		int temp = arr[fIndex];
		arr[fIndex] = arr[sIndex];
		arr[sIndex] = temp;
	}

	private static int pivot(int[] arr, int pivotIndex, int endIndex) {
		int swapIndex = pivotIndex;
		for (int i = pivotIndex + 1; i <= endIndex; i++) {
			if (arr[i] < arr[pivotIndex]) {
				swapIndex++;
				swap(arr, swapIndex, i);
			}
		}
		swap(arr, pivotIndex, swapIndex);
		return swapIndex;
	}

}
