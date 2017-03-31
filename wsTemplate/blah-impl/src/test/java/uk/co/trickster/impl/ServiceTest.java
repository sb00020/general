package uk.co.trickster.impl;

import org.junit.Assert;
import org.junit.Test;


import uk.co.trickster.TemplateService;
import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;

public class ServiceTest {

	@Test
	public void test() {
		TemplateService serv = new TemplateServiceImpl();
		
		
		GetRequestMessage message = new GetRequestMessage();
		message.setMyString("InputString");
		 GetResponseMessage output = serv.Test(message);
		
		 Assert.assertEquals("Response "+message.getMyString(), output.getMyString());
		 
	}

}
