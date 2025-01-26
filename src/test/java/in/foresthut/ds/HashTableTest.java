package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class HashTableTest {

	@Test
	void testSet_whenInNewBucket_shouldSet() {
		HashTable<String, Integer> hashTable = new HashTable<>();
		hashTable.set("bolts", 20);
		assertEquals(20, hashTable.get("bolts"));
	}

	@Test
	void testSet_whenInTheExistingBucket_shouldSet() {
		HashTable<String, Integer> hashTable = new HashTable<>();
		hashTable.set("nails", 20);
		hashTable.set("lumber", 2);
		assertEquals(20, hashTable.get("nails"));
		assertEquals(2, hashTable.get("lumber"));
	}

	@Test
	void testSet_whenDuplicateKeys_shouldOverwrite() {
		HashTable<String, Integer> hashTable = new HashTable<>();
		hashTable.set("nails", 20);
		assertEquals(20, hashTable.get("nails"));
		hashTable.set("nails", 40);
		assertEquals(40, hashTable.get("nails"));
	}

	@Test
	void testGet_whenKeyNotFound_shouldThrowNoSuchElementException() {
		HashTable<String, Integer> hashTable = new HashTable<>();
		NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> {
			hashTable.get("plywood");
		});
		assertEquals("Key Not Found.", ex.getMessage());
	}
	
	//TODO : Add test cases for containsKey()
	//TODO : Add test cases for keys()
	//TODO : Add test cases for remove()
}
