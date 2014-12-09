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
		stateNames[0] = "Washington";
		stateNames[1] = "Oregon";
		stateNames[2] = "California";
		stateNames[3] = "Nevada";
		stateNames[4] = "Idaho";
		stateNames[5] = "Montana";
		stateNames[6] = "Utah";
		stateNames[7] = "Arizona";
		stateNames[8] = "Wyoming";
		stateNames[9] = "Colorado";
		stateNames[10] = "New Mexico";
		stateNames[11] = "North Dakota";
		stateNames[12] = "South Dakota";
		stateNames[13] = "Nebraska";
		stateNames[14] = "Kansas";
		stateNames[15] = "Oklahoma";
		stateNames[16] = "Texas";
		stateNames[17] = "Minnesota";
		stateNames[18] = "Iowa";
		stateNames[19] = "Missouri";
		stateNames[20] = "Arkansas";
		stateNames[21] = "Louisiana";
		stateNames[22] = "Wisconsin";
		stateNames[23] = "Illinois";
		stateNames[24] = "Mississippi";
		stateNames[25] = "Michigan";
		stateNames[26] = "Indiana";
		stateNames[27] = "Kentucky";
		stateNames[28] = "Tennessee";
		stateNames[29] = "Alabama";
		stateNames[30] = "Ohio";
		stateNames[31] = "West Virginia";
		stateNames[32] = "Virginia";
		stateNames[33] = "North Carolina";
		stateNames[34] = "South Carolina";
		stateNames[35] = "Georgia";
		stateNames[36] = "Florida";
		stateNames[37] = "Maine";
		stateNames[38] = "New Hampshire";
		stateNames[39] = "Vermont";
		stateNames[40] = "New York";
		stateNames[41] = "Pennsylvania";
		stateNames[42] = "Maryland";
		stateNames[43] = "Massachusetts";
		stateNames[44] = "Rhode Island";
		stateNames[45] = "Connecticut";
		stateNames[46] = "New Jersey";
		stateNames[47] = "Delaware";
		return stateNames[id - 1];
	}

}
