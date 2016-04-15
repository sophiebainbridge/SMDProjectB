package com.unimelb.swen30006.metromadness.stations;

import java.util.ArrayList;
import java.util.Iterator;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.passengers.PassengerGenerator;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;
import com.unimelb.swen30006.metromadness.trains.Train.State;

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
		ArrayList<Passenger> newPassengers = generatePassengers(lines,delta);
		for(Train t: this.getTrains()){
			if(t.state.equals(State.IN_STATION)){
			disembarking(t);
			boarding(t, newPassengers);
			}
		}
		waiting.addAll(newPassengers);
		
		return newPassengers ;

	}

	public ArrayList<Passenger> generatePassengers(ArrayList<Line> lines, float delta){
		// Add the new passenger
		ArrayList<Passenger> ps = new ArrayList<Passenger>();
		if(timer >1){
			ps = this.g.generatePassengers(lines);
			this.waiting.addAll(ps);
			timer = 0;
			return ps;
		}
		timer += delta;
		return ps;
	}
	private void disembarking(Train t){
		Iterator<Passenger> pIter = t.passengers.iterator();
		while(pIter.hasNext()){
			Passenger p = pIter.next();
			try {
				if(this.getRouter().journeyComplete(this, p)){
					pIter.remove();
				}
				else if(this.getRouter().shouldLeave(this, p)){
					waiting.add(p);
					pIter.remove();
				}
			} catch (Exception e){
				// Do nothing, already waiting
				break;
			}
		}
		t.disembarked = true;
	}

		private void boarding(Train t, ArrayList<Passenger> newPassengers){
			// Add the waiting passengers
			Iterator<Passenger> pIter = this.waiting.iterator();
			while(pIter.hasNext()){
				Passenger p = pIter.next();
				try {
					if(this.getRouter().shouldBoard(this, p)){
						t.embark(p);
					}
					pIter.remove();
				} catch (Exception e){
					// Do nothing, already waiting
					break;
				}
			}
		}
	}


