package uk.co.trickster.music.enums;

import org.junit.Test;

public class ToneEnumTest {

	@Test
	public void test() {
		//ToneEnum t = ToneEnum.C;
		
		for (ToneEnum t : ToneEnum.values()){
		
		ToneEnum.searchForAliases(t);
		
		}
	}

}
