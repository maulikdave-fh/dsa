package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		Integer[] nums = { 1, 2, 3, 4, 5 };
		Node<Integer> root = new BinarySearchTree<Integer>().fromSortedArray(nums);
		assertEquals(3, root.value());
		assertEquals(1, root.left().value());
		assertEquals(2, root.left().right().value());
		assertEquals(4, root.right().value());
		assertEquals(5, root.right().right().value());
	}

	@Test
	void testInvert_whenNonEmptyBSTIsSupplied_shouldInvertedBSTRootNode() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		Node<Integer> root = bst.invertTree();
		assertEquals(76, root.left().value());
		assertEquals(21, root.right().value());
		assertEquals(18, root.right().right().value());
		assertEquals(27, root.right().left().value());
		assertEquals(82, root.left().left().value());
		assertEquals(52, root.left().right().value());
	}

	@Test
	void testBFS_whenBSTIsNotEmpty_shouldReturnArrayList() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		List<Integer> expected = new ArrayList<>(Arrays.asList(47, 21, 76, 18, 27, 52, 82));
		List<Integer> actual = bst.BFS();
		assertEquals(expected, actual);
	}

	@Test
	void testDFSPreOrder_whenBSTIsNotEmpty_shouldReturnArrayList() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		List<Integer> expected = new ArrayList<>(Arrays.asList(47, 21, 18, 27, 76, 52, 82));
		List<Integer> actual = bst.DFSPreOrder();
		assertEquals(expected, actual);
	}

	@Test
	void testDFSPostOrder_whenBSTIsNotEmpty_shouldReturnArrayList() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		List<Integer> expected = new ArrayList<>(Arrays.asList(18, 27, 21, 52, 82, 76, 47));
		List<Integer> actual = bst.DFSPostOrder();
		assertEquals(expected, actual);
	}

	@Test
	void testDFSInOrder_whenBSTIsNotEmpty_shouldReturnArrayList() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		List<Integer> expected = new ArrayList<>(Arrays.asList(18, 21, 27, 47, 52, 76, 82));
		List<Integer> actual = bst.DFSInOrder();
		assertEquals(expected, actual);
	}

	@Test
	void testKthSmallest_whenBSTIsNotEmpty() {

		BinarySearchTree<Integer> bst = new BinarySearchTree<>();

		bst.insert(5);
		bst.insert(3);
		bst.insert(7);
		bst.insert(2);
		bst.insert(4);
		bst.insert(6);
		bst.insert(8);

		assertEquals(2, bst.kthSmallest(1));
		assertEquals(4, bst.kthSmallest(3));
		assertEquals(7, bst.kthSmallest(6));
	}

	@Test
	void testMaxDepth1_whenBSTIsNotEmpty() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(47);
		bst.insert(21);
		bst.insert(76);
		bst.insert(18);
		bst.insert(27);
		bst.insert(52);
		bst.insert(82);
		assertEquals(3, bst.maxDepth());
	}

	@Test
	void testMaxDepth2_whenBSTIsNotEmpty() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(1);
		bst.insert(2);

		assertEquals(2, bst.maxDepth());
	}
	
	@Test
	void testhasPathSum_whenBSTHasSupportingValues_shouldReturnTrue() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(1);
		bst.insert(2);

		assertTrue(bst.hasPathSum(3));
	}
	
	@Test
	void testhasPathSum_whenBSTDoesNotHaveSupportingValues_shouldReturnFalse() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(1);
		bst.insert(2);

		assertFalse(bst.hasPathSum(5));
	}

}
