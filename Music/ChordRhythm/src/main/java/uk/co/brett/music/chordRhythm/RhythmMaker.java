package uk.co.brett.music.chordRhythm;

import java.util.ArrayList;
import java.util.Random;

public class RhythmMaker {

	private final int bars, beatsPerBar, beats, divisorBeatsPerBar;
	private final double divisor, divisorBeats;
	private boolean favourEvenNumber = true;

	public RhythmMaker(final int bars, final int beatsPerBar, final double divisor) {

		this.bars = bars;
		this.beatsPerBar = beatsPerBar;
		this.beats = bars * beatsPerBar;
		this.divisor = divisor;
		this.divisorBeats = ((double) beats) / divisor;
		this.divisorBeatsPerBar = (int) (((double) beatsPerBar) / divisor);

	}

	public ArrayList<Timing> makeRhythm() {

		ArrayList<Timing> timings = new ArrayList<>();

		Random rnd = new Random();
		double sum = 0;
		double hold = 0;

		while (sum < divisorBeats) {

			if (favourEvenNumber) {
				hold = favourEvenRandom(rnd);
			} else {
				hold = rnd.nextInt(divisorBeatsPerBar) + 1;
			}

			if ((hold + sum) > divisorBeats) {

				hold = divisorBeats - sum;

			}
			sum += hold;

			Timing timing = new Timing(hold, sum);
			timings.add(timing);

			System.out.println(hold + "  " + sum + "  " + divisorBeats);

		}

		return timings;

	}

	private double favourEvenRandom(Random rnd) {

		double d = rnd.nextDouble();
		int hold;
		
		//System.out.println("Favour Evens: " + d);
		
		if (d < 0.8) {
			hold = 1;
			
			//System.out.println(hold + " " + ((hold % 2)));
			
			while ((hold % 2) != 0) {

			//	System.out.println(hold + " " + ((hold % 2) == 0));
				
				hold = rnd.nextInt(divisorBeatsPerBar)+1;
				//System.out.println(hold);
			}

		} else {
			hold = rnd.nextInt(divisorBeatsPerBar)+1;

		}

		return hold;
	}

}
