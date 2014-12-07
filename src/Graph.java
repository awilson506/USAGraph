import java.util.*;

public class Graph {
	//protected Integer[] Vertices; // 1-d array to store the vertices
	protected ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
	protected LinkedList[] Edges; // adjacency list representation
	protected int numVertices; // tracks the number of vertices
	protected int numEdges; // tracks the number of edges
	protected int[] dFTTree; // Stores the depth first tree
	protected int[] bFTTree; // Stores the breadth first tree

	// Default constructor. Sets aside enough capacity for one vertex
	public Graph() {
		this(1);
	}

	// Constructor that sets aside as much capacity as specified by the user
	public Graph(int capacity) {
		//Vertices = new Integer[capacity];
		Edges = new LinkedList[capacity];
		
		// Construct the LinkList object in each slot in Edges
		for (int i = 0; i < capacity; i++)
			Edges[i] = new LinkedList();

		numVertices = 0;
		numEdges = 0;
	}

	public int numberOfVertices() {
		return numVertices;
	}

	public int numberOfEdges() {
		return numEdges;
	}

	// Finds the location at which a vertex is stored in Vertices.
	// Returns -1 if vertex not found
	public int getIndex(Integer vertex) {
		for (int i = 0; i < numVertices; i++)
			if (vertex.equals(Vertices.get(i).id))
				return i;

		return -1;
	}

	// Resizes the array of vertices. Can make it larger or smaller,
	// depending on what newSize is.
	protected Integer[] resize(Integer[] vertices2, int newSize) {
		Integer[] temp = new Integer[newSize];

		int smallerSize = newSize;
		if (vertices2.length < smallerSize)
			smallerSize = vertices2.length;

		for (int i = 0; i < smallerSize; i++)
			temp[i] = vertices2[i];

		return temp;
	}

	// Resizes Edges, the array of linked lists. Can make it
	// larger or smaller, depending on what newSize is.
	protected LinkList[] resize(LinkList[] array, int newSize) {
		LinkList[] temp = new LinkList[newSize];

		int smallerSize = newSize;
		if (array.length < smallerSize)
			smallerSize = array.length;

		for (int i = 0; i < smallerSize; i++)
			temp[i] = array[i];

		for (int i = smallerSize; i < newSize; i++)
			temp[i] = new LinkList();

		return temp;
	}

	// Adds a new vertex
	public void addVertex(Integer newVertex) {
		if (getIndex(newVertex) != -1) {
			System.out.print("addVertex: ");
			System.out.print(newVertex);
			System.out.println(" failed -- vertex already exists.");
			return;
		}

		// if array of vertices is full, we need to expand it and
		// also expand Edges
//		if (Vertices.size() == numVertices) {
//			Vertices = resize(Vertices, 2 * numVertices + 1);
//			Edges = resize(Edges, 2 * numVertices + 1);
//		}

		Vertices.add(numVertices++, new Vertex(newVertex));
		//System.out.println(newVertex);
	}

	// Adds a new edge, wittht no given weight
	public void addEdge(Integer vertex1, Integer vertex2) {
		
		addEdge(vertex1, vertex2, 0);
	}

	// Adds a new edge
	public void addEdge(Integer vertex1, Integer vertex2, double distance) {
		int i = getIndex(vertex1);
		if (i == -1) {
			System.out.print("addEdge failed: ");
			System.out.print(vertex1);
			System.out.println(" does not exist.");
			return;
		}

		int j = getIndex(vertex2);
		if (j == -1) {
			//this.addVertex(vertex2);
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
	
	// returns the degree of a vertex with given name
	public int degree(Integer vertex) {
		// Get the index of the vertex
		int i = getIndex(vertex);
		if (i == -1) {
			System.out.print("In degree: ");
			System.out.print(vertex);
			System.out.println(" does not exist.");
			return -1;
		}

		return Edges[i].size();
	}

	// returns the degree of a vertex with given index
	public int degree(int index) {
		return Edges[index].size();
	}

	// returns the indices of all the neighbors of a given vertex with index
	public Integer[] getNeighbors(int index) {
		Object[] list = Edges[index].toArray();
		Integer[] intArray = new Integer[list.length];

		  for(int i=0; i<list.length; i++){
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
		for (int j = 0; j < numVertices; j++)
			visited[j] = false;
		visited[sourceIndex] = true;

		// Defining and initializing the stack
		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(sourceIndex));

		// Initializing the depth first traversal tree
		dFTTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++)
			dFTTree[j] = j;

		boolean more;
		do {
			// The traversal can go on while the stack
			// contains a vertex to process
			while (!s.empty()) {
				// Peek at the current vertex
				int currentVertex = (s.peek()).intValue();

				System.out.println(Vertices.get(currentVertex).id);

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
						dFTTree[neighbors[j]] = currentVertex;
					}

					j++; // scan the next vertex
				}

				// If no unvisited vertices have been found, it is time
				// to backtrack
				if (!found)
					s.pop();
			} // end of while-stack-not-empty loop

			// Determine if there are more unvisited vertices
			// by scanning the visited array and looking for the
			// first unvisited vertex
			more = false;
			int j = 0;
			while (j < numVertices && !more) {
				if (!visited[j])
					more = true;
				j++;
			}

			// Push a vertex from the next connected component
			// into the stack
			if (more) {
				s.push(new Integer(j - 1));
				visited[j - 1] = true;
			}

		} while (more);

	} // end of function

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
		for (int j = 0; j < numVertices; j++)
			visited[j] = false;
		visited[sourceIndex] = true;

		// Defining and initializing the queue
		Queue q = new Queue(numVertices);
		q.insert(sourceIndex);

		// Initializing the breadth first traversal tree
		bFTTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++)
			bFTTree[j] = j;

		boolean more;
		do {
			// The traversal can go on while the queue
			// contains a vertex to process
			while (!q.isEmpty()) {
				// Remove the vertex at the front of the queue.
				// This is our current vertex
				int currentVertex = q.remove();

				System.out.println(Vertices.get(currentVertex).id);

				// Get the indices of the neighbors of the current vertex
				Integer[] neighbors = getNeighbors(currentVertex);

				// Scan the neighbors of the current vertex, looking
				// for an unvisited neighbor
				for (int j = 0; j < neighbors.length; j++) {
					if (!visited[neighbors[j]]) {
						q.insert(neighbors[j]);
						visited[neighbors[j]] = true;
						bFTTree[neighbors[j]] = currentVertex;
					}
				}

			} // end of while-queue-not-empty loop

			// Determine if there are more unvisited vertices
			// by scanning the visited array and looking for the
			// first unvisited vertex

			more = false;
			int j = 0;
			while (j < numVertices && !more) {
				if (!visited[j])
					more = true;
				j++;
			}

			// Enqueue a vertex from the next connected component
			// into the queue
			if (more) {
				q.insert(j - 1);
				visited[j - 1] = true;
			}
		} while (more);

	} // end of function

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
		while (bFTTree[i] != i) {
			temp.push(Vertices.get(i).id);
			pathLength++;
			i = bFTTree[i];
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
		for (i = 0; i < pathLength; i++)
			path[i] = temp.pop();

		return path;

	} // end of shortest path function

} // end of class