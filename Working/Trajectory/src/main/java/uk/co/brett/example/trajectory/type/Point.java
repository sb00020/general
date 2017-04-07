package uk.co.brett.example.trajectory.type;

import uk.co.generic.LatLon;

public class Point {

	
	private double time;
	private LatLon latlon;
	
	public Point(double tIn, LatLon latlonIn){
		time = tIn;
		latlon = latlonIn;
	}
	
}
