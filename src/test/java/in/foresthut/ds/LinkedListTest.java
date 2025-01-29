package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import in.foresthut.ds.LinkedList.Node;

class LinkedListTest {

	@Test
	void testConstructor_whenValidIntIsPassed_shouldInitializeTheLinkedList() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		assertEquals(1, ll.length());
		assertNotNull(ll.head());
		assertNotNull(ll.tail());
		assertNull(ll.head().get().next());
		assertNull(ll.tail().get().next());
		assertEquals(1, ll.head().get().value());
	}

	@Test
	void testAppend_whenHeadIsPresent_shouldAppend() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		assertEquals(2, ll.length());
		assertEquals(2, ll.head().get().next().value());
		assertEquals(2, ll.tail().get().value());
	}

	@Test
	void testAppend_whenLLIsEmpty_shouldAppend() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.append(3);
		assertEquals(1, ll.length());
		assertEquals(3, ll.head().get().value());
		assertEquals(3, ll.tail().get().value());
	}

	@Test
	void testRemoveFirst_whenLLIsEmpty_shouldReturnEmpty() {
		LinkedList<Integer> ll = new LinkedList<>();
		Optional<Node<Integer>> removed = ll.removeFirst();
		assertEquals(Optional.empty(), removed);
		assertEquals(0, ll.length());
	}

	@Test
	void testRemoveFirst_whenLLHasOneElement_shouldReturnRemovedNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		Optional<Node<Integer>> expected = ll.head();
		Optional<Node<Integer>> removed = ll.removeFirst();
		assertEquals(expected, removed);
		assertEquals(0, ll.length());
	}

	@Test
	void testRemoveFirst_whenLLHasMoreThanOneElements_shouldReturnRemovedNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		Optional<Node<Integer>> expected = ll.head();
		Optional<Node<Integer>> removed = ll.removeFirst();
		assertEquals(expected, removed);
		assertEquals(1, ll.length());
	}

	@Test
	void testRemoveLast_whenLLIsEmpty_shouldReturnEmpty() {
		LinkedList<Integer> ll = new LinkedList<>();
		Optional<Node<Integer>> removed = ll.removeLast();
		assertEquals(Optional.empty(), removed);
		assertEquals(0, ll.length());
	}

	@Test
	void testRemoveLast_whenLLHasOneElement_shouldReturnRemovedNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		Optional<Node<Integer>> expected = ll.tail();
		Optional<Node<Integer>> removed = ll.removeLast();
		assertEquals(expected, removed);
		assertEquals(0, ll.length());
	}

	@Test
	void testRemoveLast_whenLLHasMoreThanOneElements_shouldReturnRemovedNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		Optional<Node<Integer>> expected = ll.tail();
		Optional<Node<Integer>> removed = ll.removeLast();
		assertEquals(expected, removed);
		assertEquals(1, ll.length());
	}

	@Test
	void testPrepend_whenLLIsEmpty_shouldPrepend() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.prepend(3);
		assertEquals(1, ll.length());
		assertEquals(3, ll.head().get().value());
		assertEquals(3, ll.tail().get().value());
	}

	@Test
	void testPrepend_whenLLIsNotEmpty_shouldPrepend() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.prepend(2);
		assertEquals(2, ll.length());
		assertEquals(2, ll.head().get().value());
		assertEquals(1, ll.tail().get().value());
	}

	@Test
	void testGet_whenLLIsEmpty_shouldReturnEmpty() {
		LinkedList<Integer> ll = new LinkedList<>();
		Optional<Node<Integer>> actual = ll.get(2);
		assertEquals(Optional.empty(), actual);
	}

	@Test
	void testGet_whenIndexIsOOB_shouldReturnEmpty() {
		LinkedList<Integer> ll = new LinkedList<>();
		Optional<Node<Integer>> actual = ll.get(2);
		assertEquals(Optional.empty(), actual);
	}

	@Test
	void testGet_whenIndexIsZero_shouldReturnHead() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		Optional<Node<Integer>> actual = ll.get(0);
		assertEquals(ll.head(), actual);
	}

	@Test
	void testGet_whenIndexIsForTail_shouldReturnTail() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		Optional<Node<Integer>> actual = ll.get(1);
		assertEquals(ll.tail(), actual);
	}

	@Test
	void testGet_whenIndexIsValid_shouldReturnNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		Optional<Node<Integer>> actual = ll.get(1);
		assertEquals(ll.head().get().next(), actual.get());
	}

	@Test
	void testSet_whenIndexIsValid_shouldUpdateNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		assertTrue(ll.set(0, 3));
		assertEquals(3, ll.get(0).get().value());
	}

	@Test
	void testSet_whenIndexIsNotValid_shouldReturnFalse() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		assertFalse(ll.set(2, 3));
		assertEquals(1, ll.get(0).get().value());
	}

	@Test
	void testInsert_whenIndexIsZero_shouldInsertNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		assertTrue(ll.insert(0, 5));
		assertEquals(5, ll.get(0).get().value());
		assertEquals(4, ll.length());
	}

	@Test
	void testInsert_whenIndexIsForTail_shouldInsertNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		assertTrue(ll.insert(3, 5));
		assertEquals(5, ll.get(3).get().value());
	}

	@Test
	void testInsert_whenIndexIsValid_shouldInsertNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		assertTrue(ll.insert(1, 5));
		assertEquals(5, ll.get(1).get().value());
		assertEquals(4, ll.length());
	}

	@Test
	void testRemove_whenIndexIsZero_shouldRemoveHead() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		Optional<Node<Integer>> head = ll.head();
		Optional<Node<Integer>> removed = ll.remove(0);
		assertEquals(head, removed);
		assertEquals(2, ll.length());
	}

	@Test
	void testRemove_whenIndexIsForTail_shouldRemoveTail() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		Optional<Node<Integer>> tail = ll.tail();
		Optional<Node<Integer>> removed = ll.remove(2);
		assertEquals(tail, removed);
		assertEquals(2, ll.length());
	}

	@Test
	void testRemove_whenIndexIsValid_shouldRemoveNode() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		ll.append(2);
		ll.append(3);
		Node<Integer> toBeRemoved = ll.head().get().next();
		Optional<Node<Integer>> removed = ll.remove(1);
		assertEquals(toBeRemoved, removed.get());
		assertEquals(2, ll.length());
	}

	@Test
	void testRemove_whenIndexIsOOB_shouldReturnEmpty() {
		LinkedList<Integer> ll = new LinkedList<>(1);
		Optional<Node<Integer>> removed = ll.remove(1);
		assertEquals(Optional.empty(), removed);
		assertEquals(1, ll.length());
	}

	@Test
	void testBubbleSort_whenLLHas2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(2);
		ll.append(1);
		ll.bubbleSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.tail().get().value());
	}

	@Test
	void testBubbleSort_whenLLHasMoreThan2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(3);
		ll.append(1);
		ll.append(3);
		ll.append(2);
		ll.bubbleSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.head().get().next().value());
		assertEquals(3, ll.tail().get().value());
	}

	@Test
	void testSelectionSort_whenLLHas2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(2);
		ll.append(1);
		ll.selectionSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.tail().get().value());
	}

	@Test
	void testSelectionSort_whenLLHasMoreThan2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(3);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.selectionSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.head().get().next().value());
		assertEquals(3, ll.tail().get().value());
	}

	@Test
	void testInsertionSort_whenLLHas2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(2);
		ll.append(1);
		ll.insertionSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.tail().get().value());
	}

	@Test
	void testInsertionSort_whenLLHasMoreThan2NodesWithDuplicates_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(3);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.insertionSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(2, ll.head().get().next().value());
		assertEquals(3, ll.tail().get().value());
	}

	@Test
	void testInsertionSort_whenLLHasMoreThan2Nodes_shouldSort() {
		LinkedList<Integer> ll = new LinkedList<>(4);
		ll.append(2);
		ll.append(6);
		ll.append(5);
		ll.append(1);
		ll.append(3);

		ll.insertionSort();
		assertEquals(1, ll.head().get().value());
		assertEquals(6, ll.tail().get().value());
	}

	@Test
	void testMiddle_whenOddNumOfNodes() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.append(10);
		ll.append(1);
		ll.append(60);
		ll.append(30);
		ll.append(5);
		assertEquals(60, ll.middle(ll.head().get()).value());
	}

	@Test
	void testMiddle_whenEvenNumOfNodes() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.append(1);
		ll.append(60);
		ll.append(30);
		ll.append(5);
		assertEquals(30, ll.middle(ll.head().get()).value());
	}
	
	@Test
	void testMergeSort_whenEvenNumOfNodes() {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.append(1);
		ll.append(60);
		ll.append(30);
		ll.append(5);
		System.out.println(ll.mergeSort(ll.head().get()));
	}

}
