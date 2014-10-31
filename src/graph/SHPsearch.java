package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SHPsearch {
	
	private ArrayList<State> visited;
	private PriorityQueue<State> q;
	
	public ArrayList<String> search(Graph graph, String fromName, String toName){
		
		
		ArrayList<String> path = new ArrayList<String>();
		
		//ArrayList<String> expandedNode = new ArrayList<String>();
		
		// INITIALISATION FOR ALL DIFFERENT COMPONENTS.
		Comparator<State> comparator = new StateComparator();
		q = new PriorityQueue<State>(20, comparator);
		visited = new ArrayList<State>();
		
		Node from = graph.findNode(fromName);
		
		
		State startState = new State(from.getNodeName(), null, 0, 0);
		q.add(startState);
		
		State currentState = null;
		
		while(!q.isEmpty() && !isVisited(toName)){
			currentState = q.poll();
			visited.add(currentState);
			
			ArrayList<Edge> toAddEdge = graph.findNode(currentState.getStateName()).getConnectedEdge();
			//System.out.println("==================>>CURRENT : " + currentState.getStateName());
			for (Edge currentEdge: toAddEdge){
				String stateName = currentEdge.getEdgeToNode().getNodeName();
				//System.out.println("STATE NAME : " + stateName);
				int currentPropagation = 1 + currentState.getPropagationDelay();
				//System.out.println("PROP DELAY : " + currentPropagation);
				State newState = new State(stateName, currentState, currentPropagation, 0);
				
				if(!isVisited(stateName)){
					
					if(!isToVisit(stateName)){
						q.add(newState);
					} else {
						State oldState = findToVisitState(stateName);
						
						if(newState.getPropagationDelay() < oldState.getPropagationDelay()){
							q.remove(oldState);
							q.add(newState);
						}
					}
					
				}
			}
				
		}
	
		
		//System.out.println("to be added to path : " + path.get(0));
		//System.out.println("the size of VISITED "+ visited.size());
		
		path.add(visited.get(visited.size()-1).getStateName());
		
		for (int j=visited.size()-1; j> 0; j-- ){

			if(!path.contains(visited.get(j).getParent().getStateName()) 
					&& visited.get(j).getStateName().equals(path.get(0))){
				path.add(0, visited.get(j).getParent().getStateName());
			}
		}

		return path;
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
