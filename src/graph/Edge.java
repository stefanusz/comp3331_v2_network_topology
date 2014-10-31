package graph;

public class Edge {
	
	private Node from;
	private Node to;
	private int propagationDelay;
	private int maxConnections;
	private int currentConnection;
	private double currentLoad; 
	
	public Edge (Node from, Node to, int propagationDelay, int maxConnection){
		this.from = from;
		this.to = to;
		this.propagationDelay = propagationDelay;
		this.maxConnections = maxConnection;
		this.currentConnection = 0;
		
	}
	
	public void getFromToName(){
		System.out.print("FROM "+ this.from.getNodeName());
		System.out.print(" TO "+ this.to.getNodeName());
		System.out.print(" MAX CON "+ this.maxConnections);
		System.out.print(" CURRENT CON "+ this.currentConnection);
		System.out.print(" CURRENT LOAD "+ getCurrentLoad());
		System.out.println();

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
	
	public void addCurrentConnection(){
		this.currentConnection = this.currentConnection + 1;
	}
	
	public void minusCurrentConnection(){
		this.currentConnection = this.currentConnection - 1;
	}
	
	public int getCurrentConnection(){
		return this.currentConnection;
	}
	
	public double getCurrentLoad (){
		
		this.currentLoad = (double) this.currentConnection / (double) this.maxConnections;
		
		return currentLoad;
		
	}
	
}
