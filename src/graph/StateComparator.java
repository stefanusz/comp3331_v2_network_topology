package graph;

import java.util.Comparator;

public class StateComparator implements Comparator<State>{

	@Override
	public int compare(State s1, State s2) {
		
		if(s1.getPropagationDelay() > s2.getPropagationDelay()){
			return 1;
		}else if(s1.getPropagationDelay() < s2.getPropagationDelay()){
			return -1;
		}else{
			return 0;
		}
	}

}
