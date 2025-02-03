package in.foresthut.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			@SuppressWarnings("unchecked")
			Node<T> other = (Node<T>) obj;
			return Objects.equals(value, other.value);
		}

		@Override
		public String toString() {
			return String.valueOf(value);
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

	private Node<T> sortedArrayToBST(T[] nums, int left, int right) {
		if (left > right)
			return null;

		int midIndex = left + ((right - left) / 2);

		Node<T> node = new Node<>(nums[midIndex]);
		node.left = sortedArrayToBST(nums, left, midIndex - 1);
		node.right = sortedArrayToBST(nums, midIndex + 1, right);

		return node;
	}

	public Node<T> fromSortedArray(T[] nums) {
		return sortedArrayToBST(nums, 0, nums.length - 1);
	}

	private Node<T> invertTree(Node<T> node) {
		if (node == null)
			return null;

		Node<T> temp = node.left;
		node.left = invertTree(node.right);
		node.right = invertTree(temp);

		return node;
	}

	public Node<T> invertTree() {
		return invertTree(root);
	}

	public List<T> BFS() {
		Node<T> currentNode = root;
		Queue<Node<T>> queue = new Queue<>();
		List<T> result = new ArrayList<>();

		queue.enqueue(currentNode);

		while (queue.length() > 0) {
			currentNode = queue.dequeue().value();
			result.add(currentNode.value());
			if (currentNode.left != null)
				queue.enqueue(currentNode.left);
			if (currentNode.right != null)
				queue.enqueue(currentNode.right);
		}
		return result;
	}

	public List<T> DFSPreOrder() {
		List<T> result = new ArrayList<>();

		class Traverse {
			Traverse(Node<T> currentNode) {
				result.add(currentNode.value);

				if (currentNode.left != null)
					new Traverse(currentNode.left);

				if (currentNode.right != null)
					new Traverse(currentNode.right);
			}

		}
		new Traverse(root);
		return result;
	}

	public List<T> DFSPostOrder() {
		List<T> result = new ArrayList<>();

		class Traverse {
			Traverse(Node<T> currentNode) {
				if (currentNode.left != null) {
					new Traverse(currentNode.left);
				}

				if (currentNode.right != null) {
					new Traverse(currentNode.right);
				}

				result.add(currentNode.value);
			}
		}
		new Traverse(root);
		return result;
	}

	public List<T> DFSInOrder() {
		List<T> result = new ArrayList<>();

		class Traverse {
			Traverse(Node<T> currentNode) {
				if (currentNode.left != null) {
					new Traverse(currentNode.left);
				}

				result.add(currentNode.value);

				if (currentNode.right != null) {
					new Traverse(currentNode.right);
				}

			}
		}
		new Traverse(root);
		return result;
	}

	public boolean isValid() {
		List<T> sorted = DFSInOrder();
		for (int index = 1; index < sorted.size(); index++) {
			if (sorted.get(index).compareTo(sorted.get(index - 1)) < 0)
				return false;
		}
		return true;
	}

	public T kthSmallest(int k) {
		if (k < 1)
			return null;
		List<T> result = DFSInOrder();
		return result.get(k - 1);
	}

	private int maxDepth(Node<T> currentNode) {
		if (currentNode == null)
			return 0;

		int leftHeight = maxDepth(currentNode.left);
		int rightHeight = maxDepth(currentNode.right);

		return Math.max(leftHeight, rightHeight) + 1;

	}

	public int maxDepth() {
		if (root == null)
			return 0;

		return maxDepth(root);
	}

	private boolean hasPathSum(Node<T> currentNode, int targetSum) {
		if (currentNode == null)
			return false;

		targetSum -= (Integer) currentNode.value;

		if (currentNode.left == null && currentNode.right == null)
			return (targetSum == 0);

		return hasPathSum(currentNode.left, targetSum) || hasPathSum(currentNode.right, targetSum);
	}

	public boolean hasPathSum(int targetSum) {
		if (root == null)
			return false;

		return hasPathSum(root, targetSum);
	}

	public Node<T> root() {
		return root;
	}
}
