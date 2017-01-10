package uk.co.jms;

import java.util.UUID;

import org.junit.Test;


public class JmsServiceTest {
@Test
	public void write() throws Exception {

		String connectionFactory = "myJmsTest/MyConnectionFactory";
		String requestQueue = "myJmsTest/MyQueue";
		
		String correlationId = UUID.randomUUID().toString();
		
		
		String request = "myRequest";
		//Assert.assertEquals(request, "myRequest");
		
		System.out.println(request);
		JmsServiceImpl jmsService = new JmsServiceImpl("server_name");
		
		correlationId = jmsService.sendTextMessage(connectionFactory, "requestQueue", "Contents");
		
		System.out.println(correlationId);
		
		String resp = jmsService.receiveTextMessage(connectionFactory, "responseQueue", correlationId);
		
	}

}
