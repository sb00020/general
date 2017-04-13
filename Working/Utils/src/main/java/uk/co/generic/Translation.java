package uk.co.generic;

import static java.lang.Math.*;

public strictfp class Translation {

	// c1 becomes the origin and c2 is translated to be in relation to it,
	public void translate(Cartesian c1, Cartesian c2){
		
		// translate the c2 by c1, making c1 at 0,0,0
		Cartesian cT = translateVector(c1, c2);
		
		// get the length or the new vector
		double lc1 = Cartesian.getLength(c1);
		
		// creating a unit vector to point c1
		Cartesian uc1 =  Cartesian.scalarDivide(c1, lc1);
		
		
		System.out.println(cT.toString());
		System.out.println(uc1.toString());
		System.out.println(Cartesian.getLength(cT));
		
		double length = Cartesian.getLength(uc1);
		
		//NB. Angle between x and y is 5*
		double xyAng = atan(uc1.getY()/uc1.getX());
		
		System.out.println("xyAng:   "+ toDegrees(xyAng));
//		
		System.out.println("length:  "+ length);
		//System.out.println("delt x:  " + (length - uc1.getX()));
		double newX = uc1.getX()*cos(toRadians(-xyAng)) - uc1.getY()*sin(toRadians(-xyAng));
		System.out.println("new x:   " + newX);
		System.out.println("delt x:  " + (newX - uc1.getX()));
		double xzAng = atan(newX/uc1.getZ());		
		System.out.println("xz ang:  " + toDegrees(xzAng));
		double newZ = (newX*sin(xzAng) + uc1.getZ()*cos(xzAng));
		
		System.out.println("delt z:  " + (newZ - uc1.getZ()));
		System.out.println("z proj:  "+(newX*sin(xzAng) + uc1.getZ()*cos(xzAng)));
	}
	
	private Cartesian translateVector(Cartesian c1, Cartesian c2){
		
		return Cartesian.substract(c2,c1);

	}
	
	
	
}
