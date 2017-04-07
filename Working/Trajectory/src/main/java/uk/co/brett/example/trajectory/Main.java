package uk.co.brett.example.trajectory;

import uk.co.generic.Cartesian;
import uk.co.generic.LatLon;

public class Main {

	public static void main(){
		
		LatLon start = new LatLon(-33d,	25d,0d); //port Eliz
		LatLon end = new LatLon(60d,	25d,0d); // Helsinki

		Cartesian startC = Cartesian.createCartesian(start);
		
		LatLon start2 = LatLon.createLatLon(startC);
		
		System.out.println(start2.getX() +"   "+ start2.getY()  +"   "+  start2.getZ());
		
	}

}