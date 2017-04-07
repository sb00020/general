package uk.co.brett.maths.geometry;

import org.junit.Test;

public class CircleTest {

	@Test
	public void test() {
		Point p = new Point(0,0,0);
		Circle c = new Circle(p, 1);
		
		c.getYatX(Math.sqrt(1d/2d));
		c.getYatX(1);
		c.getYatX(0);
	
	}
	

}
