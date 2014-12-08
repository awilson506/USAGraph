import java.util.*;

public class Graph {
	protected ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
	private LinkedList[] Edges; // adjacency list representation
	private int numVertices; // tracks the number of vertices
	private int numEdges; // tracks the number of edges
	private int[] depthTree; // Stores the depth first tree
	private int[] breadthTree; // Stores the breadth first tree

	public void setSize(int capacity) {
		Edges = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			Edges[i] = new LinkedList();
		}
	}

	public int numberOfVertices() {
		return numVertices;
	}

	public int numberOfEdges() {
		return numEdges;
	}

	public int getIndex(Integer vertex) {
		for (int i = 0; i < numVertices; i++) {
			if (vertex.equals(Vertices.get(i).id)) {
				return i;
			}
		}
		return -1;
	}

	public void addVertex(Integer newVertex) {
		if (getIndex(newVertex) != -1) {
			System.out.print("addVertex: ");
			System.out.print(newVertex);
			System.out.println(" failed -- vertex already exists.");
			return;
		}

		Vertices.add(numVertices++, new Vertex(newVertex));
	}

	public void addEdge(Integer vertex1, Integer vertex2) {
		int i = getIndex(vertex1);

		if (i == -1) {
			System.out.print("addEdge failed: ");
			System.out.print(vertex1);
			System.out.println(" does not exist.");
			return;
		}

		int j = getIndex(vertex2);
		if (j == -1) {
			System.out.print("addEdge failed: ");
			System.out.print(vertex2);
			System.out.println(" does not exist.");
			return;
		}

		if (Edges[i].contains(j) == false) {
			Edges[i].addFirst(j);
			Edges[j].addFirst(i);
			numEdges++;
		}
	}

	public int degree(Integer vertex) {
		int i = getIndex(vertex);
		if (i == -1) {
			System.out.print("In degree: ");
			System.out.print(vertex);
			System.out.println(" does not exist.");
			return -1;
		}

		return Edges[i].size();
	}

	public int degree(int index) {
		return Edges[index].size();
	}

	public Integer[] getNeighbors(int index) {
		Object[] list = Edges[index].toArray();
		Integer[] intArray = new Integer[list.length];

		for (int i = 0; i < list.length; i++) {
			intArray[i] = (Integer) list[i];
		}

		return intArray;
	}

	public void depthFirstTraversal(Integer source) {

		// Getting the index of the source vertex and
		// checking if the vertex really exists
		int sourceIndex = getIndex(source);

		if (sourceIndex == -1) {
			System.out.print("In depthFirstTraversal: vertex ");
			System.out.print(source);
			System.out.println(" is missing.");
			return;
		}

		// Defining and initializing the visited array
		boolean[] visited = new boolean[numVertices];
		for (int j = 0; j < numVertices; j++) {
			visited[j] = false;
		}
		visited[sourceIndex] = true;

		// Defining and initializing the stack
		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(sourceIndex));

		// Initializing the depth first traversal tree
		depthTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++) {
			depthTree[j] = j;
		}

		boolean more;
		do {
			// The traversal can go on while the stack
			// contains a vertex to process
			while (!s.empty()) {
				// Peek at the current vertex
				int currentVertex = (s.peek()).intValue();

				System.out.println(Vertices.get(currentVertex).id + " "
						+ Vertices.get(currentVertex).name);

				// Get the indices of the neighbors of the current vertex
				Integer[] neighbors = getNeighbors(currentVertex);

				// Scan the neighbors of the current vertex, looking
				// for an unvisited neighbor
				int j = 0;
				boolean found = false;
				while (j < neighbors.length && !found) {
					// If an unvisited neighbor has been found,
					// then push it into the stack, mark it visited,
					// make the current vertex its parent,
					// and get out of the while-loop by setting found
					// to true
					if (!visited[neighbors[j]]) {
						s.push(new Integer(neighbors[j]));
						visited[neighbors[j]] = true;
						found = true;
						depthTree[neighbors[j]] = currentVertex;
					}

					j++; // scan the next vertex
				}

				// If no unvisited vertices have been found, it is time
				// to backtrack
				if (!found) {
					s.pop();
				}
			} // end of while-stack-not-empty loop

			// Determine if there are more unvisited vertices
			// by scanning the visited array and looking for the
			// first unvisited vertex
			more = false;
			int j = 0;
			while (j < numVertices && !more) {
				if (!visited[j]) {
					more = true;
				}
				j++;
			}

			// Push a vertex from the next connected component
			// into the stack
			if (more) {
				s.push(new Integer(j - 1));
				visited[j - 1] = true;
			}

		} while (more);

	}

	public void breadthFirstTraversal(Integer source) {

		// Getting the index of the source vertex and
		// checking if the vertex really exists
		int sourceIndex = getIndex(source);
		if (sourceIndex == -1) {
			System.out.print("In breadthFirstTraversal: vertex ");
			System.out.print(source);
			System.out.println(" is missing.");
			return;
		}

		// Defining and initializing the visited array
		boolean[] visited = new boolean[numVertices];
		for (int j = 0; j < numVertices; j++) {
			visited[j] = false;
		}
		visited[sourceIndex] = true;

		// Defining and initializing the queue
		Queue<Integer> q = new LinkedList<Integer>(); // = new
														// Queue(numVertices);
		q.add(sourceIndex);

		// Initializing the breadth first traversal tree
		breadthTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++) {
			breadthTree[j] = j;
		}

		boolean more;
		do {
			// The traversal can go on while the queue
			// contains a vertex to process
			while (!q.isEmpty()) {
				// Remove the vertex at the front of the queue.
				// This is our current vertex
				int currentVertex = q.remove();

				System.out.println(Vertices.get(currentVertex).id + " "
						+ Vertices.get(currentVertex).name);

				// Get the indices of the neighbors of the current vertex
				Integer[] neighbors = getNeighbors(currentVertex);

				// Scan the neighbors of the current vertex, looking
				// for an unvisited neighbor
				for (int j = 0; j < neighbors.length; j++) {
					if (!visited[neighbors[j]]) {
						q.add(neighbors[j]);
						visited[neighbors[j]] = true;
						breadthTree[neighbors[j]] = currentVertex;
					}
				}

			} // end of while-queue-not-empty loop

			// Determine if there are more unvisited vertices
			// by scanning the visited array and looking for the
			// first unvisited vertex

			more = false;
			int j = 0;
			while (j < numVertices && !more) {
				if (!visited[j]) {
					more = true;
				}
				j++;
			}

			if (more) {
				q.add(j - 1);
				visited[j - 1] = true;
			}
		} while (more);

	}

	// Compute a shortest path between s and t
	public Integer[] shortestPath(Integer s, Integer t) {

		// Get the index of the source and check if valid
		int sourceIndex = getIndex(s);
		if (sourceIndex == -1) {
			System.out.print("In shortestPath: vertex ");
			System.out.print(s);
			System.out.println(" is missing.");
			return new Integer[0];
		}

		// Get the index of the desination and check if valid
		int destIndex = getIndex(t);
		if (destIndex == -1) {
			System.out.print("In shortestPath: vertex ");
			System.out.print(t);
			System.out.println(" is missing.");
			return new Integer[0];
		}

		// Do a BFT starting at s and construct the BFT tree
		breadthFirstTraversal(s);

		// Traverse up the BFT tree, following parent pointers
		// until a root is reached. Push the path into a stack
		Stack<Integer> temp = new Stack<Integer>();
		temp.push(Vertices.get(destIndex).id);

		int i = destIndex;
		int pathLength = 0;
		while (breadthTree[i] != i) {
			temp.push(Vertices.get(i).id);
			pathLength++;
			i = breadthTree[i];
		}

		// If the root is the source index, then complete the path
		if (i == sourceIndex) {
			temp.push(s);
			pathLength++;
		}
		// Otherwise there is no path between source and destination
		else {
			System.out.print("In shortestPath: There is no path between ");
			System.out.print(s);
			System.out.print(" and ");
			System.out.println(t);
			return new Integer[0];
		}

		// Pop the path from the stack and store in a String array
		// called path. Popping from sack automatically reverses
		// the path
		Integer[] path = new Integer[pathLength];
		for (i = 0; i < pathLength; i++) {
			path[i] = temp.pop();
		}

		return path;

	}

	// delete the given vertex
	public void deleteVertex(Integer vertex) {
		int i = getIndex(vertex);
		if (i == -1) {
			System.out.print("deleteVertex: ");
			System.out.print(vertex);
			System.out.println(" failed -- it does not exist.");
			return;
		}

		// Remember the degree of the vertex
		int degree = Edges[i].size();

		// First, let us delete this vertex from the adacency lists
		// of each of its neighbors
		Integer[] neighbors = getNeighbors(i);
		for (int j = 0; j < neighbors.length; j++)
			Edges[neighbors[j]].remove(i);

		// Move the last vertex up to occupy the hole
		// left by the departure of vertex i
		Vertices.set(i, Vertices.get(numVertices - 1));
		Edges[i] = Edges[numVertices - 1];
		Edges[numVertices - 1] = new LinkedList();

		// Change all occurances of the index numVertices-1 to i
		neighbors = getNeighbors(i);
		for (int j = 0; j < neighbors.length; j++) {
			if (Edges[neighbors[j]].contains(numVertices - 1)) {
				Edges[neighbors[j]].remove(j);
			}
			// temp.iData = i;
		}

		numVertices--;
		numEdges = numEdges - degree;
	}

	public void deleteEdge(Integer vertex1, Integer vertex2) {
		int i = getIndex(vertex1);
		if (i == -1) {
			System.out.print("deleteEdge failed: ");
			System.out.print(vertex1);
			System.out.println(" does not exist.");
			return;
		}

		int j = getIndex(vertex2);
		if (j == -1) {
			System.out.print("deleteEdge failed: ");
			System.out.print(vertex2);
			System.out.println(" does not exist.");
			return;
		}

		if (Edges[i].remove(j) != null) {
			numEdges--;
			Edges[j].remove(i);
		}
	}

	public void colorGraph() {

	}
}