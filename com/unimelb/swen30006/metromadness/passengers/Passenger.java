package com.unimelb.swen30006.metromadness.passengers;

import com.unimelb.swen30006.metromadness.stations.Station;

public class Passenger {

	private Station currStation;
	private Station destination;
	

	private float travelTime;
	protected boolean reachedDestination;
	
	public Passenger(Station start, Station end){
		this.currStation = start;
		this.destination = end;
		this.reachedDestination = false;
		this.travelTime = 0;
	}
	
	public void update(float time){
		if(!this.reachedDestination){
			this.travelTime += time;
		}
	}
	
	public Station getCurrStation() {
		return currStation;
	}

	public Station getDestination() {
		return destination;
	}
}
