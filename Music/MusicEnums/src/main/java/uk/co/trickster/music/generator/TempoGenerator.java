package uk.co.trickster.music.generator;

public class TempoGenerator extends RandomDoubleGenerator  {

	private final static double LOW_DEFAULT = 60, HIGH_DEFAULT = 160;

	public static int generateTempo() {
		return generateRandom(LOW_DEFAULT, HIGH_DEFAULT);
	}

	public static int generateTempo(double value, boolean isLow) {

		if (isLow) {
			return generateRandom(value, HIGH_DEFAULT);
		} else {
			return generateRandom(LOW_DEFAULT, value);
		}
	}

	public static int generateTempo(double low, double high) {
		return generateRandom(low, high);
	}




}
