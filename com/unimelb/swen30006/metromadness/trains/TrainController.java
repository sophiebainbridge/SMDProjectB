package com.unimelb.swen30006.metromadness.trains;

import com.unimelb.swen30006.metromadness.stations.Station;
import com.unimelb.swen30006.metromadness.trains.Train.State;

public interface TrainController {
	public State update(State currSate, Station currStation, boolean forward, float delta);
	
	
}
