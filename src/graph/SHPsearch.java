package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SHPsearch {
	
	private ArrayList<State> visited;
	private PriorityQueue<State> q;
	
	public ArrayList<String> search(Graph graph, String fromName, String toName){
		ArrayList<String> expandedNode = new ArrayList<String>();
		
		// INITIALISATION FOR ALL DIFFERENT COMPONENTS.
		Comparator<State> comparator = new StateComparator();
		q = new PriorityQueue<State>(20, comparator);
		visited = new ArrayList<State>();
		
		Node from = graph.findNode(fromName);
		
		
		State startState = new State(from.getNodeName(), null, 1);
		q.add(startState);
		
		State currentState = null;
		
		while(!q.isEmpty() && !isVisited(toName)){
			currentState = q.poll();
			visited.add(currentState);
			
			ArrayList<Edge> toAddEdge = graph.findNode(currentState.getStateName()).getConnectedEdge();
			
			for (Edge currentEdge: toAddEdge){
				String stateName = currentEdge.getEdgeToNode().getNodeName();
				
				int currentPropagation = 1 + currentState.getPropagationDelay();
				
				State newState = new State(stateName, currentState, currentPropagation);
				
				if(!isVisited(stateName)){
					
					if(!isToVisit(stateName)){
						q.add(newState);
					}else{
						State oldState = findToVisitState(stateName);
						
						if(newState.getPropagationDelay() < oldState.getPropagationDelay()){
							q.remove(oldState);
							q.add(newState);
						}
					}
					
				}
			}
				
		}
		
		for (State state : visited){
			expandedNode.add(state.getStateName());
		}
		
		return expandedNode;
	}
	
	
	public boolean isVisited(String to){
		
		for(State s: visited){
			if(s.getStateName().equals(to)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isToVisit (String stateName){
		
		for (State currentState : q){
			
			if(currentState.getStateName().equalsIgnoreCase(stateName)){
				return true;
			}
			
		}
		
		return false;
	}
	
	public State findToVisitState(String stateName){
		State result = null;
		
		for(State currentState : q){
			if(currentState.getStateName().equalsIgnoreCase(stateName)){
				result = currentState;
				break;
			}
		}
		
		return result;
	}
	

}
