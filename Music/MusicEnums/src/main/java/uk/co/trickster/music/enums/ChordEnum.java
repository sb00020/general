/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.trickster.music.enums;

/**
 *
 * @author Admin
 *
 */
public enum ChordEnum {

	MAJOR("Major", 0, 4, 7),
	MINOR("Minor", 0, 3, 7),
	SUS4("sus4", 0, 5, 7),
	SUS2("sus2", 0, 2, 7),
	DIM("dim", 0, 3, 6),
	MAJ7("maj7", 0, 4, 7, 11),
	MIN7("min7",0, 3, 7, 10),
	DOM7("dom7",0, 4, 7, 10),
	MIN6("min6",0, 3, 7, 9),
	MINMIN6("minMin6",0, 3, 7, 8),
	MAJMIN7("majMin7",0, 3, 7, 11),
	ADD9("add9",0, 4, 7, 14),
	DIM7("dim7",0,3,6,10);
	
	private String chordName;
	private int[] intervals;

	private ChordEnum(String name, int... intervals) {

		// System.out.println(intervals.length);
		chordName = name;
		this.intervals = intervals;
	}

	private ChordEnum(int... intervals) {

		// System.out.println(intervals.length);
		chordName = "Chord has no name";
		this.intervals = intervals;
	}

	public int[] getIntervals() {
		return intervals;
	}

	public String getChordName(){
		return chordName;
	}
}
