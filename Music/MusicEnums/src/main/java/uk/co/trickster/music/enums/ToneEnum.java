package uk.co.trickster.music.enums;

import java.util.ArrayList;
import java.util.Random;

public enum ToneEnum {
	// Naturals
	C("C", 0, true, true),
	D("D", 2, true, true),
	E("E", 4, true, true),
	F("F", 5, false, true),
	G("G", 7, true, true),
	A("A", 9, true, true),
	B("B", 11, false, true),
	// Flats
	Db("Db", 1, false, false),
	Eb("Eb", 3, false, false),
	Gb("Gb", 6, false, false),
	Ab("Ab", 8, false, false),
	Bb("Bb", 10, false, false),
	// Sharps
	Cs("C#", 1, true, true),
	Ds("D#", 3, true, true),
	Fs("F#", 6, true, true),
	Gs("G#", 8, true, true),
	As("A#", 10, true, true),
	// Unusual
	Bs("B#", 0, true, false),
	Fb("Fb", 4, false, false),
	Es("E#", 5, true, false),
	Cb("Cb", 11, false, false);

	private String name;
	private boolean usesSharps, defaultNote;
	private int midiNote;

	private ToneEnum(String title, int midiNote, boolean usesSharps, boolean defaultNote) {
		this.name = title;
		this.midiNote = midiNote;
		this.usesSharps = usesSharps;
		this.defaultNote = defaultNote;
	}

	public String getName() {
		return name;
	}

	public boolean usesSharps() {
		return usesSharps;
	}

	public int getMidiNote() {
		return midiNote;
	}

	public String toString() {
		return name;
	}

	public boolean isDefault() {
		return defaultNote;
	}

	public static ArrayList<ToneEnum> searchForAliases(ToneEnum t) {

		int searchNote = t.getMidiNote();
		// System.out.println(t + " " + searchNote);

		ArrayList<ToneEnum> a = new ArrayList<>();
		for (ToneEnum g : ToneEnum.values()) {

			if (g.getMidiNote() == searchNote && g.getName() != t.getName()) {
				a.add(g);
			}

		}

//		if (a.isEmpty()) {
//			System.out.println(t.getName() + " has no aliases");
//		} else {
//			System.out.print("aliases for " + t.getName() + ": ");
//			for (ToneEnum g : a) {
//				System.out.print(g + "  ");
//			}
//		}
//		
		return a;
		
	}

	public static ToneEnum getRandom() {

		Random r = new Random();
		int max = ToneEnum.values().length;

		ToneEnum t = ToneEnum.values()[r.nextInt(max)];
		
		if (t.isDefault()) {
			return t;
		} else {
			ArrayList<ToneEnum> var = ToneEnum.searchForAliases(t);
			if(!var.isEmpty()){
				return var.get(0);
			}
		}

		return t;
	}

}
