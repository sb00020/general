/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.trickster.music.enums;

/**
 * An enumeration of musical intervals, up to 1 octave.
 *
 * @author Admin
 */
public enum IntervalEnum {

	UNISON("Unison", 0, 0),
	DIM2("Diminished 2nd", 0, 1),
	MAJ2("Major 2nd", 0, 2),
	MIN3("Minor 3rd", 0, 3),
	MAJ3("Major 3rd", 0, 4),
	SUBDOMINANT("Subdominant (4th)", 0, 5),
	DIM5("Diminished 5th", 0, 6),
	DOMINANT("Dominant (5th)", 0, 7),
	MIN6("Minor Sixth", 0, 8),
	MAJ6("Major 6th", 0, 9),
	DOM7("Dominant 7th", 0, 10),
	MAJ7("Major 7th", 0, 11),
	OCTAVE("Octave", 0, 12);

	private int[] intervals;
	private String title;

	/**
	 * Constructs the enumeration based on the string and intervals
	 *
	 * @param title
	 *            A user friendly title
	 * @param intervals
	 *            the array of intervals
	 */
	private IntervalEnum(String title, int... intervals) {
		this.intervals = intervals;
		this.title = title;
	}
	
	
	/**
	 * Return the array representing the interval
	 *
	 * @return pair of integers
	 */
	public int[] getIntervals() {
		return intervals;
	}

	/**
	 * Returns just the interval in semi-tones
	 *
	 * @return
	 */
	public int getInterval() {
		int interval = intervals[1];
		return interval;
	}



	/**
	 * Returns the title of an interval.
	 *
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	public static IntervalEnum lookup(int i) {

		for (IntervalEnum ie : IntervalEnum.values()) {
			if (ie.getInterval() == i) {
				return ie;
			}
		}

		return null;
	}

}
