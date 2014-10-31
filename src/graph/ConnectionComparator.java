package graph;

import java.util.Comparator;

public class ConnectionComparator implements Comparator<State>{

	@Override
	public int compare(State s1, State s2) {
		
		if(s1.getCurrentLoad() > s2.getCurrentLoad()){
			return 1;
		}else if(s1.getCurrentLoad() < s2.getCurrentLoad()){
			return -1;
		}else{
			return 0;
		}
	}

}
