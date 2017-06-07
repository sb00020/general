/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.trickster.music.enums;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Admin
 *
 */

public enum ScaleEnum {

	MAJOR("Major", Boolean.TRUE, 0, 2, 4, 5, 7, 9, 11, 12),
	MINOR("Minor", Boolean.TRUE, 0, 2, 3, 5, 7, 8, 10, 12),
	HARMONIC_MINOR("Harmonic Minor", Boolean.TRUE, 0, 2, 3, 5, 7, 8, 11, 12),
	MAJOR_PENTATONIC(Boolean.TRUE, 0, 2, 4, 7, 9, 12),
	MINOR_PENTATONIC(Boolean.TRUE, 0, 3, 5, 7, 10, 12),
	MELODIC_MINOR(Boolean.FALSE, 0, 2, 3, 5, 7, 9, 11, 12, 10, 8, 7, 5, 3, 2, 0),
	// Modes
	IONIAN("Ionian", Boolean.TRUE, 0, 2, 4, 5, 7, 9, 11, 12),
	DORIAN("Dorian", Boolean.TRUE, 0, 2, 3, 5, 7, 9, 10, 12),
	PHYGRIAN("Phrygrian", Boolean.TRUE, 0, 1, 3, 5, 7, 8, 10, 12),
	LYDIAN("Lydian", Boolean.TRUE, 0, 2, 4, 6, 7, 9, 11, 12),
	MYXOLYDIAN("Myxolydian", Boolean.TRUE, 0, 2, 4, 5, 7, 9, 10, 12),
	AEOLIEAN("Aeolian", Boolean.TRUE, 0, 2, 3, 5, 7, 8, 10, 12),
	LOCRIAN("Locian", Boolean.TRUE, 0, 1, 3, 5, 6, 8, 10, 12);

	private int[] intervals;
	private Boolean reversible;
	private String scaleName;

	private ScaleEnum(String name, Boolean temp, int... intervals) {

		// System.out.println(intervals.length);
		reversible = temp;
		this.intervals = intervals;
		scaleName = name;

	}

	private ScaleEnum(Boolean temp, int... intervals) {

		// System.out.println(intervals.length);
		reversible = temp;
		this.intervals = intervals;
	}

	public int[] getIntervals() {
		return intervals;
	}

	public Boolean getReversible() {
		return reversible;
	}

	public String toString() {
		return scaleName;
	}

	public ArrayList<Integer> getIntervalsArray() {

		ArrayList<Integer> arr = new ArrayList<Integer>();

		for (int j : intervals) {
			arr.add(j);
		}

		return arr;
	}

	public static ScaleEnum getRandom() {
		Random r = new Random();

		if (r.nextBoolean()) {
			return ScaleEnum.MAJOR;	
		} else {
			return ScaleEnum.MINOR;
		}
		
	}

}
