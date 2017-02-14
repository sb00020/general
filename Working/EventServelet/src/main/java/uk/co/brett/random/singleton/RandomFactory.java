package uk.co.brett.random.singleton;

import java.util.ArrayList;
import java.util.Random;

public class RandomFactory {

	public static ArrayList<Random> GetRands(int num) {

		ArrayList<Random> rnd = new ArrayList<Random>();

		for (int i = 0; i < num; i++) {
			rnd.add(new Random(i));
		}

		return rnd;

	}

}
