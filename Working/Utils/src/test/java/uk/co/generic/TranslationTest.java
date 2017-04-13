package uk.co.generic;

import static org.junit.Assert.*;

import org.junit.Test;

public class TranslationTest {

	@Test
	public void test() {

		Translation t = new Translation();
		LatLon l1 = new LatLon(51.0d, 5.0d, 0.0d);
		LatLon l2 = new LatLon(51.0d, 5.0d, 1000.0d);
		
		Cartesian c1 = Cartesian.createCartesian(l1);
		Cartesian c2 = Cartesian.createCartesian(l2);
		
//		System.out.println(c1);
//		System.out.println(c2);

		t.translate(c1, c2);
		
	}

}
