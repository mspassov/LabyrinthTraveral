/*
 * Martin Spassov
 * 250901340
 * mspassov
 */

/*
 * Class represents one node from the graph
 */
public class Node {
	/*
	 * Creating variables here
	 */
	private int nName;
	private boolean nMark;
	
	/*
	 * Method initializes the instance variables
	 */
	public Node(int name) {
		nName=name;
		nMark=false;
	}
	/*
	 * Method updates the mark on each node
	 */
	public void setMark(boolean mark) {
		nMark=mark;
	}
	/*
	 * Method returns the mark on each node
	 */
	public boolean getMark() {
		return nMark;
	}
	/*
	 * method returns the name integer for each node
	 */
	public  int getName() {
		return nName;
	}
}
