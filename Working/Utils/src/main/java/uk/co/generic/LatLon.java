package uk.co.generic;

public class LatLon extends Triple {

	public LatLon(double inX, double inY, double inZ) {
		super(inX, inY, inZ);
	}

	// lat = x, long = y, alt = z;

	public static LatLon createLatLon(double inX, double inY, double inZ) {

		double lat = Math.asin(inZ / Constants.earthRadius);
		double lon = Math.atan2(inY, inX);
		double alt = Math.sqrt(inX * inX + inY * inY + inZ * inZ);

		return new LatLon(lat, lon, alt);
	}

	public static LatLon createLatLon(Cartesian c) {

		double inX = c.getX();
		double inY = c.getY();
		double inZ = c.getZ();

		double lat = Math.toDegrees(Math.asin(inZ / Constants.earthRadius));
		double lon = Math.toDegrees(Math.atan2(inY, inX));
		double alt = Math.sqrt(inX * inX + inY * inY + inZ * inZ) - Constants.earthRadius;

		return new LatLon(lat, lon, alt);
	}

}
