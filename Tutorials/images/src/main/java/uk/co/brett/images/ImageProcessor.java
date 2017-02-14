package uk.co.brett.images;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ImageProcessor {

	public void read() {
		
		File f = new File("/Users/Sam/Desktop/P1000417.jpg");
		
		System.out.println(f.isFile());
		
		try {
			byte[] bytes = FileUtils.readFileToByteArray(f);
			
			System.out.println(bytes.length);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	
	
}
