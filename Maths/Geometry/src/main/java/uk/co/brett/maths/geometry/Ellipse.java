package uk.co.brett.maths.geometry;

import static java.lang.Math.*;

public class Ellipse extends Shape {
	
	Point centre;
	double minorRadius, majorRadius;
	
	public Ellipse (Point cent, double majRad, double minRad){
		centre = cent;
		minorRadius = minRad;
		majorRadius = majRad;
	}

	@Override
	public double getPerimeter() {
		// Using Ramanujan approximation
		
		double h = pow((majorRadius - minorRadius),2d) / pow((majorRadius + minorRadius),2d);
		double coef = 1 + 3*h/(10 + Math.sqrt(4-3*h));
		return coef * PI*(minorRadius + majorRadius);
	}

	@Override
	public double getArea() {
		return PI * minorRadius * majorRadius;
	}
	
	public Pair getYatX(double x){
		double y = sqrt(minorRadius*minorRadius*(1d- (pow(x,2d) /majorRadius*majorRadius)));
		Pair p = new Pair();
		p.setP1(y);
		p.setP2(-y);
		
		return p;
	}
	
	public Line getTangent(Point p){
		
		/* 
		 * 1. create circle with same major radius. 
		 * 2. calculate tangent at same x. 
		 * 3. calculate intecept with x-axis.
		 * 4. define line from 3 with point on elipse.
		 * Theory from http://www.nabla.hr/Z_MemoHU-029.htm
		 */
		
		Circle c = new Circle(centre, majorRadius);
		Pair pair = c.getYatX(p.getX());
		
		Point circlePoint = new Point(p.getX(), pair.getP1(), 0d);
		
		Line l = c.getTangent(circlePoint);
		Point intercept = new Point(l.getXIntercept(), 0d, 0d);
		
		Line.calculateLine(intercept, p);
		
		return Line.calculateLine(intercept, p);
	}

	public double radiusAtDegrees(double thetaDegrees){
		return radiusAtAngle(toRadians(thetaDegrees));
	}
	
	public double radiusAtAngle(double theta){
		
		double a = minorRadius * majorRadius;
		double b = pow(minorRadius * cos(theta),2d) + pow(majorRadius * sin(theta),2d);
		return a / sqrt(b);
	}
	
}
