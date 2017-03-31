package blah;

import javax.sql.DataSource;

import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;
import uk.co.trickster.webservice.TemplateServicePortType;

public class WebServicePortDelegate implements TemplateServicePortType {
	
	private final javax.sql.DataSource dataSource;
	
	public WebServicePortDelegate(DataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public GetResponseMessage getTemplate(GetRequestMessage payload) {
		// TODO Auto-generated method stub
		return null;
	}


	
}