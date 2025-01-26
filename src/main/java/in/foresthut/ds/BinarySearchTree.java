package in.foresthut.ds;

public class BinarySearchTree<T extends Comparable<T>> {
	private Node<T> root;

	static class Node<T> {
		private T value;
		private Node<T> left;
		private Node<T> right;

		Node(T value) {
			this.value = value;
		}

		T value() {
			return value;
		}

		Node<T> left() {
			return left;
		}

		Node<T> right() {
			return right;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
		}
	}

	private boolean contains(Node<T> currentNode, T value) {
		if (currentNode == null)
			return false;

		if (currentNode.value.compareTo(value) == 0)
			return true;

		if (value.compareTo(currentNode.value) < 0) {
			currentNode = currentNode.left;
		} else if (value.compareTo(currentNode.value) > 0) {
			currentNode = currentNode.right;
		}

		return contains(currentNode, value);
	}

	public boolean contains(T value) {
		return contains(root, value);
	}

	private Node<T> insert(Node<T> currentNode, T value) {
		if (currentNode == null)
			return new Node<T>(value);

		if (value.compareTo(currentNode.value) < 0) {
			currentNode.left = insert(currentNode.left, value);
		} else if (value.compareTo(currentNode.value) > 0) {
			currentNode.right = insert(currentNode.right, value);
		}
		return currentNode;
	}

	public void insert(T value) {
		if (root == null)
			root = new Node<T>(value);
		insert(root, value);
	}

	T minValue(Node<T> currentNode) {
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentNode.value;
	}

	private Node<T> delete(Node<T> currentNode, T value) {
		if (currentNode == null)
			return null;

		if (value.compareTo(currentNode.value) < 0) {
			currentNode.left = delete(currentNode.left, value);
		} else if (value.compareTo(currentNode.value) > 0) {
			currentNode.right = delete(currentNode.right, value);
		} else {
			if (currentNode.left == null && currentNode.right == null) {
				return null;
			} else if (currentNode.right == null) {
				currentNode = currentNode.left;
			} else if (currentNode.left == null) {
				currentNode = currentNode.right;
			} else {
				T minValue = minValue(currentNode.right);
				currentNode.value = minValue;
				currentNode.right = delete(currentNode.right, minValue);
			}
		}
		return currentNode;
	}

	public void delete(T value) {
		delete(root, value);
	}

	private static Node<Integer> sortedArrayToBST(int[] nums, int left, int right) {
		// Insert a root node
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(nums[(right - left) / 2]);

		// Left branch

		return bst.root;
	}

	public static Node<Integer> fromSortedArray(int[] nums) {
		return sortedArrayToBST(nums, 0, nums.length - 1);
	}

	public Node<T> root() {
		return root;
	}
}
