package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.foresthut.ds.BinarySearchTree.Node;

public class BinarySearchTreeTest {

	@Test
	void testInsert_whenBSTIsEmpty_shouldInsert() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		assertEquals(47, bst.root().value());
	}

	@Test
	void testInsert_whenLessThanParent_shouldInsertToLeft() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		assertEquals(47, bst.root().value());
		bst.insert(21);
		assertEquals(21, bst.root().left().value());
	}

	@Test
	void testInsert_whenGreaterThanParent_shouldInsertToRight() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		assertEquals(47, bst.root().value());
		bst.insert(76);
		assertEquals(76, bst.root().right().value());
	}

	@Test
	void testContains_whenBSTIsEmpty_shouldReturnFalse() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		assertFalse(bst.contains(46));
	}

	@Test
	void testContains_whenBSTDoesNotContainTheElement_shouldReturnFalse() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		assertFalse(bst.contains(46));
	}

	@Test
	void testContains_whenBSTContainsTheElement_shouldReturnTrue() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(56);
		assertTrue(bst.contains(56));
	}

	@Test
	void testMinValue_whenBSTIsNotEmpty_shouldReturnMinValue() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(56);
		bst.insert(18);
		bst.insert(52);
		assertEquals(52, bst.minValue(bst.root().right()));
	}

	@Test
	void testDelete_whenBSTIsNotEmpty_shouldDelete() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(56);
		bst.insert(18);
		bst.insert(52);
		bst.delete(47);
		assertFalse(bst.contains(47));
		assertEquals(52, bst.root().value());
	}

	@Test
	void testBSTFromSortedArray_whenNonEmptyArrayIsSupplied_shouldReturnBSTRootNode() {
		int[] nums = { 1, 2, 3, 4, 5 };
		Node<Integer> root = BinarySearchTree.fromSortedArray(nums);
		assertEquals(3, root.value());
	}

}
