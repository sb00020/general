package uk.co.brett.maths.geometry;
import static java.lang.Math.*;
import org.apache.commons.math3.analysis.function.*;
public class OblateSpheroid {
	Point centre;
	double minorRadius, majorRadius, foci, u;
	
	/*
	 * NB x and y are symmetrical and z is flattened
	 * 
	 * (x^2 + y^2)/a^2 + z^2 / c^2 = 1
	 */
	
	public OblateSpheroid (Point cent, double majRad, double minRad){
		centre = cent;
		minorRadius = minRad;
		majorRadius = majRad;
		foci = calculateFoci();
		u = calculateU();
	}
	
	public double calculateFoci(){
		return sqrt(majorRadius*majorRadius - minorRadius*minorRadius);
	}
	
	public double calculateU(){
		Acosh a = new Acosh();
		a.value(majorRadius / foci);
		return a.value(majorRadius / foci);
	}
	
	public Point getPointAtAngles(double a, double b){
		
		double x,y,z;
		Cosh c = new Cosh();
		Sinh s = new Sinh();
		
		x = foci * c.value(u) * cos(a) * cos(b);
		y = foci * c.value(u) * cos(a) * sin(b);
		z = foci * s.value(u) * sin(a) ;

		return new Point(x,y,z);
	}
	
}
