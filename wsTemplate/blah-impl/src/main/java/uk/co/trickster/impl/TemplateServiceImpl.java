package uk.co.trickster.impl;

import uk.co.trickster.TemplateService;
import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;

public class TemplateServiceImpl implements TemplateService {


	@Override
	public GetResponseMessage Test(GetRequestMessage message) {
		GetResponseMessage resp = new GetResponseMessage();
		resp.setMyString("Response " + message.getMyString());
		return resp;
	}

}
 