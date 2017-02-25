package uk.brett.jms;

import org.junit.Test;

import uk.co.brett.jms.ConcreteImpl;
import uk.co.brett.jms.data.Data;

public class jmsTest {

	@Test
	public void test() throws Exception {

		ConcreteImpl c = new ConcreteImpl(new Data("/pattern/queue", "/pattern/queue"));

		String id = c.addMessage("send message");

		System.out.println(id);
		
		Thread.sleep(1000);
		
		System.out.println(c.receiveMessage());

	}

}
