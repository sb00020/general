package uk.co.brett.jms;

import javax.jms.JMSException;

public class JMSDriver {

	public static void main(String... args) throws InterruptedException, JMSException {

		System.out.println("Hello World");
		
		//JMSFeeder feeder = new JMSFeeder();
		
		String[] ids = new String[10];
		String[] message = new String[10];
		
		for (int i=0; i <10; i ++) { 
			JMSFeeder feeder = new JMSFeeder();
			ids[i] = feeder.feeder();
			feeder = null;
			System.out.println(ids[i]);
			break;
		}
		
		Thread.sleep(4000);
		
		JMSListener listen = new JMSListener();
		
		for (int i=0; i <10; i ++) { 
			
			message[i] = listen.listen(ids[i]);
			
			System.out.println(message[i]);
			break;
		}
		
		for (int i=0; i <10; i ++) { 
			System.out.println(ids[i]);
			break;
		}
		
	}
}
