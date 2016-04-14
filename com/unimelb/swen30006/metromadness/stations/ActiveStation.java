package com.unimelb.swen30006.metromadness.stations;

import java.util.ArrayList;
import java.util.Iterator;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.passengers.PassengerGenerator;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public class ActiveStation extends Station {

	public PassengerGenerator g;
	public ArrayList<Passenger> waiting;

	public ActiveStation(float x, float y, PassengerRouter router, String name, float maxPax) {
		super(x, y, router, name);
		this.waiting = new ArrayList<Passenger>();
		this.g = new PassengerGenerator(this, maxPax);
	}
	@Override
	public ArrayList<Passenger> update(ArrayList<Line> lines, float delta){
		// Add the new passenger
		if(timer >1){
			ArrayList<Passenger> ps = this.g.generatePassengers(lines);
			this.waiting.addAll(ps);
			if(this.getName().equals("Hurstbridge")){
				timer = 0;
			}
			return ps;
		}
		timer += delta;
		return null;

	}

	public void boarding(Train t){
		// Add the waiting passengers
		Iterator<Passenger> pIter = this.waiting.iterator();
		while(pIter.hasNext()){
			Passenger p = pIter.next();
			try {
				t.embark(p);
				pIter.remove();
			} catch (Exception e){
				// Do nothing, already waiting
				break;
			}
		}
	}
}


