package graph;

import java.util.Comparator;

public class PacketComparator implements Comparator<Packet>{

	@Override
	public int compare(Packet p1, Packet p2) {
		
		if(p1.getStartTime() > p2.getStartTime()){
			return 1;
		}else if(p1.getStartTime() < p2.getStartTime()){
			return -1;
		}else{
			return 0;
		}
	}

}
