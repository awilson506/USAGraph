import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class BuildGraph {

	public static Graph getMap(Graph g) {
		try {

			BufferedReader vertexInput = new BufferedReader(new FileReader(
					"input.txt"));
			BufferedReader edgeInput = new BufferedReader(new FileReader(
					"input.txt"));
			String line = null;
			Integer i = 1;
			Integer j = 1;
			
			while ((line = vertexInput.readLine()) != null) {
				g.addVertex(j);
				j++;
			}
			
			while ((line = edgeInput.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(line);
				while (tokens.hasMoreTokens()) {
					g.addEdge(i, Integer.parseInt(tokens.nextToken()));
				}
				i++;
			}
			vertexInput.close();
			edgeInput.close();
			
			return g;
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

}
