package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxHeapTest {
	@Test
	void testInsert_whenInsertElementIsMax_shouldBeAddedToIndexZero() {
		MaxHeap<Integer> h = new MaxHeap<>();
		h.insert(99);
		h.insert(72);
		h.insert(61);
		h.insert(58);
		h.insert(100);
		assertEquals(100, h.heap().get(0));
	}

	@Test
	void testInsert_whenInsertElementIsNotMax_shouldBeAdded() {
		MaxHeap<Integer> h = new MaxHeap<>();
		h.insert(99);
		h.insert(72);
		h.insert(61);
		h.insert(58);
		h.insert(100);
		h.insert(75);
		assertEquals(75, h.heap().get(2));
	}

	@Test
	void testRemove_whenHeapIsNotEmpty_shouldRemove() {
		MaxHeap<Integer> h = new MaxHeap<>();
		h.insert(95);
		h.insert(75);
		h.insert(80);
		h.insert(55);
		h.insert(60);
		h.insert(50);
		h.insert(65);
		h.remove();
		assertEquals(80, h.heap().get(0));
		h.remove();
		assertEquals(75, h.heap().get(0));
	}

}
