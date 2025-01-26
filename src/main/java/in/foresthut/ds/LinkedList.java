package in.foresthut.ds;

import java.util.Optional;

public class LinkedList<T> {
	private Node<T> head;
	private Node<T> tail;
	private int length;

	static class Node<T> {
		private T value;
		private Node<T> next;

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
			Optional<Node<T>> temp = get(index-1);
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
	
	public Optional<Node<T>> remove(int index){
		if (index < 0 || index >= length)
			return Optional.empty();
		
		if (index == 0)
			return removeFirst();
		else if (index == length - 1)
			return removeLast();
		else {
			Optional<Node<T>> temp = get(index-1);
			Node<T> before = temp.get();
			Node<T> toBeRemoved = temp.get().next();
			before.next = toBeRemoved.next;
			toBeRemoved.next = null;
			length--;
			return Optional.of(toBeRemoved);
		}
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
}
