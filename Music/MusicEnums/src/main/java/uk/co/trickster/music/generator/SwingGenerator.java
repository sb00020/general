package uk.co.trickster.music.generator;

public class SwingGenerator extends RandomDoubleGenerator {

	private final static double LOW_DEFAULT = 0, HIGH_DEFAULT = 80;

	public static int generate() {
		return generateRandom(LOW_DEFAULT, HIGH_DEFAULT);
	}

	public static int generate(double value, boolean isLow) {
		if (isLow) {
			return generateRandom(value, HIGH_DEFAULT);
		} else {
			return generateRandom(LOW_DEFAULT, value);
		}
	}

	public static int generate(double low, double high) {
		return generateRandom(low, high);
	}

}
