import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * Filename:   Graph.java
 * Project:    p4
 * Due Date:   04/09/2020
 * Authors:    Ishaan Backliwal
 * Email:      backliwal@wisc.edu
 * 
 * Course:     CS400
 * Semester:   Spring 2020
 * Lecture:    001
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	// linked list of all the graph's vertices
	private LinkedList<String> vertices;
	
	private LinkedList<LinkedList<String>> edges;
	
	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {
		vertices = new LinkedList<String>();
		edges = new LinkedList<LinkedList<String>>();
	}

	/* (non-Javadoc)
	 * @see GraphADT#addVertex(java.lang.String)
	 * 
	 * Adds a new vertex to the graph
	 * 
	 * If the vertex passed is null or is already in the graph, the method ends
	 */
	@Override
	public void addVertex(String vertex) {
		if(vertex == null || vertices.contains(vertex))
			return;
		edges.add(new LinkedList<String>());
		vertices.add(vertex);
	}

	/* (non-Javadoc)
	 * @see GraphADT#removeVertex(java.lang.String)
	 * 
	 * Removes a vertex (and all edges associated with that vertex) from graph
	 * 
	 * If vertex passed is null or is not in the graph, method ends
	 */
	@Override
	public void removeVertex(String vertex) {
		if(vertex == null || !vertices.contains(vertex))
			return;
		edges.remove(vertices.indexOf(vertex));
		vertices.remove(vertices.indexOf(vertex));
		// removes vertex from other adjacency lists
		for(int i = 0; i < edges.size(); i++) {
			if(edges.get(i).contains(vertex)) {
				edges.get(i).remove(vertex);
			}
		}
	}

	/* (non-Javadoc)
	 * @see GraphADT#addEdge(java.lang.String, java.lang.String)
	 * 
	 * Add edge from vertex1 to vertex2 of the graph
	 * 
	 * If vertex does not exist, edge is not added
	 * If the edge is already in the graph, the edge is not added
	 */
	@Override
	public void addEdge(String vertex1, String vertex2) {
		if(vertex1 != null && !vertices.contains(vertex1)){
			this.addVertex(vertex1);
		}
		if(vertex2 != null && !vertices.contains(vertex2)){
			this.addVertex(vertex2);
		}
		if(vertex1 == null || vertex2 == null) {
			return;
		}
		if(!edges.get(vertices.indexOf(vertex1)).contains(vertex2)) {
			edges.get(vertices.indexOf(vertex1)).add(vertex2);
		}
	}

	/* (non-Javadoc)
	 * @see GraphADT#removeEdge(java.lang.String, java.lang.String)
	 * 
	 * Removes an edge from the graph
	 * 
	 * If either vertex or edge from each vertex does not exist, the edge is not removed
	 */
	@Override
	public void removeEdge(String vertex1, String vertex2) {
		if (vertex1 != null && !vertices.contains(vertex1)) {
		      this.addVertex(vertex1);
		}
		if (vertex2 != null && !vertices.contains(vertex2)) {
		      this.addVertex(vertex2);
		}
		if (vertex2 == null || vertex1 == null) {
		      return;
		}
		if (edges.get(vertices.indexOf(vertex1)).contains(vertex2)) {
		      edges.get(vertices.indexOf(vertex1)).remove(vertex2);
		}
	}

	/* (non-Javadoc)
	 * @see GraphADT#getAllVertices()
	 * 
	 * Returns set that contains all the vertices
	 */
	@Override
	public Set<String> getAllVertices() {
		Set<String> set = new TreeSet<String>();
		for(int i = 0; i < vertices.size(); i++)
			set.add(vertices.get(i));
		return set;
	}

	/* (non-Javadoc)
	 * @see GraphADT#getAdjacentVerticesOf(java.lang.String)
	 * 
	 * Gets all adjacent vertices of the passed vertex
	 */
	@Override
	public List<String> getAdjacentVerticesOf(String vertex) {
		if(vertex == null || !vertices.contains(vertex))
			return null;
		return edges.get(vertices.indexOf(vertex));
	}

	/* (non-Javadoc)
	 * @see GraphADT#size()
	 */
	@Override
	public int size() {
		int size = 0;
		for(int i = 0; i < edges.size(); i++)
			size += edges.get(i).size();
		return size;
	}

	/* (non-Javadoc)
	 * @see GraphADT#order()
	 * 
	 * Returns number of vertices in the graph
	 */
	@Override
	public int order() {
		return vertices.size();
	}
}
