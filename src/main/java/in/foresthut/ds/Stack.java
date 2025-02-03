package in.foresthut.ds;

public class Stack<T> {
	private Node<T> top;
	private int height;

	private static class Node<T> {
		private T value;
		private Node<T> next;

		Node(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	public Stack(T value) {
		Node<T> newNode = new Node<>(value);
		top = newNode;
		height = 1;
	}

	public void push(T value) {
		Node<T> newNode = new Node<>(value);
		if (top == null) {
			top = newNode;
		} else {
			newNode.next = top;
			top = newNode;
		}
		height++;
	}

	public Node<T> pop() {
		if (top == null)
			return null;

		Node<T> temp = top;
		top = top.next;
		temp.next = null;

		height--;
		return temp;
	}

	public int height() {
		return height;
	}

	@Override
	public String toString() {
		Node<T> temp = top;
		StringBuilder sb = new StringBuilder();
		while (temp != null) {
			sb.append(temp.value + "\n");
			temp = temp.next;
		}

		return sb.toString();
	}
}
