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
			g.setSize(j);
			
			
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
	
	public static void assignStateName(Graph g){
		for(Vertex vertex : g.Vertices){
			vertex.name = getName(vertex.id);
		}
	}

	private static String getName(Integer id) {
		String[] stateNames = new String[48];
		stateNames[1] = "Washington";
		stateNames[2] = "Oregon";
		stateNames[3] = "California";
		stateNames[4] = "Nevada";
		stateNames[5] = "Idaho";
		stateNames[6] = "Montana";
		stateNames[7] = "Utah";
		stateNames[8] = "Arizona";
		stateNames[9] = "Wyoming";
		stateNames[10] = "Colorado";
		stateNames[11] = "New Mexico";
		return stateNames[id];
	}

}
