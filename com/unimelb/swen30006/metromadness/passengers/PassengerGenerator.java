package com.unimelb.swen30006.metromadness.passengers;

import java.util.ArrayList;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.tracks.Line;

public class PassengerGenerator {
	int count = 0;
	// The station that passengers are getting on
	public Station currStation;
	// The line they are travelling on
	//public ArrayList<Line> lines;

	// The max volume
	public float maxVolume;

	public PassengerGenerator(Station s, float max){
		this.currStation = s;
		this.maxVolume = max;
	}

	public ArrayList<Passenger> generatePassengers(ArrayList<Line> lines){
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		if(Math.random()>0.5f)
			passengers.add(generatePassenger(lines));
		return passengers;
	}

	public Passenger generatePassenger(ArrayList<Line> lines){
		// Pick a random line
		Line destLine = lines.get((int)Math.random()*(lines.size()-1));
		Station destStation = destLine.getStations().get((int)Math.random()*(destLine.getStations().size()-1));
		return new Passenger(this.currStation, destStation);
		/*if(count==0&&this.equals("Hurstbridge")){
			for(Line l: lines){
			Station start=null, dest=null;
			for(Station s: l.getStations()){
				if(s.getName().equals("Hurstbridge"))
					start = s;
				else if(s.getName().equals("Frankston"))
					dest = s;
			}
			System.out.println("New Passenger");
			return new Passenger(start,dest);
			}			
		}
		count = 1;
		return null;*/
	}

}
