package in.foresthut.ds;

public class Queue<T> {
	private Node<T> first;
	private Node<T> last;
	private int length;

	static class Node<T> {
		private T value;
		private Node<T> next;

		Node(T value) {
			this.value = value;
		}
		
		T value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	public Queue() {

	}

	public Queue(T value) {
		Node<T> newNode = new Node<>(value);
		first = newNode;
		last = newNode;
		length = 1;
	}

	public void enqueue(T value) {
		Node<T> newNode = new Node<T>(value);
		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
		length++;
	}

	public Node<T> dequeue() {
		if (first == null)
			return null;

		Node<T> temp = first;
		if (first.next == null) {
			first = null;
			last = null;
		} else {
			first = first.next;
			temp.next = null;
		}
		length--;
		return temp;
	}

	public int length() {
		return length;
	}

	@Override
	public String toString() {
		Node<T> temp = first;
		StringBuilder sb = new StringBuilder();
		while (temp != null) {
			sb.append(temp.value + " ");
			temp = temp.next;
		}
		return sb.toString();
	}
}
