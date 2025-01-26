package in.foresthut.ds;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {
	private List<T> heap;

	public MaxHeap() {
		heap = new ArrayList<>();
	}

	public List<T> heap() {
		return new ArrayList<>(heap);
	}

	public void insert(T item) {
		heap.add(item);
		int current = heap.size() - 1;
		while (current > 0 && heap.get(current).compareTo(heap.get(parent(current))) > 0) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public T remove() {
		if (heap.size() == 0)
			return null;

		if (heap.size() == 1) {
			return heap.remove(0);
		}

		T max = heap.get(0);
		heap.set(0, heap.remove(heap.size() - 1));

		sinkDown(0);

		return max;
	}

	private void sinkDown(int index) {
		int parentIndex = index;

		while (true) {
			int leftIndex = leftChild(parentIndex);
			int rightIndex = rightChild(parentIndex);

			T leftValue = leftIndex < heap.size() ? heap.get(leftIndex) : null;
			T rightValue = rightIndex < heap.size() ? heap.get(rightIndex) : null;

			if (leftValue != null && rightValue != null && leftValue.compareTo(rightValue) > 0) {
				swap(leftIndex, parentIndex);
				parentIndex = leftIndex;
			} else if (rightValue != null && leftValue!= null && rightValue.compareTo(leftValue) > 0) {
				swap(rightIndex, parentIndex);
				parentIndex = rightIndex;
			}
			index = parentIndex;

			if (parentIndex == index)
				return;
		}
	}

	private int leftChild(int index) {
		return (index << 1) + 1;
	}

	private int rightChild(int index) {
		return (index << 1) + 2;
	}

	private int parent(int index) {
		return (index - 1) >> 1;
	}

	private void swap(int index1, int index2) {
		T temp = heap.get(index1);
		heap.set(index1, heap.get(index2));
		heap.set(index2, temp);
	}

	@Override
	public String toString() {
		return heap.toString();
	}

}
