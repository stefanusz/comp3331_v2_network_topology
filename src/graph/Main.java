package graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		
		// TO BUILD THE GRAPH WITH ALL THE DIFFERENT NODES. 
		try {
			Scanner s = new Scanner(new FileReader(args[2])); 

			while (s.hasNext() && s.hasNextLine()) {

				String[] input = s.nextLine().split(" ");

				String n1 = input[0];
				String n2 = input[1];

				int propagationDelay = Integer.parseInt(input[2]);
				int maxConnection = Integer.parseInt(input[3]);

				graph.addNode(n1);
				graph.addNode(n2);

				graph.addEdge(n1, n2, propagationDelay, maxConnection);
				graph.addEdge(n2, n1, propagationDelay, maxConnection);

			}
			
			s.close();
		} catch (FileNotFoundException e) {
		}
		
		// END OF MAKING GRAPH. 

		// THE START OF SEARCH DEPENDING ON ALGOS AND TYPE. 
		
		// INITILISATION OF VARIABLE. 
		ArrayList<ConnectionState> connections = new ArrayList<ConnectionState>();
		double totalPacket = 0;
		double successPacket = 0;
		double blockPacket = 0;
		int totalCon = 0;
		double totalHops = 0;
		double pathDelay = 0;
		double failPercent = 0;
		double successPercent = 0;
		double averageHops = 0;
		double averageDelay = 0;
		SHPsearch searchSHP = null;
		LeastLoaded searchLLP = null;
		ShortestDelay searchSDP = null;
		ArrayList<String> path = null;
		 
		// START OF BUILDING THE DIFFERENT CONNECTION AND DROPPING IT. 
		try {
			Scanner s = new Scanner(new FileReader(args[3])); 
			String algorithm = args[1];
			String types = args[0];
			
			if(types.equalsIgnoreCase("circuit")){

					while (s.hasNext() && s.hasNextLine()) {

						String[] input = s.nextLine().split(" ");

						// INITIALISATION
						double startTime = Double.parseDouble(input[0]) ;
						String source = input[1];
						String destination = input[2];
						double duration = Double.parseDouble(input[3]);
						double stopTime = startTime + duration;
						double tempStartTime = startTime;
						
						//ITERATOR TESTING. 
						
								Iterator<ConnectionState> i = connections.iterator();
								while (i.hasNext()) {
								   ConnectionState cs = i.next(); // must be called before you can call i.remove()
								   
								   if(tempStartTime > cs.getStopTime()){
									   
									   ArrayList<String> pathTaken = cs.getConnectionPath();
									   
									   for(int j=0; j<pathTaken.size()-1; j++){
											Node from = graph.findNode(pathTaken.get(j));
											Node to = graph.findNode(pathTaken.get(j+1));
											
											// GET ALL THE EDGES FROM BOTH NODE. 
											
											ArrayList<Edge> connectedFromEdges = from.getConnectedEdge();
											ArrayList<Edge> connectedToEdges = to.getConnectedEdge();
											for (int k=0; k < connectedFromEdges.size(); k++){
												
												if(connectedFromEdges.get(k).getEdgeToNode().equals(to)){
													
													connectedFromEdges.get(k).minusCurrentConnection();

													for (int m = 0; m <connectedToEdges.size(); m++){
														
														if(connectedToEdges.get(m).getEdgeToNode().equals(from)){
															

															connectedToEdges.get(m).minusCurrentConnection();
														}
													}
													
												}
												
											}
									   }
									   
									   i.remove();
								   }
								   
								}

						// DETERMINE WHICH ALGO TO RUN FOR. 
								
						if(algorithm.equalsIgnoreCase("SHP")){
									 searchSHP = new SHPsearch();
									 path = searchSHP.search(graph, source, destination);
						}else if (algorithm.equalsIgnoreCase("SDP")){
									searchSDP = new ShortestDelay();
									path = searchSDP.search(graph, source, destination);
						}else if (algorithm.equalsIgnoreCase("LLP")){
							searchLLP = new LeastLoaded();
							path = searchLLP.search(graph, source, destination);
							
						}
						
						totalHops += path.size()-1;
						pathDelay += findPathDelay(graph, path);
						
						boolean status = false;
						//System.out.println("THE STOP TIME IS "+ stopTime);
						//System.out.println();
						ArrayList<Node> backTrackPath = new ArrayList<Node>();

						outerloop:
						for (int z=0; z<path.size()-1; z++){

							Node from = graph.findNode(path.get(z));
							Node to = graph.findNode(path.get(z+1));
							backTrackPath.add(from);

							// TO ADD TO THE FROM SIDE OF EDGES. 
							ArrayList<Edge> connectedFromEdges = from.getConnectedEdge();
							ArrayList<Edge> connectedToEdges = to.getConnectedEdge();
							
							//System.out.println("THE FROM NODE IS " + from.getNodeName());
							for(int x=0 ; x<connectedFromEdges.size(); x++){
								
								Edge currentFromEdge = connectedFromEdges.get(x);

								if(currentFromEdge.getEdgeToNode().equals(to)){

									if(currentFromEdge.getCurrentConnection() != currentFromEdge.getMaxConnection()){
										currentFromEdge.addCurrentConnection();

										status = true;
										for(int n=0; n<connectedToEdges.size(); n++){
											Edge currentToEdge = connectedToEdges.get(n);

											if(currentToEdge.getEdgeToNode().equals(from)){
												
												currentToEdge.addCurrentConnection();
							
											}
										}

									}else{
										
										status = false;

											 for(int j=0; j<backTrackPath.size()-1; j++){

													Node fromB = backTrackPath.get(j);
													Node toB = backTrackPath.get(j+1);
													
													// GET ALL THE EDGES FROM BOTH NODE. 
													
													ArrayList<Edge> connectedFromEdgesB = fromB.getConnectedEdge();
													ArrayList<Edge> connectedToEdgesB = toB.getConnectedEdge();
													for (int k=0; k < connectedFromEdgesB.size(); k++){
														
														if(connectedFromEdgesB.get(k).getEdgeToNode().equals(toB)){
															
															connectedFromEdgesB.get(k).minusCurrentConnection();

															for (int m = 0; m <connectedToEdgesB.size(); m++){
																
																if(connectedToEdgesB.get(m).getEdgeToNode().equals(fromB)){
																	

																	connectedToEdgesB.get(m).minusCurrentConnection();
																}
															}
															
														}
														
													}
											   }

										break outerloop;
									}
									
								}

							}

						}

						if(status == true){
							successPacket = successPacket + Math.ceil(duration*Double.parseDouble(args[4]));
							ConnectionState con = new ConnectionState(path, stopTime);
							connections.add(con);
						}else{
							
							blockPacket = blockPacket +  Math.ceil(duration*Double.parseDouble(args[4]));

						}

						totalPacket = totalPacket +  Math.ceil(duration*Double.parseDouble(args[4]));
						totalCon += 1;
						
					}
					
					failPercent = (blockPacket/totalPacket)*100;
					successPercent = (successPacket/totalPacket)*100;
					averageHops = totalHops / totalCon;
					averageDelay = pathDelay / totalCon;
					System.out.println("total number of virtual circuit requests: "+ totalCon);
					System.out.println("total number of packets: "+ totalPacket);
					System.out.println("number of successfully routed packets: "+ successPacket);
					System.out.println("percentage of successfully routed packets: "+ round(successPercent,2));
					System.out.println("number of blocked packets: "+ blockPacket);
					System.out.println("percentage of blocked packets: "+ round(failPercent,2));
					System.out.println("average number of hops per circuit: "+ round(averageHops,2));
					System.out.println("average cumulative propagation delay per circuit: "+ round(averageDelay,2));

			}else{
				
			}

			s.close();
		} catch (FileNotFoundException e) {
		}

		
	}
	
	
	// HELPER FUNCTION. 
	
	public static double findPathDelay (Graph graph, ArrayList<String> path){
		double result = 0;
		
		for(int i=0; i<path.size()-1; i++){
			Node from = graph.findNode(path.get(i));
			Node to = graph.findNode(path.get(i+1));
			
			ArrayList<Edge> connectedFromEdges = from.getConnectedEdge();
			
			for(int j=0; j<connectedFromEdges.size(); j++){
				if(connectedFromEdges.get(j).getEdgeToNode().equals(to)){
					result += connectedFromEdges.get(j).getPropagationDelay();
				}
			}

		}
		
		
		return result;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
