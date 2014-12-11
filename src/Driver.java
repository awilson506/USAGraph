/*
 * Name: Adam Wilson
 * StudentID: 0543568
 * Project: 6
 * Class: COSC 600
 * 
 * This program uses the graph class to build a graph based on 
 * the United States.  I reads in the vertices and edges from 
 * input.txt based on the numbering of the inland states 1 through 48
 * It then does a Breadth and Depth first traversal of the graph
 * and prints out the level and state names that were visited.
 * I worked on getting the four color theorem to work correctly
 * but ran out of time trying to figure out a null pointer exception
 * that I was getting.
 * 
 */

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Driver {
	public static void main(String args[]) {
		try {
			PrintStream out = new PrintStream(
					new FileOutputStream("output.txt"));
			System.setOut(out);

			Graph g = new Graph();
			g = BuildGraph.getMap(g);
			BuildGraph.assignStateName(g);
			g.breadthFirstTraversal(1);
			System.out.println("\n");
			g.depthFirstTraversal(1);
			
			
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

	}
}