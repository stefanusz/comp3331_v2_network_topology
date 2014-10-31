package graph;

public class Packet {
	
	private double startTime;
	private double stopTime;
	private String source;
	private String destination;
	private double duration;

	public Packet(double startTime, double stopTime, double duration, String source, String destination){
		
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.source = source;
		this.destination = destination;
		this.duration = duration;
		
	}
	
	public double getDuration(){
		return this.duration;
	}
	
	public double getStartTime(){
		return this.startTime;
	}
	
	public double getStopTime(){
		return this.stopTime;
	}
	
	public String getSourceName(){
		return this.source;
	}
	
	public String getDestinationName(){
		return this.destination;
	}
	
}
