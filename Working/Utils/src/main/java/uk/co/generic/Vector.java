package uk.co.generic;

/**
 *
 * @author Admin
 */
public class Vector extends Triple {

	public Vector() {

	}

	public Vector(double inX, double inY, double inZ) {
		super(inX, inY, inZ);

	}

	public void setVector(Triple set) {
		setX(set.getX());
		setY(set.getY());
		setZ(set.getZ());
	}

	public double getModulus() {

		double sum = Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2);
		double sqrt = Math.pow(sum, 0.5);
		return sqrt;
	}

	public Vector getUnitVector() {

		double mod = getModulus();

		Vector unit = new Vector();
		unit.setX(getX() / mod);
		unit.setY(getY() / mod);
		unit.setZ(getZ() / mod);

		return unit;
	}

	public static double dotProduct(Vector a, Vector b) {

		double dot = a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();

		return dot;

	}

	public static Vector linearMultiply(Vector u, Vector v) {

		Vector result = new Vector();

		result.setX(u.getX() * v.getX());
		result.setY(u.getY() * v.getY());
		result.setZ(u.getZ() * v.getZ());

		return result;
	}

	public static double matrixMultiply(Vector u, Vector v) {

		Vector c = Vector.linearMultiply(u, v);

		return c.getX() + c.getY() + c.getZ();
	}

	public static Vector crossProduct(Vector u, Vector v) {

		Vector result = new Vector();

		result.setX(u.getY() * v.getZ() - u.getZ() * v.getY());
		result.setY(u.getZ() * v.getX() - u.getX() * v.getZ());
		result.setZ(u.getX() * v.getY() - u.getY() * v.getX());

		return result;
	}

	/**
	 * 
	 * @param a
	 *            The vector to rotate
	 * @param normal
	 *            The axis to rotate it around
	 * @param theta
	 *            The angle in degrees
	 * @return The rotated vector
	 * 
	 *         The vector
	 * 
	 */
	public Vector projectVectorAtAngleDegrees(Vector a, Vector normal, double theta) {

		double thetaRad = Math.toRadians(theta);
		return projectVectorAtAngleRadians(a, normal, thetaRad);

	}

	public Vector projectVectorAtAngleRadians(Vector a, Vector normal, double theta) {

		Vector x = Vector.scalarMuliply(a, Math.cos(theta));

		System.out.println("Part 1: " + x.toString());

		Vector ySub = Vector.crossProduct(normal, a);

		Vector y = Vector.scalarMuliply(ySub, Math.sin(theta));

		System.out.println("Part 2: " + y.toString());

		double dot = Vector.dotProduct(normal, a);

		Vector zSub = Vector.scalarMuliply(normal, dot);

		Vector z = Vector.scalarMuliply(zSub, (1d - Math.cos(theta)));

		System.out.println("Part 3: " + z.toString());

		Vector resA = Vector.addVectors(x, y);

		System.out.println("Part 4: " + resA.toString());

		return Vector.addVectors(resA, z);

	}

	public static Vector addVectors(Vector a, Vector b) {

		Vector c = new Vector();

		c.setX(a.getX() + b.getX());
		c.setY(a.getY() + b.getY());
		c.setZ(a.getZ() + b.getZ());

		return c;

	}

	public static Vector subtractVectors(Vector a, Vector b) {

		Vector c = new Vector();

		c.setX(a.getX() - b.getX());
		c.setY(a.getY() - b.getY());
		c.setZ(a.getZ() - b.getZ());

		return c;

	}

	public static Vector scalarMuliply(Vector a, double s) {

		Vector c = new Vector();

		c.setX(a.getX() * s);
		c.setY(a.getY() * s);
		c.setZ(a.getZ() * s);

		return c;

	}

	@Override
	public String toString() {

		String a;
		a = "================";

		a += "\nx = " + getX();
		a += "\ny = " + getY();
		a += "\nz = " + getZ();

		a += "\n================";

		return a;
	}

}
