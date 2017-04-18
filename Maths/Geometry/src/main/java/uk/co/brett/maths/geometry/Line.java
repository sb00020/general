package uk.co.brett.maths.geometry;

public class Line extends Pair {

	public Line(double m, double c) {
		super(m, c);
	}

	public double getM() {
		return getP1();
	}

	public void setM(double m) {
		setP1(m);
	}

	public double getC() {
		return getP2();
	}

	public void setC(double c) {
		setP2(c);
	}
	
	public double getXIntercept(){
		return -getC() / getM();
	}
	
	public double calculateX(double y){
		return (y - getC())/getM();
	}
	
	public double calculateY(double x){
		return getM() * x + getC();
	}
	
	public static Line calculateLine(Point p1, Point p2){
		
		double m = (p2.getY() - p1.getY())/ (p2.getX() - p1.getX());
		double c = p2.getY() - m*p2.getX();

		return new Line(m,c);
	}
	
}
