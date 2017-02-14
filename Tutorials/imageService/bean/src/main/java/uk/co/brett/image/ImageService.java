package uk.co.brett.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

public class ImageService {

	public String getImageAsB64String() {

		InputStream inputStream = ImageService.class.getResourceAsStream("/uk/co/brett/image/img.png");

		try {

			byte[] bytes = IOUtils.toByteArray(inputStream);

			return Base64.getEncoder().encodeToString(bytes);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Bass!";
	}

}
