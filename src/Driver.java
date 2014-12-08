
public class Driver {
	public static void main(String args[]) {
		
		Graph g = new Graph();
		g = BuildGraph.getMap(g);
		BuildGraph.assignStateName(g);
		g.depthFirstTraversal(1);
		System.out.println("\n");
		g.breadthFirstTraversal(1);

	}
}