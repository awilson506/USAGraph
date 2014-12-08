
public class Driver {
	public static void main(String args[]) {
		
		Graph g = new Graph();
		g = BuildGraph.getMap(g);
		BuildGraph.assignStateName(g);

		System.out.println("Depth first traversal");
		g.depthFirstTraversal(1);
		System.out.println("*********************");

		System.out.println("Breadth first traversal");
		g.breadthFirstTraversal(1);
		System.out.println("*********************");


	}
}