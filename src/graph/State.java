package graph;

public class State {
	
	private State parent;
	private String name;
	private int propagationDelay;
	private double currentLoad;
	
	public State (String name, State parent, int propagationDelay, double currentLoad){
		this.name = name;
		this.parent = parent;
		this.propagationDelay = propagationDelay;
		this.currentLoad = currentLoad;
	}
	
	
	public String getStateName(){
		return this.name;
	}
	
	public State getParent(){
		return this.parent;
	}
	
	public int getPropagationDelay(){
		return this.propagationDelay;
	}
	
	public double getCurrentLoad(){
		return this.currentLoad;
	}


}
