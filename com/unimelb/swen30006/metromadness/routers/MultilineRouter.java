package com.unimelb.swen30006.metromadness.routers;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public class MultilineRouter implements PassengerRouter {

	@Override
	public boolean shouldLeave(Station current, Passenger p) {
		//If this station is destination
		if(p.getDestination().equals(current))
			return true;
		else{
			//Look for next line that will take to destination
			for(Line l: current.getLines()){
				for(Station s: l.getStations()){
					if(p.getDestination().equals(s))
						return true;
				}
				
			}
		}
		return false;
	}

	@Override
	public boolean shouldBoard(Station current, Passenger p) {
		//See if train in station currently is the right one
		for(Train t: current.getTrains()){
			if(t.getTrainLine().getStations().contains(p.getDestination())){
				System.out.println("Get ON");
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean journeyComplete(Station current, Passenger p) {
		// TODO Auto-generated method stub
		return p.getDestination().equals(current);
	}

}
