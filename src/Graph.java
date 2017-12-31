/*
 * Martin Spassov 
 * 250901340
 * mspassov
 */
import java.io.*;
import java.util.*;
/*
 * Creates a graph with n number of nodes
 */
public class Graph {
	//Creating the instance variables
	Edge adjMatrix[][];
	Node nodeStorage[];
	int totalNodes;
	/*
	 * Constructor for the graph, initializes the graph and creates an adjacent matrix for the graph
	 */
	public Graph( int n) {
		/*
		 * Here the instance variables are initialized
		 */
		totalNodes=n;
		Node newNode;
		nodeStorage= new Node[n];
		/*
		 * Graph is filled with nodes
		 */
		for(int i=0; i<n; i++) {
			newNode= new Node(i);
			nodeStorage[i]=newNode;
		}
		/*
		 * Matrix is filled with null
		 */
		adjMatrix= new Edge[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				adjMatrix[i][j]=null;
			}
		}
	}

	/*
	 * This method inserts edges, takes in two nodes and and edge type
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException{
		/*
		 * Makes sure that edge can actually be inserted
		 */
		if(u == null || v == null || (u.getName()>=totalNodes || v.getName()>=totalNodes)) {
			throw new GraphException();
		}
		else {
			/*
			 * Insertion occurs here
			 */
			Edge newEdge= new Edge(u, v, edgeType);
			adjMatrix[u.getName()][v.getName()]=newEdge;
			adjMatrix[v.getName()][u.getName()]=newEdge;
		}
	}
	
	/*
	 * Method returns a node wit the given number
	 */
	public Node getNode(int name) throws GraphException{
		if(name>=totalNodes) {
			throw new GraphException();
		}
		else {
			return nodeStorage[name];
		}
	}
	
	/*
	 * Method returns an Iterator with with all the edges adjacent to the node
	 */
	public Iterator incidentEdges(Node u) throws GraphException{
		
		/*
		 * Makes sure that the node exists
		 */
		if(u == null || u.getName()>=totalNodes) {
			throw new GraphException();
		}
		
		/*
		 * Checks the matric and appends any edges to an ArrayList
		 */
		ArrayList<Edge> edgeStorage= new ArrayList();
		int empty=0;
		for(int j=0; j<totalNodes; j++) {
			if(adjMatrix[u.getName()][j]!=null) {
				edgeStorage.add(adjMatrix[u.getName()][j]);
				empty++;
			}
		}
		
		if(empty==0) {
			return null;
		}
		else {
			return edgeStorage.iterator();
		}
	}
	
	/*
	 * Method returns the edge between two nodes
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		/*
		 * checks that the nodes exist and if they have an edge in between
		 */
		if(u.getName()>=totalNodes || v.getName()>=totalNodes) {
			throw new GraphException();
		}
		
		if(u.getName()>=totalNodes || v.getName()>=totalNodes) {
			throw new GraphException();
		}
		
		if(adjMatrix[u.getName()][v.getName()]==null) {
			throw new GraphException();
		}
		else {
			return adjMatrix[u.getName()][v.getName()];
		}
	}
	
	/*
	 * Checks if the two nodes are beside each other
	 */
	 public boolean areAdjacent(Node u, Node v) throws GraphException{
		 /*
		  * Makes sure that the nodes exist
		  */
		 if(u == null || v == null || (u.getName()>=totalNodes || v.getName()>=totalNodes)) {
				throw new GraphException();
			}
		 
		 if(adjMatrix[u.getName()][v.getName()]==null) {
			return false;
		 }
		 else {
			 return true;
		 }
	 }
	

}
