package in.foresthut.ds;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
	private List<T> heap;

	public MinHeap() {
		heap = new ArrayList<>();
	}

	public List<T> heap() {
		return new ArrayList<>(heap);
	}

	public void insert(T value) {
		heap.add(value);
		int current = heap.size() - 1;
		while (current > 0 && heap.get(current).compareTo(heap.get(parent(current))) < 0) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public T remove() {
		if (heap.size() == 0)
			return null;
		if (heap.size() == 1)
			return heap.remove(0);

		T min = heap.get(0);
		heap.set(0, heap.remove(heap.size() - 1));

		sinkDown(0);

		return min;
	}

	private void sinkDown(int index) {
		int parentIndex = index;

		while (true) {
			int leftIndex = leftChild(parentIndex);
			int rightIndex = rightChild(parentIndex);

			T leftChild = leftIndex < heap.size() ? heap.get(leftIndex) : null;
			T rightChild = rightIndex < heap.size() ? heap.get(rightIndex) : null;

			if (leftChild != null && rightChild == null && heap.get(parentIndex).compareTo(leftChild) > 0) {
				swap(leftIndex, parentIndex);
				parentIndex = leftIndex;
			} else if (rightChild != null && leftChild == null && heap.get(parentIndex).compareTo(rightChild) > 0) {
				swap(rightIndex, parentIndex);
				parentIndex = rightIndex;
			} else if (leftChild != null && rightChild != null && leftChild.compareTo(rightChild) < 0) {
				swap(leftIndex, parentIndex);
				parentIndex = leftIndex;
			} else if (leftChild != null && rightChild != null && rightChild.compareTo(leftChild) < 0) {
				swap(rightIndex, parentIndex);
				parentIndex = rightIndex;
			}
			if (index != parentIndex)
				index = parentIndex;
			else
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
