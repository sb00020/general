package uk.co.brett.image;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImageServiceTests {

	@Test
	public void test() {
		
		ImageService im = new ImageService();
		String s = im.getImageAsB64String();
		
		
	}

}
