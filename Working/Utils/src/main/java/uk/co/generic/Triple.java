package uk.co.generic;


public class Triple {

	private double x,y,z;

	public Triple(){};
	
	public Triple (double inX, double inY, double inZ){
		x = inX;
		y = inY;
		z = inZ;
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
	
	public static double getLength(Cartesian vector){
		
		return Math.sqrt(vector.getX()*vector.getX() + vector.getY()*vector.getY() + vector.getZ()*vector.getZ());
	}
	
    public void unitMultiplierSet(Vector u, double d){
        
        x = u.getX() * d;
        y = u.getY() * d;
        z = u.getZ() * d;
    }
	
}
