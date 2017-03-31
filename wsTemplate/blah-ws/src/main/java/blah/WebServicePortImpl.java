package blah;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.sql.DataSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;

import uk.co.trickster.services.template.message.GetRequestMessage;
import uk.co.trickster.services.template.message.GetResponseMessage;
import uk.co.trickster.webservice.TemplateServicePortType;

@MTOM
@WebService(portName = "TemplateServicePortType", 
serviceName = "TemplateService", 
targetNamespace = "http://uk.co.trickster/xmlmodel/templateService", 
wsdlLocation = "apps/Services/TemplateService/TemplateService.wsdl", 
endpointInterface = "uk.co.trickster.webservice.TemplateServicePortType")

@BindingType("http://www.w3.org/2003/05/soap/bindings/HTTP/")

public class WebServicePortImpl implements TemplateServicePortType {

	@Resource(mappedName = "java:/MySqlDS")
	private DataSource dataSource;

	private WebServicePortDelegate delegate;

	@PostConstruct
	public void postContruct() {
		delegate = new WebServicePortDelegate(dataSource);
	}

	protected synchronized WebServicePortDelegate getDelegate() {
		if (delegate == null) {
			delegate = new WebServicePortDelegate(dataSource);
		}
		return delegate;
	}

	@Override
	public GetResponseMessage getTemplate(GetRequestMessage payload) {
		return getDelegate().getTemplate(payload);
	}

}