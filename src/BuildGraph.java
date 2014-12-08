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
		String[] stateNames = new String[49];
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
		stateNames[12] = "North Dakota";
		stateNames[13] = "South Dakota";
		stateNames[14] = "Nebraska";
		stateNames[15] = "Kansas";
		stateNames[16] = "Oklahoma";
		stateNames[17] = "Texas";
		stateNames[18] = "Minnesota";
		stateNames[19] = "Iowa";
		stateNames[20] = "Missouri";
		stateNames[21] = "Arkansas";
		stateNames[22] = "Louisiana";
		stateNames[23] = "Wisconsin";
		stateNames[24] = "Illinois";
		stateNames[25] = "Mississippi";
		stateNames[26] = "Michigan";
		stateNames[27] = "Indiana";
		stateNames[28] = "Kentucky";
		stateNames[29] = "Tennessee";
		stateNames[30] = "Alabama";
		stateNames[31] = "Ohio";
		stateNames[32] = "West Virginia";
		stateNames[33] = "Virginia";
		stateNames[34] = "North Carolina";
		stateNames[35] = "South Carolina";
		stateNames[36] = "Georgia";
		stateNames[37] = "Florida";
		stateNames[38] = "Maine";
		stateNames[39] = "New Hampshire";
		stateNames[40] = "Vermont";
		stateNames[41] = "New York";
		stateNames[42] = "Pennsylvania";
		stateNames[43] = "Maryland";
		stateNames[44] = "Massachusetts";
		stateNames[45] = "Rhode Island";
		stateNames[46] = "Connecticut";
		stateNames[47] = "New Jersey";
		stateNames[48] = "Delaware";
		return stateNames[id];
	}

}
