package com.unimelb.swen30006.metromadness.routers;

import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.stations.Station;

public class BranchRouter implements PassengerRouter {

	@Override
	public boolean shouldLeave(Station current, Passenger p) {
		return current.equals(p.getDestination());
		
	}

	@Override
	public boolean getOn(Station current, Passenger p) {
		return true;
	}

}
