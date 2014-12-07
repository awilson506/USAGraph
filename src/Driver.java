
public class Driver {
	public static void main(String args[]) {
		
		Graph g = new Graph(11);
		g = BuildGraph.getMap(g);
		

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal(1);
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal(1);
		System.out.println("=======================");


	}
}