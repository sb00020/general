package uk.co.trickster.music.generator;

import uk.co.trickster.music.enums.ScaleEnum;
import uk.co.trickster.music.enums.ToneEnum;

public class ProgressionVariables {

	private static int tempo, swing;
	private static ToneEnum key;
	private static ScaleEnum scale;

	public ProgressionVariables() {
		tempo = TempoGenerator.generateTempo();
		swing = SwingGenerator.generate();
		key = ToneEnum.getRandom();
		scale = ScaleEnum.getRandom();
	}

	public static void createObject() {

		tempo = TempoGenerator.generateTempo();
		swing = SwingGenerator.generate();
		key = ToneEnum.getRandom();
		scale = ScaleEnum.getRandom();

	}

	public static ProgressionVariables generateNewSwing(ProgressionVariables pv) {
		swing = SwingGenerator.generate();
		return pv;
	}
	
	public static ProgressionVariables generateNewTempo(ProgressionVariables pv) {
		tempo = TempoGenerator.generateTempo();
		return pv;
	}

	public static ProgressionVariables generateNewKey(ProgressionVariables pv) {
		key = ToneEnum.getRandom();
		return pv;
	}
	
	public static ProgressionVariables generateNewScale(ProgressionVariables pv) {
		scale = ScaleEnum.getRandom();
		return pv;
	}
	
	public String getKey(){
		return key.getName();
	}
	
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("=================\n");
		sb.append("Tempo = " + tempo + "\n");
		sb.append("Swing = " + swing + "\n");
		sb.append("Key   = " + key + "\n");
		sb.append("Scale = " + scale + "\n");
		sb.append("=================\n");
		return sb.toString();

	}

}
