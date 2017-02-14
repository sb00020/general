package uk.co.brett.random.singleton;

import java.util.Random;

public class RandomSingleton {
	private static volatile RandomSingleton instance;
	private Random rnd;
	private static int seed;

	private RandomSingleton() {
		rnd = new Random(seed);
	}
	
	private static final ThreadLocal<RandomSingleton> _localStorage = new ThreadLocal<RandomSingleton>(){
	    protected RandomSingleton initialValue() {
	      return new RandomSingleton();
	   }
	  };
	
	

	public static RandomSingleton getInstance() {
		return _localStorage.get();
	}

	public static RandomSingleton getInstance(int inSeed) {

		if (instance == null) {
			seed = inSeed;
			instance = new RandomSingleton();
			System.out.println("Seed set to: " + seed);
		} else {
			System.out.println("Seed was already to: " + seed);
		}
		return instance;
	}

	public double nextDouble() {
		return rnd.nextDouble();
	}

	public int nextInt() {
		return rnd.nextInt();
	}

}