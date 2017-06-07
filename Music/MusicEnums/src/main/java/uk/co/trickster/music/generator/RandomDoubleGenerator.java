package uk.co.trickster.music.generator;

import java.util.Random;

public class RandomDoubleGenerator {

	public static int generateRandom(double low, double high){
		
		double range = high - low;
		
		Random r = new Random();
		double dValue =low + r.nextDouble() * range ;
		return (int) dValue;
	}
	
	
}
