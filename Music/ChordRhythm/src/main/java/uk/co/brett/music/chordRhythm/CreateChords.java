package uk.co.brett.music.chordRhythm;

import java.util.ArrayList;
import java.util.Random;

public class CreateChords {

	ArrayList<Timing> timings;
	
	public CreateChords(ArrayList<Timing> rhythm) {
		
		this.timings = rhythm;
		
	}
	
	public ArrayList<Timing> chords(){
		
		Random rnd = new Random();
		
		for (Timing t : timings){
			
			int chord = rnd.nextInt(7) + 1;
			
			t.setChord(chord);
			
			System.out.println(chord);
			
			
		}
		
		return timings;
		
	}
	

}
