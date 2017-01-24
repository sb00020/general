package uk.co.brett.music.chordRhythm;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		RhythmMaker rm = new RhythmMaker(4,4, 1);
		
		
		ArrayList<Timing> rhythm = rm.makeRhythm();
		
		CreateChords chords = new CreateChords(rhythm);
		rhythm = chords.chords();
		
		
		for (Timing t : rhythm) { 
			
			System.out.println(t.getBeats() +"  "+ t.getSum() +"  "+ t.getChord());
			
		}
		

	}

}
