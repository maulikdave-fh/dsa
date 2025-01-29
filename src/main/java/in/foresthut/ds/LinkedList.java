package in.foresthut.ds;

import java.util.Optional;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> tail;
	private int length;

	static class Node<T> {
		private T value;
		private Node<T> next;

		Node() {

		}

		Node(T value) {
			this.value = value;
		}

		Node<T> next() {
			return next;
		}

		T value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	public LinkedList() {

	}

	public LinkedList(T value) {
		Node<T> newNode = new Node<>(value);
		head = newNode;
		tail = newNode;
		length = 1;
	}

	public void append(T value) {
		Node<T> newNode = new Node<>(value);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		length++;
	}

	public Optional<Node<T>> removeFirst() {
		if (head == null)
			return Optional.empty();

		Node<T> temp = head;
		if (head.next == null) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			temp.next = null;
		}
		length--;
		return Optional.of(temp);
	}

	public Optional<Node<T>> removeLast() {
		if (head == null)
			return Optional.empty();

		Node<T> temp = head;
		if (head.next() == null) {
			head = null;
			tail = null;
		} else {
			Node<T> pointer = head;
			while (pointer.next() != tail) {
				pointer = pointer.next;
			}
			temp = tail;
			tail = pointer;
			tail.next = null;
		}
		length--;
		return Optional.of(temp);
	}

	public void prepend(T value) {
		Node<T> newNode = new Node<>(value);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		length++;
	}

	public Optional<Node<T>> get(int index) {
		if (length == 0 || index < 0 || index >= length)
			return Optional.empty();

		if (index == 0)
			return Optional.of(head);
		else if (index == length - 1)
			return Optional.of(tail);
		else {
			Node<T> temp = head;
			for (int counter = 0; counter < index; counter++) {
				temp = temp.next;
			}
			return Optional.of(temp);
		}
	}

	public boolean set(int index, T value) {
		Optional<Node<T>> currentNode = get(index);
		if (currentNode.isPresent()) {
			currentNode.get().value = value;
			return true;
		}
		return false;
	}

	public boolean insert(int index, T value) {
		if (index < 0 || index > length)
			return false;

		if (index == 0) {
			prepend(value);
			return true;
		} else if (index == length) {
			append(value);
			return true;
		} else {
			Optional<Node<T>> temp = get(index - 1);
			if (temp.isPresent()) {
				Node<T> newNode = new Node<>(value);
				Node<T> before = temp.get();
				Node<T> after = temp.get().next();
				before.next = newNode;
				newNode.next = after;
				length++;
				return true;
			}
		}
		return false;
	}

	public Optional<Node<T>> remove(int index) {
		if (index < 0 || index >= length)
			return Optional.empty();

		if (index == 0)
			return removeFirst();
		else if (index == length - 1)
			return removeLast();
		else {
			Optional<Node<T>> temp = get(index - 1);
			Node<T> before = temp.get();
			Node<T> toBeRemoved = temp.get().next();
			before.next = toBeRemoved.next;
			toBeRemoved.next = null;
			length--;
			return Optional.of(toBeRemoved);
		}
	}

	public void bubbleSort() {
		if (length == 0 || length == 1)
			return;

		if (length == 2) {
			T temp = head.value;
			head.value = tail.value;
			tail.value = temp;
			return;
		}

		Node<T> start = head;
		Node<T> end = tail;
		int i = 0;
		while (i < length) {
			while (start != end) {
				if (start.value.compareTo(start.next.value) >= 0) {
					T temp = start.value;
					start.value = start.next.value;
					start.next.value = temp;
				}
				start = start.next;
			}
			end = start;
			start = head;
			i++;
		}
	}

	public void selectionSort() {
		if (length < 2)
			return;

		Node<T> walker = head;
		while (walker != null) {
			Node<T> minValueNode = walker;
			Node<T> innerWalker = walker.next;

			while (innerWalker != null) {
				if (minValueNode.value.compareTo(innerWalker.value) >= 0) {
					minValueNode = innerWalker;
				}
				innerWalker = innerWalker.next;
			}
			if (minValueNode.value.compareTo(walker.value) != 0) {
				T temp = minValueNode.value;
				minValueNode.value = walker.value;
				walker.value = temp;
			}
			walker = walker.next;
		}
	}

	// Not written by me
	public void insertionSort() {
		if (length < 2) {
			return; // List is already sorted
		}

		Node<T> sortedListHead = head;
		Node<T> unsortedListHead = head.next;
		sortedListHead.next = null;

		while (unsortedListHead != null) {
			Node<T> current = unsortedListHead;
			unsortedListHead = unsortedListHead.next;

			if (current.value.compareTo(sortedListHead.value) < 0) {
				current.next = sortedListHead;
				sortedListHead = current;
			} else {
				Node<T> searchPointer = sortedListHead;
				while (searchPointer.next != null && current.value.compareTo(searchPointer.next.value) > 0) {
					searchPointer = searchPointer.next;
				}
				current.next = searchPointer.next;
				searchPointer.next = current;
			}
		}

		head = sortedListHead;
		Node<T> temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		tail = temp;
	}

	Node<T> mergeSort(Node<T> currentHead) {
		if (currentHead == null || currentHead.next == null)
			return currentHead;

		Node<T> middle = middle(currentHead);

		Node<T> left = mergeSort(currentHead);
		Node<T> right = mergeSort(middle);

		return merge(left, right);
	}

	Node<T> merge(Node<T> list1, Node<T> list2) {
		Node<T> dummyHead = new Node<>();
		Node<T> tail = dummyHead;
		while (list1 != null && list2 != null) {
			if (list1.value.compareTo(list2.value) < 0) {
				tail.next = list1;
				list1 = list1.next;
				tail = tail.next;
			} else {
				tail.next = list2;
				list2 = list2.next;
				tail = tail.next;
			}
		}
		tail.next = (list1 != null) ? list1 : list2;
		return dummyHead.next;
	}

	Node<T> middle(Node<T> head) {
        Node<T> midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        Node<T> mid = midPrev.next;
        midPrev.next = null;
        return mid;
	}

	public Optional<Node<T>> head() {
		return Optional.of(head);
	}

	public Optional<Node<T>> tail() {
		return Optional.of(tail);
	}

	public int length() {
		return length;
	}

	@Override
	public String toString() {
		Node<T> temp = head;
		StringBuilder sb = new StringBuilder("{ ");
		while (temp != null) {
			sb.append(temp.value + " ");
			temp = temp.next;
		}
		sb.append("}");

		return sb.toString();
	}
}
