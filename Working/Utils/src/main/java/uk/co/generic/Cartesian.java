package uk.co.generic;

public class Cartesian extends Triple {

	public Cartesian(double inX, double inY, double inZ) {
		super(inX, inY, inZ);
		// TODO Auto-generated constructor stub
	}

	public static Cartesian createCartesian(double lat, double lon, double alt) {

		double x = (Constants.earthRadius + alt) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lon));
		double y = (Constants.earthRadius + alt) * Math.cos(Math.toRadians(lat)) * Math.sin(Math.toRadians(lon));
		double z = (Constants.earthRadius + alt) * Math.sin(Math.toRadians(lat));

		return new Cartesian(x, y, z);
	}
	
	public static Cartesian createCartesian(LatLon ll) {
		double lat = ll.getX();
		double lon = ll.getY();
		double alt = ll.getZ();

		double x = (Constants.earthRadius + alt) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(lon));
		double y = (Constants.earthRadius + alt) * Math.cos(Math.toRadians(lat)) * Math.sin(Math.toRadians(lon));
		double z = (Constants.earthRadius + alt) * Math.sin(Math.toRadians(lat));

		return new Cartesian(x, y, z);
	}
	
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("--------------------\n");
		sb.append("x = " + this.getX());
		sb.append("\ny = " + this.getY());
		sb.append("\nz = " + this.getZ());
		sb.append("\n--------------------");
		return sb.toString();
		
	}
	
}
