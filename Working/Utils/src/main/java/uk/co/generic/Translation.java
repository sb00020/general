package uk.co.generic;

public class Translation {

	// c1 becomes the origin and c2 is translated to be in relation to it,
	public void translate(Cartesian c1, Cartesian c2){
		
		// translate the c2 by c1, making c1 at 0,0,0
		Cartesian cT = new Cartesian(c2.getX() - c1.getX(),c2.getY() - c1.getY(),c2.getZ() - c1.getZ() );
		
		// get the length or the new vector
		double lc1 = Math.sqrt(c1.getX()*c1.getX() + c1.getY()*c1.getY() + c1.getZ()*c1.getZ());
		
		// creating a unit vector
		Cartesian uc1 = new Cartesian(c1.getX()/lc1, c1.getY()/lc1 , c1.getZ()/lc1);
		
		
		System.out.println(cT.toString());
		System.out.println(uc1.toString());
		
		double length = Math.sqrt(uc1.getY()*uc1.getY() + uc1.getX()*uc1.getX());
		
		//NB. Angle between x and y is 5*
		double xyAng = Math.atan(uc1.getY()/uc1.getX());
		
		System.out.println("   "+Math.toDegrees(xyAng));
		
		System.out.println(length);
		System.out.println(length - uc1.getX() );
		double newX = uc1.getX()*Math.cos(Math.toRadians(-5)) - uc1.getY()*Math.sin(Math.toRadians(-5));
		System.out.println(uc1.getX()*Math.cos(Math.toRadians(-5)) - uc1.getY()*Math.sin(Math.toRadians(-5)));
		
		System.out.println(Math.toDegrees(Math.atan(newX/uc1.getZ())));
		double xzAng = Math.atan(newX/uc1.getZ());
		System.out.println(newX*Math.sin(xzAng) + uc1.getZ()*Math.cos(xzAng));
	}
	
	
}
