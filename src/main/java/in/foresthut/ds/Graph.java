package in.foresthut.ds;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
	private HashTable<T, List<T>> adjacencyList = new HashTable<>();

	public boolean addVertex(T vertex) {
		if (!adjacencyList.containsKey(vertex)) {
			adjacencyList.set(vertex, new ArrayList<T>());
			return true;
		}
		return false;
	}

	public boolean addEdge(T vertex1, T vertex2) {
		if (adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
			adjacencyList.get(vertex1).add(vertex2);
			adjacencyList.get(vertex2).add(vertex1);
			return true;
		}
		return false;
	}

	public boolean removeEdge(T vertex1, T vertex2) {
		if (adjacencyList.containsKey(vertex1) && adjacencyList.containsKey(vertex2)) {
			adjacencyList.get(vertex1).remove(vertex2);
			adjacencyList.get(vertex2).remove(vertex1);
			return true;
		}
		return false;
	}

	public boolean removeVertex(T vertex) {
		if (adjacencyList.containsKey(vertex)) {
			List<T> connectedVertices = adjacencyList.get(vertex);
			for (T connectedVertex : connectedVertices) {
				adjacencyList.get(connectedVertex).remove(vertex);
			}
			adjacencyList.remove(vertex);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return adjacencyList.toString();
	}
}
