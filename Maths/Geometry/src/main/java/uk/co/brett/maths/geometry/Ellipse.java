package uk.co.brett.maths.geometry;

public class Ellipse extends Shape {
	
	Point centre;
	double minorRadius, majorRadius;
	
	public Ellipse (Point cent, double minRad, double majRad){
		centre = cent;
		minorRadius = minRad;
		majorRadius = majRad;
	}

	@Override
	public double getPerimeter() {
		// Using Ramanujan approximation
		
		double h = Math.pow((majorRadius - minorRadius),2d) / Math.pow((majorRadius + minorRadius),2d);
		double coef = 1 + 3*h/(10 + Math.sqrt(4-3*h));
		return coef * Math.PI*(minorRadius + majorRadius);
	}

	@Override
	public double getArea() {
		
		return Math.PI * minorRadius * majorRadius;
	}

}
