package uk.co.brett.maths.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class OblateSpheroidTest {

	@Test
	public void test() {
		Point cent = new Point(0,0,0);
		
		OblateSpheroid o =new OblateSpheroid ( cent, 1d, 0.8d);
		
		//Point p = o.getPointAtAngles(0, 0);
		System.out.println(o.getPointAtAngles(0, 0));
		System.out.println(o.getPointAtAngles(Math.toRadians(90d), Math.toRadians(90d)));
		System.out.println(o.getPointAtAngles(Math.toRadians(45d), Math.toRadians(45d)));
		
	
	}

}
