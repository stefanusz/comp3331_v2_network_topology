package graph;

import java.util.ArrayList;

public class Graph {
	
	private ArrayList<Node> graph;
	
	
	// CREATE A NEW GRAPH. 
	public Graph(){
		
		graph = new ArrayList<Node>();
		
	}
	
	// FIND A NODE FROM THE GRAPH WITH THEIR NAME
	public Node findNode (String name){
		Node result = null;
		
		for (Node current: graph){
			if(current.getNodeName().equalsIgnoreCase(name)){
				return current;
			}
		}
		
		return result;
	}
	
	// ADD A NEW NODE TO THE GRAPH.
	public void addNode(String name){
		
		if (!this.hasNode(name)){
			Node node = new Node(name);
			graph.add(node);
		}
		
	}
	
	
	// CHECK WHETHER GRAPH CONTAINS THIS NODE OR NOT. 
	public boolean hasNode (String name){
		
		for (Node current : graph){
			if(current.getNodeName().equalsIgnoreCase(name)){
				return true;
			}
		}
		
		return false;
	}
	
	// ADD AN EDGE BETWEEN THE FROM AND TO GRAPH. 
	public void addEdge (String fromName, String toName, int propagationDelay, int maxConnection){
		
		Node from = this.findNode(fromName);
		Node to = this.findNode(toName);
		
		if(from != null && to != null){
			Edge newEdge = new Edge(from, to, propagationDelay, maxConnection);
			//System.out.println("enter");
			
			
			
			if(!from.getConnectedEdge().contains(newEdge)){
				
				from.getConnectedEdge().add(newEdge);
			}
		}
		
	}
	
	
	// CHECKING GET NUM EDGES. 
	
	public int getNumEdges(){
		int result = 0;
		
		for(Node current : graph){
			result += current.getConnectedEdge().size();
			
		}
		
		return result;
				
			
	}
	
	// PRINT ALL THE DIFFERENT EDGES. 
	
	public void getEdgesDetail(){
		
		for (Node n: graph){
			
			for (Edge e: n.getConnectedEdge()){
				System.out.print(e.getEdgeFromNode().getNodeName() + " ");
				System.out.print(e.getEdgeToNode().getNodeName() + " ");
				System.out.print(e.getPropagationDelay() + " ");
				System.out.println(e.getMaxConnection());
			}
			
		}
	}
	
	
	// CHECKING TO GET NUM NODE
	
	public int getNumNode(){
		int result = 0;
		
		for(Node n: graph){
			result += 1;
			System.out.println(n.getNodeName());
		}
		return result;
	}

	

}
