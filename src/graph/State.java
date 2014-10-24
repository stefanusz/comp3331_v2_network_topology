package graph;

public class State {
	
	private State parent;
	private String name;
	private int propagationDelay;
	
	public State (String name, State parent, int propagationDelay){
		this.name = name;
		this.parent = parent;
		this.propagationDelay = propagationDelay;
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

}
