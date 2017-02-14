package uk.co.brett.jms;

import javax.jms.JMSException;

public class ImageJMSDriver {

	public static void main(String... args) throws InterruptedException, JMSException {

		System.out.println("Hello World");
		

		String ids = "";
		String message = "";
		
		JMSFeeder feeder = new JMSFeeder();
		ids = feeder.feeder();

		
		Thread.sleep(2000);
		
		JMSListener listen = new JMSListener();
		
		
			message = listen.listen(ids);
			
			System.out.println(message);

	
			System.out.println(ids);

		
	}
}
