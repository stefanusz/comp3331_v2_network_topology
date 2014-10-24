package graph;

public class Edge {
	
	private Node from;
	private Node to;
	private int propagationDelay;
	private int maxConnections;

	public Edge (Node from, Node to, int propagationDelay, int maxConnection){
		this.from = from;
		this.to = to;
		this.propagationDelay = propagationDelay;
		this.maxConnections = maxConnection;
		
	}
	
	public Node getEdgeFromNode (){
		return this.from;
	}
	
	public Node getEdgeToNode(){
		return this.to;
	}
	
	public int getPropagationDelay(){
		return this.propagationDelay;
	}
	
	public int getMaxConnection(){
		return this.maxConnections;
	}
	
}
