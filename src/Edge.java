/*
 * Marin Spassov
 * 250901340
 * mspassov
 */
/*
 * Method represents an edge from a graph
 */
public class Edge {
	/*
	 * Creating the variables here
	 */
	private Node endpoint1;
	private Node endpoint2;
	private String edgeType;
	
	/*
	 * Constructor initializes instance variables here
	 */
	public Edge(Node u, Node v, String type) {
		endpoint1=u;
		endpoint2=v;
		edgeType=type;
	}
	
	/*
	 * Method returns the first endpoint of a graph
	 */
	public Node firstEndpoint() {
		return endpoint1;
	}
	
	/*
	 * Method returns the second endpoint of the graph
	 */
	public Node secondEndpoint() {
		return endpoint2;
	}
	/*
	 * Method returns the type of the edge
	 */
	public String getType() {
		return edgeType;
	}
	
	/*
	 * Method updates the type of th edge
	 */
	public void setType(String type) {
		edgeType=type;
	}
	
	
	
	
	
}
