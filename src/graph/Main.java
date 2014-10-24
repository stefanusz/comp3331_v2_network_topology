package graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		
		try {
			Scanner s = new Scanner(new FileReader(args[0])); 

			// TO GET ALL THE INPUT FROM THE FILE AND SPLIT IT BASE ON THE
			// COMMAND.
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
		
//		System.out.println("the num node -> " + graph.getNumNode());
//		System.out.println("the num edges -> " + graph.getNumEdges());
		//graph.getEdgesDetail();
		
		SHPsearch searchSHP = new SHPsearch();
		ShortestDelay searchShort = new ShortestDelay();
		ArrayList<String> result = searchSHP.search(graph, "A", "D");
		ArrayList<String> result2 = searchShort.search(graph, "A", "D");
		
		for(String s : result){
			System.out.println("test " + s);
		}
		
		for(String s : result2){
			System.out.println("test2 " + s);
		}

	}

}
