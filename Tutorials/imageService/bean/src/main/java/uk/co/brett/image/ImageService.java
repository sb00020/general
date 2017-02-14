package uk.co.brett.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

import javafx.scene.image.Image;

public class ImageService {

	public String getImageAsB64String() {
		
		
		
		//InputStream inputStream = ImageService.class.getResourceAsStream("/email/file.txt");
		InputStream inputStream = ImageService.class.getResourceAsStream("/uk/co/brett/image/img.png");
		Image image = new Image(inputStream);
		
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		try {
			System.out.println(reader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		InputStream is = ImageService.class.getResourceAsStream("uk\\co\\brett\\image\\img.png");
//
//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("img.png").getFile());
//
//		System.out.println(file.exists());
//
//		byte[] buffer = null;
		// try {
		// IOUtils.readFully(is, buffer );
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// System.out.println(buffer.length);
		//
		return "Bass!";
	}

}
