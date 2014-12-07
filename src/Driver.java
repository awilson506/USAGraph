
public class Driver {
	public static void main(String args[]) {
		
		Graph g = new Graph();
		g = BuildGraph.getMap(g);
		BuildGraph.assignStateName(g);
		

		System.out.println("Output of depth first traversal");
		g.depthFirstTraversal(1);
		System.out.println("=======================");

		System.out.println("Output of breadth first traversal");
		g.breadthFirstTraversal(1);
		System.out.println("=======================");


	}
}