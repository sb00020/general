package uk.co.brett.maths.geometry;

public class Circle extends Shape {
	
	Point centre;
	double radius;
	
	public Circle (Point cent, double rad){
		centre = cent;
		radius = rad;
	}

	public Pair getYatX(double x){
		
		Pair p = new Pair();
		p.setP1(Math.sqrt((1-x)*(1+x)));
		p.setP2(-Math.sqrt((1-x)*(1+x)));
		
		System.out.println(p.getP1() + "  " + p.getP2());
		
		return p;
		
	}
	
	@Override
	public double getPerimeter() {
		return 2d * radius * Math.PI;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}
	
	public Line getTangent(Point p){
		// gradient to point
		double mp = p.getY() / p.getX();
		double tanM = -1d/mp;
		double tanC = p.getY() - tanM*p.getX();

		return new Line(tanM, tanC);

	}
	

}
