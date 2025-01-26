package in.foresthut.ds;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * A data structure that maps keys to values. The structure cannot contain
 * duplicate values; each key can map to at most one value.
 * 
 * @param <K> the type of keys maintained by this table
 * @param <V> the type of mapped values
 * 
 * @author Maulik Dave
 */
public class HashTable<K, V> {
	private int size = 7;
	private Node<K, V>[] dataMap;

	static class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> next;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(key, next, value);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node<K, V> other = (Node<K, V>) obj;
			return Objects.equals(key, other.key) && Objects.equals(next, other.next)
					&& Objects.equals(value, other.value);
		}

		@Override
		public String toString() {
			return "[" + String.valueOf(key) + " : " + String.valueOf(value) + "]";
		}
	}

	@SuppressWarnings("unchecked")
	public HashTable() {
		dataMap = new Node[size];
	}

	private int hash(K key) {
		int hash = 0;
		String keyStr = String.valueOf(key);
		char[] keyChars = keyStr.toCharArray();
		for (char keyChar : keyChars) {
			int asciiValue = keyChar;
			hash = (hash + asciiValue * 23) % dataMap.length;
		}
		return hash;
	}

	public void set(K key, V value) {
		int index = hash(key);
		Node<K, V> newNode = new Node<>(key, value);
		if (dataMap[index] == null) {
			dataMap[index] = newNode;
		} else {
			Node<K, V> temp = dataMap[index];
			// To handle duplicate keys
			if (temp.key.equals(key)) {
				temp.value = value;
				return;
			}
			while (temp.next != null) {
				if (temp.key.equals(key)) {
					temp.value = value;
					return;
				}
				temp = temp.next;
			}
			temp.next = newNode;
		}
	}

	public V get(K key) {
		int index = hash(key);
		if (dataMap[index] == null) {
			throw new NoSuchElementException("Key Not Found.");
		} else if (dataMap[index].next == null) {
			return dataMap[index].value;
		} else {
			Node<K, V> temp = dataMap[index];
			while (!temp.key.equals(key)) {
				temp = temp.next;
			}
			return temp.value;
		}
	}

	public boolean containsKey(K key) {
		int index = hash(key);
		Node<K, V> temp = dataMap[index];
		while (temp != null) {
			if (temp.key.equals(key))
				return true;
			temp = temp.next;
		}
		return false;
	}

	public Set<K> keys() {
		Set<K> allKeys = new HashSet<>();
		for (Node<K, V> pointer : dataMap) {
			Node<K, V> temp = pointer;
			while (temp != null) {
				allKeys.add(temp.key);
				temp = temp.next;
			}
		}
		return allKeys;
	}
	
	public boolean remove(K key) {
		int index = hash(key);
		if (dataMap[index] == null)
			return false;
		else if (dataMap[index].next == null) {
			dataMap[index] = null;
			return true;
		} else {
			Node<K, V> temp = dataMap[index];
			Node<K, V> previous = null;
			while(!temp.key.equals(key)) {
				previous = temp;
				temp = temp.next;
			}
			if (previous != null)
				previous.next = temp.next;
			else 
				dataMap[index] = temp.next;
			temp.next = null;
			return true;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node<K, V> pointer : dataMap) {
			Node<K, V> temp = pointer;
			while (temp != null) {
				sb.append(temp.toString() + "\n");
				temp = temp.next;
			}
		}
		return sb.toString();
	}
}
