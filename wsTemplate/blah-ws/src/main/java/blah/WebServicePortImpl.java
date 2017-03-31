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
@WebService(portName = "MyServiceWebServicePort", serviceName = "MyServiceWebService", targetNamespace = "http://trickster.co.uk/services/TemplateService", wsdlLocation = "apps/Services/MyService.wsdl", endpointInterface = "uk.co.trickster.webservice.TemplateServicePortType")
@BindingType("http://www.w3.org/2003/05/soap/binding/HTTP/")

public class WebServicePortImpl implements TemplateServicePortType {

	@Resource(mappedName = "jdbc/TricksterGlobalDataSource")
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