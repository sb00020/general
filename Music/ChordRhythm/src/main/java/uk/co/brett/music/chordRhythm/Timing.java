package uk.co.brett.music.chordRhythm;

public class Timing {
	
	private final double beats, sum;
	private int chord;
	
	public Timing (final double beats, final double sum){
		this.beats = beats;
		this.sum = sum;
	}
	
	public void setChord(int chord) {
		this.chord = chord;
	}

	public double getBeats() {
		return beats;
	}

	public double getSum() {
		return sum;
	}

	public int getChord() {
		return chord;
	}
	

}
