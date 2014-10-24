package graph;

import java.util.ArrayList;

public class Node {
	
	private String name;
	private ArrayList<Edge> connectedEdges;
	
	
	public Node (String name){
		this.name = name;
		connectedEdges = new ArrayList<Edge>();
		
	}
	
	public String getNodeName(){
		return this.name;
	}
	
	public ArrayList<Edge> getConnectedEdge(){
		return this.connectedEdges;
	}

}
