package uk.co.trickster.music.generator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProgressionVariablesTest {

	@Test
	public void test() {
		
		ProgressionVariables pv = new ProgressionVariables();
		
		System.out.println(pv);
		
		ProgressionVariables newTempo = ProgressionVariables.generateNewTempo(pv);
		System.out.println(newTempo);
		
		
		ProgressionVariables newScale = ProgressionVariables.generateNewScale(pv);
		System.out.println(newScale);
		
		ProgressionVariables newKey = ProgressionVariables.generateNewKey(pv);
		System.out.println(newKey);
		
		
		ProgressionVariables newSwing = ProgressionVariables.generateNewSwing(pv);
		System.out.println(newSwing);
		
	}

}
