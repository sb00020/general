package uk.co.trickster.music.chord.availability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uk.co.trickster.music.enums.ChordEnum;
import uk.co.trickster.music.enums.IntervalEnum;
import uk.co.trickster.music.enums.ScaleEnum;

public class ChordAvailability {

	public void getAvailability(final ScaleEnum scale, IntervalEnum intervalEnum) {
		ArrayList<Integer> arr = scale.getIntervalsArray();
		int interval = intervalEnum.getInterval();

		if (interval != 0) {
			arr = correctArrayList(arr, interval);
		}

		System.out.print(IntervalEnum.lookup(interval).getTitle() + "  ");

		if (interval <= arr.get(arr.size() - 1)) {

			for (ChordEnum c : ChordEnum.values()) {
				if (containsChord(arr, interval, c)) {
					System.out.printf("%s  ", c.name());
				}
				
			}
			System.out.println();
		} else {
			String message = String.format("Interval %d is unavailable for the %s Scale. The maximum is %d.", interval,
					scale.toString(), scale.getIntervals().length);
			System.out.println(message);

		}
	}

	private ArrayList<Integer> correctArrayList(ArrayList<Integer> arr, int interval) {

		// in the special case where it's unison or the octave, return unaltered
		if (interval == 12 || interval == 0) {
			return arr;
		}
		
		// remove octave
		List<Integer> removedOctave = arr.subList(0, arr.size() - 1);

		int rotation = removedOctave.indexOf(interval);

		// rotate
		Collections.rotate(removedOctave, -(rotation));

		int fixed = removedOctave.get(0);
		ArrayList<Integer> corrected = new ArrayList<Integer>();
		for (Integer i : removedOctave) {
			if (i - fixed >= 0) {
				corrected.add(i - fixed);
			} else {
				corrected.add(12 + i - fixed);
			}
		}
		
		// add octave back
		corrected.add(12);
System.out.println(corrected);
		return corrected;
	}

	private boolean containsChord(ArrayList<Integer> arr, int interval, ChordEnum chord) {

		int[] chordInt = limitChord(chord.getIntervals());

		boolean result = false;
		for (int i : chordInt) {
			// System.out.println(i + " " + arr.contains(new Integer(i)));

			if (arr.contains(new Integer(i))) {
				result = true;
			} else {
				return false;
			}

		}

		return result;
	}

	private int[] limitChord(int[] intervals) {

		Arrays.sort(intervals);

		if (intervals[intervals.length - 1] > 12) {

			int[] fixed = new int[intervals.length];

			for (int i = 0; i < intervals.length; i++) {

				if (intervals[i] < 12) {
					fixed[i] = intervals[i];
				} else {
					fixed[i] = intervals[i] - 12;
				}
			}
			return fixed;

		} else {
			return intervals;
		}

	}

}
