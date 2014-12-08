import java.io.FileOutputStream;
import java.io.PrintStream;


public class Driver {
	public static void main(String args[]) {
		try{
		
		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		System.setOut(out);
		
		Graph g = new Graph();
		g = BuildGraph.getMap(g);
		BuildGraph.assignStateName(g);
		g.depthFirstTraversal(1);
		System.out.println("\n");
		g.breadthFirstTraversal(1);
		}catch(Exception ex){
			System.out.println("Error: " + ex);
		}

	}
}