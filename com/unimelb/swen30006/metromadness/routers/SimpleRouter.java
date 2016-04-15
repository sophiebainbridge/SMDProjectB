package com.unimelb.swen30006.metromadness.routers;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;

public class SimpleRouter implements PassengerRouter {

	@Override
	public boolean shouldLeave(Station current, Passenger p) {
		return current.equals(p.getDestination());
	}

	@Override
	public boolean journeyComplete(Station current, Passenger p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldBoard(Station current, Passenger p) {
		// TODO Auto-generated method stub
		return false;
	}

}
