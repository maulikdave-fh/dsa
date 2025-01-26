package in.foresthut.ds;

import java.util.Optional;

public class DoublyLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int length;

	private static class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		Node(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	public DoublyLinkedList(T value) {
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
			newNode.prev = tail;
			tail = newNode;
		}
		length++;
	}

	public Optional<Node<T>> removeLast() {
		if (head == null)
			return Optional.empty();

		Node<T> temp = tail;
		if (head.next == null) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
			temp.prev = null;
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
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
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
			head.prev = null;
			temp.next = null;
		}
		length--;
		return Optional.of(temp);
	}

	public Optional<Node<T>> get(int index) {
		if (index < 0 || index >= length)
			return Optional.empty();

		if (index == 0)
			return Optional.of(head);

		if (index == length - 1)
			return Optional.of(tail);

		Node<T> walker = head;
		if (index < length / 2) {
			for (int i = 0; i < index; i++) {
				walker = walker.next;
			}
		} else {
			walker = tail;
			for (int i = length - 1; i >= index; i--) {
				walker = walker.prev;
			}
		}
		return Optional.of(walker);
	}
	
	public boolean set(int index, T value) {
		Optional<Node<T>> node = get(index);
		if (node.isPresent()) {
			node.get().value = value;
			return true;
		}
		return false;
	}
	

	public Node<T> head() {
		return head;
	}

	public Node<T> tail() {
		return tail;
	}

	public int length() {
		return length;
	}

	@Override
	public String toString() {
		Node<T> temp = head;
		StringBuilder sb = new StringBuilder("[ ");

		while (temp != null) {
			sb.append(temp + " ");
			temp = temp.next;
		}

		sb.append("]");

		return sb.toString();
	}
}
