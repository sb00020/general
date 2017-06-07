package uk.co.trickster.music.chord.availability;

import org.junit.Test;

import uk.co.trickster.music.enums.IntervalEnum;
import uk.co.trickster.music.enums.ScaleEnum;

public class ChordAvailabilityTest {

	@Test
	public void test() {
		
		
		ChordAvailability c = new ChordAvailability();
		
		ScaleEnum s = ScaleEnum.MAJOR;
		
		for(int i: s.getIntervals()){
			IntervalEnum interval = IntervalEnum.lookup(i);
			
			c.getAvailability(s, interval);
			System.out.println();
			
		}
		
		

	}

}
