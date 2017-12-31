/*
 * Martin Spassov
 * mspassov
 * 250901340
 */

import java.io.*;
import java.util.*;

/*
 * Class creates a Labyrinth and a solution to it
 */
public class Labyrinth {
	/*
	 * All the variables are created here
	 * The width, the length and the number of bombs are initialized form the first lines of the text file
	 */
	private Graph LabyrinthGraph;
	private int width;   
	private int length; 
	private int brickBombs;
	private int acidBombs;
	private int entrance;
	private int exit;
	
	/*
	 * Stack is created to show store the solution
	 */
	private Stack<Node> stack;
	/*
	 * This method creates the labyrinth and the accompanying graph
	 */
	public Labyrinth(String inputFile) throws LabyrinthException{
		/*
		 * Setting up variables to read in the file
		 */
		BufferedReader in;
		String line;
	    String fileName = inputFile;
	    stack=new Stack<Node>();		//Creating the stack here
	    try {
	    	/*
	    	 * Files is read in and the first 6 lines are set to the appropriate variables, except th efirst line which is discarded
	    	 */
	    	in = new BufferedReader(new FileReader(fileName));
	    	line=in.readLine();
	    	width=Integer.parseInt(in.readLine());
	    	length=Integer.parseInt(in.readLine());
	    	brickBombs=Integer.parseInt(in.readLine());
	    	acidBombs=Integer.parseInt(in.readLine());
	    	line=in.readLine();
	    	
	    	LabyrinthGraph= new Graph(width*length);		//Graph is created
	    	int cellNum=0;
	    	
	    	/*
	    	 * A while loop is run to read the whole text file
	    	 */
	    	while(line!=null) {
	    		
	    		/*
	    		 * For loop represents every line of the text file.
	    		 * I have implemented an equation to check the number of the node relative to the position in the for loop
	    		 * It works by keeping track of the row in the file and then using the width to compute where exactly it exists within the 
	    		 * row.
	    		 */
	    		for(int i=0; i<(2*width-1); i++) {
	    			/*
	    			 * First checks if the character is an entrance or exit
	    			 */
	    			if(line.charAt(i)=='b') {
	    				entrance=((cellNum/2)*width)+(i/2);
	    			}
	    			else if(line.charAt(i)=='x') {
	    				exit=((cellNum/2)*width)+(i/2);
	    			}
	    			
	    			/*
	    			 * Then checks if it a normal brick wall
	    			 */
	    			else if(line.charAt(i)=='h' || line.charAt(i)=='v') {
	    				if(line.charAt(i)=='h') {
	    					try {
	    						
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))), LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))+1), "wall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here1");
	    						e.getMessage();
	    					}
	    				}
	    				else {
	    					try {
	    						
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode(((((cellNum+1)/2)*width)+(i/2))), LabyrinthGraph.getNode(((((cellNum-1)/2)*width)+(i/2))), "wall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here2");
	    						e.getMessage();
	    					}
	    				}
	    				
	    			}
	    			/*
	    			 * Then checks if it is a thick Wall
	    			 */
	    			else if(line.charAt(i)=='H' || line.charAt(i)=='V') {
	    				if(line.charAt(i)=='H') {
	    					try {
	    						//System.out.println(i+" "+i+1);
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))), LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))+1), "thickWall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here3");
	    						e.getMessage();
	    					}
	    				}
	    				else {
	    					try {
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode(((((cellNum+1)/2)*width)+(i/2))), LabyrinthGraph.getNode(((((cellNum-1)/2)*width)+(i/2))), "thickWall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here4");
	    						e.getMessage();
	    					}
	    				}
	    			}
	    			/*
	    			 * Then checks if it is a metal wall
	    			 */
	    			else if(line.charAt(i)=='m' || line.charAt(i)=='M') {
	    				if(line.charAt(i)=='m') {
	    					try {
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))), LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))+1), "metalWall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here5");
	    						e.getMessage();
	    					}
	    				}
	    				else {
	    					try {
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode(((((cellNum+1)/2)*width)+(i/2))), LabyrinthGraph.getNode(((((cellNum-1)/2)*width)+(i/2))), "metalWall");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here6");
	    						e.getMessage();
	    					}
	    				}
	    			}
	    			/*
	    			 * Then checks if it is a corridor
	    			 */
	    			else if(line.charAt(i)=='-' || line.charAt(i)=='|') {
	    				if(line.charAt(i)=='-') {
	    					try {
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))), LabyrinthGraph.getNode((((cellNum*width)/2)+(i/2))+1), "corridor");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here7");
	    						e.getMessage();
	    					}
	    				}
	    				else {
	    					
	    					try {
	    						LabyrinthGraph.insertEdge(LabyrinthGraph.getNode(((((cellNum+1)/2)*width)+(i/2))), LabyrinthGraph.getNode(((((cellNum-1)/2)*width)+(i/2))), "corridor");
	    					}
	    					catch(GraphException e) {
	    						System.out.println("here8");
	    						e.getMessage();
	    					}
	    				}
	    			}
	    			
	    		}
	    		/*
	    		 * Increments row count, and reads in the next line
	    		 */
	    		cellNum++;
	    		line=in.readLine();
	    	}
	    	/*
	    	 * closes file
	    	 */
	    	in.close();
	
	    }
	    catch(IOException e) {
	    	throw new LabyrinthException();
	    }
	    
	    
	}
	
	/*
	 * Helper method which backtracks depending on which type of edge you are returning from
	 */
	private void backtrack(String type) {
		if(type=="wall") {
			brickBombs++;
		}
		else if(type=="thickWall") {
			brickBombs++;
			brickBombs++;
		}
		else if(type=="metalWall") {
			acidBombs++;
		}
	}
	/*
	 * Helper method which populates the stack with the solution
	 */
	private boolean path(Node u, Node v) {
		
		/*
		 * The node is pushed onto the stack and an iterator is set up to get its incident edges
		 */
		Iterator<Edge> iter=null;
		u.setMark(true);
		stack.push(u);
		System.out.println("Pushed "+stack.peek().getName()+" ");
		
		/*
		 * If the two nodes are equal then method just returns true
		 */
		if(u.getName()==v.getName()) {
			return true;
		}
		else {
			try {
				iter=LabyrinthGraph.incidentEdges(u);
			}
			catch(GraphException e) {
				e.getMessage();
			}
			try {
				
				/*
				 * while loop is run to check if each incident edge is a potential path to the exit
				 */
				while(iter.hasNext()) { //For each edge (u, v) incident on u
					
					/*
					 * The current node and the edge that we are examining is defined here. Makes sure to compare both endpoints
					 * to make sure that the algorithm does not compare the same one as th eone being called.
					 */
					Edge currentEdge=iter.next(); 
					
					Node current=currentEdge.secondEndpoint();
					if(current.getName()==u.getName()) {
						current=currentEdge.firstEndpoint();
					}
					
					/*
					 * The following if statements check to see if it is a certain type of edge
					 * The method continues only if there are enough bombs to go through it.
					 * If there are not, then the method backtracks using the helper function
					 */
					if(current.getMark()==false) {
							if(currentEdge.getType().equals("corridor")) {
								if(this.path(current, LabyrinthGraph.getNode(exit))==true) {
									return true;
								}
								
							}
							if(currentEdge.getType().equals("wall")) {
								
								if(brickBombs>0) {
									brickBombs--;
									if(this.path(current, LabyrinthGraph.getNode(exit))==true) {
										return true;
									}
									else {
										backtrack("wall");
									}
									
								}
								
							}
							if(currentEdge.getType().equals("thickWall")) {
								
								if(brickBombs>=2) {
									brickBombs--;
									brickBombs--;
									if(this.path(current, LabyrinthGraph.getNode(exit))==true) {
										return true;
									}
									else {
										backtrack("thickWall");
									}
								}
							}
							if(currentEdge.getType().equals("metalWall")) {
								if(acidBombs>0) {
									acidBombs--;
									if(this.path(current, LabyrinthGraph.getNode(exit))==true) {
										return true;
									}
									else {
										backtrack("metalWall");
									}
								}
							}
							/*
							 * If it is none of the special cases, then the algorithm just continues onto the next incident edge
							 * Here for completeness
							 */
							if(currentEdge.getType().equals("corridor")) {
								continue;
							}
					}
				}
			}
			catch(Exception e) {
				e.getMessage();
			}
		}
		/*
		 * Finally the node is set to false and popped off of the stack, since it will not lead to the correct path
		 */
		u.setMark(false);
		
		System.out.println("Popped: "+stack.pop().getName()+" ");
		return false;
	}
	
	/*
	 * Method which returns the solution
	 */
	public Iterator solve() {
		try {
			path(LabyrinthGraph.getNode(entrance), LabyrinthGraph.getNode(exit));
		}
		catch(GraphException e) {
			e.getMessage();
		}
		
		if(stack.isEmpty()) {
			return null;
		}
		else {
			return stack.iterator();
		}
	}
	
	
	public static void main(String [] args){
		try {
			Labyrinth test= new Labyrinth(args[0]);
			Edge edge=test.LabyrinthGraph.getEdge(test.LabyrinthGraph.getNode(1), test.LabyrinthGraph.getNode(6));
			System.out.println(edge.getType());
			Iterator<Edge> it=test.LabyrinthGraph.incidentEdges(test.LabyrinthGraph.getNode(9));
			/*
			while(it.hasNext()) {
				System.out.println(it.next().getType());
				if(it.next().getType().equals("metalWall")) {
					System.out.println("look at this");
				}
			}
			*/
			//System.out.println(test.exit);
			
			
		}
		catch(Exception e) {
			
		}
		
		
	}

}