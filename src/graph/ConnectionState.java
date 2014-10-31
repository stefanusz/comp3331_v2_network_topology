package graph;

import java.util.ArrayList;

public class ConnectionState {
	
	
	private ArrayList<String> path;
	private double stopTime;
	
	
	public ConnectionState(ArrayList<String> path, double stopTime){
		this.path = path;
		this.stopTime = stopTime;
	}
	
	public double getStopTime(){
		return this.stopTime;
	}
	
	public ArrayList<String> getConnectionPath(){
		return this.path;
	}
	
	public void printConnection (){
		
		for(String s : this.path){
			System.out.print(s);
		}
		System.out.println();
	}
	

}
