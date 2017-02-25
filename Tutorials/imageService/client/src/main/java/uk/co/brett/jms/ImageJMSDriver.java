package uk.co.brett.jms;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.jms.JMSException;

import org.apache.commons.io.FileUtils;

public class ImageJMSDriver {

	public static void main(String... args) throws InterruptedException, JMSException {

		System.out.println("Hello World");

		String ids = "";
		String message = "";

		JMSFeeder feeder = new JMSFeeder();
		ids = feeder.feeder();
		
		JMSListener listen = new JMSListener();

		message = listen.listen(ids);
		
		convertToImage(message);

	}

	private static void convertToImage(String message) {
		
		byte[] bytes = Base64.getDecoder().decode(message.getBytes());
		
		String curDir = System.getProperty("user.dir");
		
		System.out.println(curDir);
		
		try {
			FileUtils.writeByteArrayToFile(new File(curDir+ "/out.png"), bytes);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
}