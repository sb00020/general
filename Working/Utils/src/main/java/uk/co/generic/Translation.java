package uk.co.generic;

import static java.lang.Math.*;

public strictfp class Translation {

	// c1 becomes the origin and c2 is translated to be in relation to it,
	public Cartesian translate(Cartesian c1, Cartesian c2) {

		// translate the c2 by c1, making c1 at 0,0,0
		Cartesian cT = translateVector(c1, c2);
		
		System.out.println(cT);
		System.out.println(Cartesian.getLength(cT));

		// get the length or the new vector
		double lc1 = Cartesian.getLength(c1);

		// creating a unit vector to point c1
		Cartesian uc1 = Cartesian.scalarDivide(c1, lc1);

		// Calculate the angle between the x and y components
		double xyAng = atan(uc1.getY() / uc1.getX());

		// Translate the vector around the Z access to have it projected against x
		Cartesian cr1 = translateAroundZ(uc1, -xyAng);

		// Calculate the angle between the the vector and the z axis
		double xzAng = atan(cr1.getX() / cr1.getZ());
		
		// Translate around the y axis to align with c1 at 0,0 and no translation
		Cartesian cr2 = translateAroundY(cr1, -xzAng);
		
		Cartesian cr3 = translateAroundZ(cT, -xyAng);
		Cartesian cr4 = translateAroundY(cr3, -xzAng);
		
		System.out.println(cr4);
		System.out.println(Cartesian.getLength(cr4));
		// reapply the length
		return Cartesian.scalarMulitply(cr2, Cartesian.getLength(cT));

	}

	private Cartesian translateVector(Cartesian c1, Cartesian c2) {

		return Cartesian.substract(c2, c1);

	}

	// NB theta is in radians
	public Cartesian translateAroundX(Cartesian c, double theta) {
		Vector mat1 = new Vector(1d, 0d, 0d);
		Vector mat2 = new Vector(0d, Math.cos(theta), -Math.sin(theta));
		Vector mat3 = new Vector(1d, Math.sin(theta), Math.cos(theta));

		Vector v = new Vector(c.getX(), c.getY(), c.getZ());

		double x = Vector.matrixMultiply(mat1, v);
		double y = Vector.matrixMultiply(mat2, v);
		double z = Vector.matrixMultiply(mat3, v);

		return new Cartesian(x, y, z);

	}

	// NB theta is in radians
	public Cartesian translateAroundY(Cartesian c, double theta) {
		Vector mat1 = new Vector(Math.cos(theta), 0d, Math.sin(theta));
		Vector mat2 = new Vector(0d, 1d, 0d);
		Vector mat3 = new Vector(-Math.sin(theta), 0d, Math.cos(theta));

		Vector v = new Vector(c.getX(), c.getY(), c.getZ());

		double x = Vector.matrixMultiply(mat1, v);
		double y = Vector.matrixMultiply(mat2, v);
		double z = Vector.matrixMultiply(mat3, v);

		return new Cartesian(x, y, z);
	}

	// NB theta is in radians
	public Cartesian translateAroundZ(Cartesian c, double theta) {
		Vector mat1 = new Vector(Math.cos(theta), -Math.sin(theta), 0d);
		Vector mat2 = new Vector(Math.sin(theta), Math.cos(theta), 0d);
		Vector mat3 = new Vector(0d, 0d, 1d);

		Vector v = new Vector(c.getX(), c.getY(), c.getZ());

		double x = Vector.matrixMultiply(mat1, v);
		double y = Vector.matrixMultiply(mat2, v);
		double z = Vector.matrixMultiply(mat3, v);

		return new Cartesian(x, y, z);

	}

}
