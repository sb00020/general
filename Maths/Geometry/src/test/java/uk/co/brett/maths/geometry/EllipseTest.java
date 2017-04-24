package uk.co.brett.maths.geometry;


import java.lang.Math.*;
import org.junit.Assert;
import org.junit.Test;

public class EllipseTest {

	@Test
	public void test() {
		Point p = new Point(0d,0d,0d);
		Ellipse e = new Ellipse(p, 1.d, 0.8d);
		
		//System.out.println(e.getYatX(1d/Math.sqrt(2d)));
		
		Circle c = new Circle(p, 1d);
		Point p2 = new Point(1d/Math.sqrt(2d),1d/Math.sqrt(2d),0d);
		
		Line l = c.getTangent(p2);
		
		//System.out.println(l.getXIntercept(p2));
		
		Point elPoint = new Point(1d/Math.sqrt(2d), e.getYatX(1d/Math.sqrt(2d)).getP1(),0d);
		Line el = e.getTangent(elPoint);

	}
	@Test
	public void test2() {
		Point p = new Point(0d,0d,0d);
		Ellipse e = new Ellipse(p, 1.d, 0.8d);
		
		//System.out.println(e.getYatX(1d/Math.sqrt(2d)));
		double x = -0.2;
		Circle c = new Circle(p, 1d);
		Point p2 = new Point(x ,c.getYatX(x).getP1(),0d);
		System.out.println("circle point " + p2.getX() + "  " + p2.getY());
		
		Line l = c.getTangent(p2);
		System.out.println("\ncircle tangent " + l.getM() + "  " + l.getC());
		
		double inter = l.getXIntercept();
		System.out.println(p2.getX() + " " +p2.getY() + " " + inter);
		
		//System.out.println(l.getXIntercept());
		
		Point elPoint = new Point(x, e.getYatX(x).getP1(),0d);
		Line el = e.getTangent(elPoint);
		
		Assert.assertEquals(el.calculateY(x), e.getYatX(x).getP1(), 0.00001);;

	}
	
	
	@Test
	public void test3() {
		Point p = new Point(0d,0d,0d);
		double majorAxis = 1d;
		double minorAxis = 0.8d;
		Ellipse e = new Ellipse(p, majorAxis, minorAxis);

		Assert.assertEquals(majorAxis, e.radiusAtAngle(0d), 0.001);
		Assert.assertEquals(minorAxis, e.radiusAtAngle(Math.toRadians(90d)), 0.001);
		
		for (int i = 0; i <= 90; i=i+5){
			System.out.println(e.radiusAtAngle(Math.toRadians(i)));
		}
	}
	
}
