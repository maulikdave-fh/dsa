package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinHeapTest {
	@Test
	void testRemove_whenHeapNotEmpty_shouldRemove() {
        MinHeap<Integer> myHeap = new MinHeap<>();
        myHeap.insert(99);
        myHeap.insert(72);
        myHeap.insert(61);
        myHeap.insert(58);
        myHeap.insert(10);
        myHeap.insert(75);
        Integer removedValue1 = myHeap.remove();
        assertEquals(10, removedValue1);
        assertEquals(58, myHeap.heap().get(0));
        Integer removedValue2 = myHeap.remove();
        assertEquals(58, removedValue2);
        assertEquals(61, myHeap.heap().get(0));
	}
	
	@Test
	void testRemove_whenInsertedInAscendingOrder_shouldRemove() {
		MinHeap<Integer> myHeap = new MinHeap<>();
        myHeap.insert(1);
        myHeap.insert(2);
        myHeap.insert(3);
        Integer removedValue1 = myHeap.remove();
        assertEquals(1, removedValue1);
        assertEquals(2, myHeap.heap().get(0));
	}
}
