package uk.co.brett.maths.plotter;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.ejb.EJBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import com.brett.services.maths.ObjectFactory;
import com.brett.services.maths.PlotterDriverRequestType;
import com.brett.services.maths.PlotterDriverResponseType;
import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;



public class PlotterMediator {

	private static final XMLInputFactory xmlFactory = XMLInputFactory.newFactory();
	private static final ObjectFactory objectFactory = new ObjectFactory();

	private static final JAXBContext jaxbContext;

	static {
		try {
			System.out.println("Init context");
			jaxbContext = JAXBContext.newInstance(PlotterRequestType.class, PlotterResponseType.class, PlotterDriverRequestType.class, PlotterDriverResponseType.class);
		} catch (JAXBException e) {
			throw new EJBException("Failed to create Context");
		}
	}

	public static PlotterRequestType requestFromXml(String xml) {

		Unmarshaller unmarshaller;
		JAXBElement<PlotterRequestType> req = null;

		try {
			unmarshaller = jaxbContext.createUnmarshaller();
			req = unmarshaller.unmarshal(xmlFactory.createXMLStreamReader(new StringReader(xml)), PlotterRequestType.class);
		} catch (JAXBException | XMLStreamException e) {
			throw new EJBException("Failed to unmarshall message '" + xml + "'", e);
		}

		return req.getValue();
	}

	public static String requestToXml(PlotterRequestType req) {
		System.out.println("In method");
		Writer sw = new StringWriter();
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectFactory.createPlotterRequest(req), sw);

		} catch (JAXBException e) {
			throw new EJBException("Failed to Marshall request", e);
		}

		return sw.toString();
	}
	
	public static PlotterResponseType responseFromXml(String xml) {

		Unmarshaller unmarshaller;
		JAXBElement<PlotterResponseType> res = null;

		try {
			unmarshaller = jaxbContext.createUnmarshaller();
			res= unmarshaller.unmarshal(xmlFactory.createXMLStreamReader(new StringReader(xml)), PlotterResponseType.class);
		} catch (JAXBException | XMLStreamException e) {
			throw new EJBException("Failed to unmarshall message '" + xml + "'", e);
		}

		return res.getValue();
	}

	public static String responseToXml(PlotterResponseType res) {

		Writer sw = new StringWriter();
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectFactory.createPlotterResponse(res), sw);

		} catch (JAXBException e) {
			throw new EJBException("Failed to Marshall request", e);
		}

		return sw.toString();
	}
	
	
	public static PlotterDriverResponseType driverResponseFromXml(String xml) {

		Unmarshaller unmarshaller;
		JAXBElement<PlotterDriverResponseType> res = null;

		try {
			unmarshaller = jaxbContext.createUnmarshaller();
			res= unmarshaller.unmarshal(xmlFactory.createXMLStreamReader(new StringReader(xml)), PlotterDriverResponseType.class);
		} catch (JAXBException | XMLStreamException e) {
			throw new EJBException("Failed to unmarshall message '" + xml + "'", e);
		}

		return res.getValue();
	}
	
	
	public static String reponseToXml(PlotterDriverResponseType res){
		
		Writer sw = new StringWriter();
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectFactory.createPlotterDriverResponse(res), sw);

		} catch (JAXBException e) {
			throw new EJBException("Failed to Marshall request", e);
		}
		return sw.toString();
	}
	
	
	public static PlotterDriverRequestType driverRequestFromXml(String xml) {

		Unmarshaller unmarshaller;
		JAXBElement<PlotterDriverRequestType> res = null;

		try {
			unmarshaller = jaxbContext.createUnmarshaller();
			res= unmarshaller.unmarshal(xmlFactory.createXMLStreamReader(new StringReader(xml)), PlotterDriverRequestType.class);
		} catch (JAXBException | XMLStreamException e) {
			throw new EJBException("Failed to unmarshall message '" + xml + "'", e);
		}

		return res.getValue();
	}
	
	
	public static String reponseToXml(PlotterDriverRequestType req){
		
		Writer sw = new StringWriter();
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectFactory.createPlotterDriverRequest(req), sw);

		} catch (JAXBException e) {
			throw new EJBException("Failed to Marshall request", e);
		}
		return sw.toString();
	}
	

}
