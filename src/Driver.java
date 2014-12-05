import java.util.*;

public class Driver {
	public static void main(String args[]) {
		Graph g = new Graph(2);

		g.addVertex("Boston");
		g.addVertex("Seattle");

		// Should produce an error message
		g.deleteVertex("New York City");
		System.out.println("=======================");

		// Should produce an error message
		g.addEdge("Boston", "New York City");
		System.out.println("=======================");

		g.addVertex("New York City");
		g.addEdge("Boston", "New York City");
		g.addEdge("Boston", "Seattle");

		String[] bostonsNeighbors;
		bostonsNeighbors = g.getNeighbors("Boston");

		System.out.println("Neighbors of Boston.");
		for (int i = 0; i < bostonsNeighbors.length; i++)
			System.out.println(bostonsNeighbors[i]);
		System.out.println("=======================");

		g.addVertex("Dallas");
		g.addEdge("Seattle", "Dallas");
		g.addEdge("Dallas", "Boston");
		g.addEdge("Dallas", "New York City");

		bostonsNeighbors = g.getNeighbors("Boston");
		System.out.println("Neighbors of Boston.");
		for (int i = 0; i < bostonsNeighbors.length; i++)
			System.out.println(bostonsNeighbors[i]);
		System.out.println("=======================");

		g.addVertex("Los Angeles");
		g.addVertex("San Francisco");
		g.addEdge("San Francisco", "Seattle");
		g.addEdge("San Francisco", "Los Angeles");
		g.addEdge("Seattle", "Los Angeles");

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal("Boston");
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal("Boston");
		System.out.println("=======================");

		String[] path = g.shortestPath("Boston", "Los Angeles");
		System.out.println("Shortest path: Boston-Los Angeles.");
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		System.out.println("=======================");

		g.deleteVertex("Seattle");

		bostonsNeighbors = g.getNeighbors("Boston");
		System.out.println("Neighbors of Boston.");
		for (int i = 0; i < bostonsNeighbors.length; i++)
			System.out.println(bostonsNeighbors[i]);
		System.out.println("=======================");

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal("Los Angeles");
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal("Dallas");
		System.out.println("=======================");

		// Should produce a message saying no path
		path = g.shortestPath("Dallas", "Los Angeles");
		System.out.println("Shortest path: Dallas-Los Angeles.");
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		System.out.println("=======================");

		System.out.println("Number of Vertices in the graph:");
		System.out.println(g.numberOfVertices());
		System.out.println("=======================");

		System.out.println("Number of Edges in the graph:");
		System.out.println(g.numberOfEdges());
		System.out.println("=======================");

		// Should produce ann error message
		g.deleteVertex("Chicago");
		System.out.println("=======================");

		g.addVertex("Chicago");
		g.addEdge("Chicago", "Los Angeles");
		g.addEdge("Chicago", "San Francisco");
		g.addEdge("Boston", "Chicago");

		bostonsNeighbors = g.getNeighbors("Boston");
		System.out.println("Boston's neighbors are");
		for (int i = 0; i < bostonsNeighbors.length; i++)
			System.out.println(bostonsNeighbors[i]);
		System.out.println("=======================");

		String[] chicagosNeighbors = g.getNeighbors("Chicago");
		System.out.println("Chicago's neighbors are");
		for (int i = 0; i < chicagosNeighbors.length; i++)
			System.out.println(chicagosNeighbors[i]);
		System.out.println("=======================");

		System.out.println("Number of Vertices in the graph:");
		System.out.println(g.numberOfVertices());
		System.out.println("=======================");

		System.out.println("Number of Edges in the graph:");
		System.out.println(g.numberOfEdges());
		System.out.println("=======================");

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal("Dallas");
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal("Dallas");
		System.out.println("=======================");

		path = g.shortestPath("Dallas", "Los Angeles");
		System.out.println("Shortest path: Dallas-Los Angeles.");
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		System.out.println("=======================");

		// Should produce an error message saying Houston is missing
		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal("Houston");
		System.out.println("=======================");

		// Should produce an error message saying Houston is missing
		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal("Houston");
		System.out.println("=======================");

		g.addVertex("Houston");
		g.addEdge("Chicago", "Los Angeles");
		g.addEdge("Chicago", "San Francisco");
		g.addEdge("Boston", "Chicago");
		g.addEdge("New York City", "San Francisco");

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal("Houston");
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal("Houston");
		System.out.println("=======================");

		path = g.shortestPath("Houston", "Los Angeles");
		System.out.println("Shortest path: Houston-Los Angeles.");
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		System.out.println("=======================");

		g.addEdge("Houston", "San Francisco");
		g.addEdge("Houston", "Chicago");
		g.addEdge("Houston", "New York City");

		System.out.println("Number of Vertices in the graph:");
		System.out.println(g.numberOfVertices());
		System.out.println("=======================");

		System.out.println("Number of Edges in the graph:");
		System.out.println(g.numberOfEdges());
		System.out.println("=======================");

		chicagosNeighbors = g.getNeighbors("Chicago");
		System.out.println("Chicago's neighbors are");
		for (int i = 0; i < chicagosNeighbors.length; i++)
			System.out.println(chicagosNeighbors[i]);
		System.out.println("=======================");

		String[] newYorksNeighbors = g.getNeighbors("New York City");
		System.out.println("New York's neighbors are");
		for (int i = 0; i < newYorksNeighbors.length; i++)
			System.out.println(newYorksNeighbors[i]);
		System.out.println("=======================");

		path = g.shortestPath("Houston", "Dallas");
		System.out.println("Shortest path: Houston-Dallas.");
		for (int i = 0; i < path.length; i++)
			System.out.println(path[i]);
		System.out.println("=======================");

	}
}