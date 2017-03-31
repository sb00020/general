package blah;

import javax.sql.DataSource;

import uk.co.trickster.TemplateService;
import uk.co.trickster.impl.TemplateServiceImpl;
import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;
import uk.co.trickster.webservice.TemplateServicePortType;

public class WebServicePortDelegate implements TemplateServicePortType {
	
	private final javax.sql.DataSource dataSource;
	
	TemplateService service;
	
	public WebServicePortDelegate(DataSource inDataSource){
		this.dataSource = inDataSource;
		service = new TemplateServiceImpl();
	}

	@Override
	public GetResponseMessage getTemplate(GetRequestMessage payload) {
		return service.Test(payload);
	}


	
}