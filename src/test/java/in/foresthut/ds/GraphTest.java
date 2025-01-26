package in.foresthut.ds;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GraphTest {
	@Test
	void testAddVertex_whenGraphIsEmpty_shouldAdd() {
		Graph<String> g = new Graph<>();
		String moe = "Moe";
		assertTrue(g.addVertex(moe));
	}

	@Test
	void testAddEdge_whenGivenValidVertices_shouldConnect() {
		Graph<String> g = new Graph<>();
		String larry = "Larry";
		String moe = "Moe";
		g.addVertex(larry);
		g.addVertex(moe);
		assertTrue(g.addEdge(larry, moe));
	}

	@Test
	void testAddEdge_whenGivenInValidVertices_shouldConnect() {
		Graph<String> g = new Graph<>();
		String larry = "Larry";
		String moe = "Moe";
		String curly = "Curly";
		g.addVertex(larry);
		g.addVertex(moe);
		assertFalse(g.addEdge(larry, curly));
	}

	@Test
	void testRemoveEdge_whenGivenValidVertices_shouldRemoveTheEdge() {
		Graph<String> g = new Graph<>();
		String larry = "Larry";
		String moe = "Moe";
		String curly = "Curly";
		g.addVertex(larry);
		g.addVertex(moe);
		g.addVertex(curly);

		g.addEdge(larry, moe);
		g.addEdge(moe, curly);
		g.addEdge(larry, curly);
		assertTrue(g.removeEdge(larry, moe));
	}
	
	@Test
	void testRemoveVertex_whenGivenValidVertex_shouldRemoveVertex() {
		Graph<String> g = new Graph<>();
		String larry = "Larry";
		String moe = "Moe";
		String curly = "Curly";
		g.addVertex(larry);
		g.addVertex(moe);
		g.addVertex(curly);

		g.addEdge(larry, moe);
		g.addEdge(moe, curly);
		g.addEdge(larry, curly);
		assertTrue(g.removeVertex(moe));
	}
}
