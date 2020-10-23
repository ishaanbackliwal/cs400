
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Filename:   GraphTests.java
 * Project:    p4
 * Due Date:   04/09/2020
 * Authors:    Ishaan Backliwal
 * Email:      backliwal@wisc.edu
 * 
 * Course:     CS400
 * Semester:   Spring 2020
 * Lecture:    001
 * 
 */

/**
 * GraphTests tests the Graph Class using JUnit tests
 * @author ishaan backliwal
 *
 */
class GraphTests {
	
	Graph graph;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}
	/**
	 * Tests size of an empty graph
	 */
	@Test
	void test001_empty_graph_size() {
		if(graph.size() != 0)
			fail("Empty graph has a nonzero size when it should not.");
	}
	/**
	 * Tests removing a null vertex
	 */
	@Test
	void test002_remove_null_vertex() {
		try {
			graph.removeVertex("10");
		} 
		catch (NullPointerException e) {
	      // pass
	    } 
		catch (Exception e) {
	      // unexpected case
	      e.printStackTrace();
	    }
	}
	/**
	 * Tests order of an empty graph
	 */
	@Test
	void test003_empty_graph_order() {
		if(graph.order() != 0)
			fail("Empty graph has a nonzero order when it should not.");
	}
	/**
	 * Tests adding and then removing a vertex
	 */
	@Test
	void test004_add_then_remove_vertex() {
		graph.addVertex("1");
	    graph.removeVertex("1");
	    if (graph.size() != 0 || graph.order() != 0)
	      fail("The size and order should be 0 but instead the size is " + graph.size() + " and the order is " + graph.order());
	}
	/**
	 * Tests removing a null edge
	 */
	@Test
	void test005_remove_null_edge() {
		try {
			graph.removeEdge("10", "9");
		} 
		catch (NullPointerException e) {
	      // pass
	    } 
		catch (Exception e) {
	      // unexpected case
	      e.printStackTrace();
	    }
	}
	/**
	 * Tests adding an edge
	 */
	void test006_add_an_edge(){
		graph.addEdge("1", "2");
	    if (graph.size() != 1 || graph.order() != 2)
	    	fail("The size should be 1 and order should be 2 but instead the size is " + graph.size() + " and the order is " + graph.order());
	}
	/**
	 * Tests getting an adjacent vertex
	 */
	void test007_get_adjacent() {
	    graph.addEdge("A", "B");
	    List<String> adjacent = graph.getAdjacentVerticesOf("1");
	    if (adjacent.size() != 1 || !adjacent.get(0).equals("2"))
	      fail("Expected adjacency list is [2] but actual is " + adjacent);
	}
	/**
	 * Tests getting all vertices
	 */
	@Test
	void test008_get_all_vertices() {
		try {
	      graph.addVertex("1");
	      String vertices = graph.getAllVertices().toString();
	      if (!vertices.equals(graph.getAllVertices().toString()))
	        fail("Expected is [1] but actual was " + vertices);
		} 
		catch (Exception e) {
	      e.printStackTrace();
		}
	}

	/**
	 * Tests adding null vertex
	 */
	@Test
	void test009_addNull() {
		try {
	      graph.addVertex(null);
	    } 
		catch (NullPointerException e) {
	      // pass
	    } 
		catch (Exception e) {
	      // unexpected case
	      e.printStackTrace();
	    }
	}
}
