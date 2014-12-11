import java.util.*;

public class Graph {
	protected ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
	private LinkedList[] Edges;
	private int numVertices;
	private int numEdges;
	private int[] depthTree;
	private int[] breadthTree;

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
		Vertices.add(numVertices++, new Vertex(newVertex));
	}

	public void addEdge(Integer vertex1, Integer vertex2) {
		int i = getIndex(vertex1);
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
		int sourceIndex = getIndex(source);
		boolean[] visited = new boolean[numVertices];
		for (int j = 0; j < numVertices; j++) {
			visited[j] = false;
		}
		visited[sourceIndex] = true;

		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(sourceIndex));

		depthTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++) {
			depthTree[j] = j;
		}

		boolean continueloop;
		System.out.println("Depth first traversal");
		System.out.println("*********************");
		System.out.println(String.format("%-6s %s", "Level", "State"));
		System.out.println("-----+--------------");
		do {
			while (!s.empty()) {
				int currentVertex = (s.peek()).intValue();
				System.out.println(String.format("%-5s| %s",
						Vertices.get(currentVertex).level,
						Vertices.get(currentVertex).name));
				Integer[] neighbors = getNeighbors(currentVertex);

				int j = 0;
				boolean found = false;
				while (j < neighbors.length && !found) {
					if (!visited[neighbors[j]]) {
						s.push(new Integer(neighbors[j]));
						visited[neighbors[j]] = true;
						found = true;
						depthTree[neighbors[j]] = currentVertex;
					}
					j++;
				}

				if (!found) {
					s.pop();
				}
			}
			continueloop = false;
			int j = 0;
			while (j < numVertices && !continueloop) {
				if (!visited[j]) {
					continueloop = true;
				}
				j++;
			}

			if (continueloop) {
				s.push(new Integer(j - 1));
				visited[j - 1] = true;
			}

		} while (continueloop);

	}

	public void breadthFirstTraversal(Integer source) {

		int sourceIndex = getIndex(source);
		boolean[] visited = new boolean[numVertices];
		for (int j = 0; j < numVertices; j++) {
			visited[j] = false;
		}
		visited[sourceIndex] = true;

		Queue<Integer> q = new LinkedList<Integer>();
		q.add(sourceIndex);

		breadthTree = new int[numVertices];
		for (int j = 0; j < numVertices; j++) {
			breadthTree[j] = j;
		}

		boolean continueloop;
		System.out.println("Breadth first traversal");
		System.out.println("*********************");
		System.out.println(String.format("%-6s %s", "Level", "State"));
		System.out.println("-----+--------------");
		int level = 1;
		int nextlevel = 0;
		Integer current = 0;

		do {
			while (!q.isEmpty()) {
				int currentVertex = q.remove();

				Integer[] neighbors = getNeighbors(currentVertex);
				if (level == 0) {
					current++;
					level = nextlevel;
					nextlevel = 0;
				}
				Vertices.get(currentVertex).setlevel(current);
				System.out.println(String.format("%-5s| %s",
						Vertices.get(currentVertex).level,
						Vertices.get(currentVertex).name));

				for (int j = 0; j < neighbors.length; j++) {
					if (!visited[neighbors[j]]) {
						q.add(neighbors[j]);
						visited[neighbors[j]] = true;
						breadthTree[neighbors[j]] = currentVertex;
						nextlevel++;
					}
				}
				level--;
			}

			continueloop = false;
			int j = 0;
			while (j < numVertices && !continueloop) {
				if (!visited[j]) {
					continueloop = true;
				}
				j++;
			}

			if (continueloop) {
				q.add(j - 1);
				visited[j - 1] = true;
			}
		} while (continueloop);

	}

	public void colorGraph() {

	}
}