package uk.co.brett.maths.geometry;

public class Point {

	private double x, y, z;

	public Point(double a, double b, double c) {
		x = a;
		y = b;
		z = c;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
