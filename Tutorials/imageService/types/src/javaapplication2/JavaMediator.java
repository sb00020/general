/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ObjectFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author brettsa
 */
public class JavaMediator {

    private static final XMLInputFactory xmlFactory =XMLInputFactory.newFactory();
    private static final OutputFactory outputFactory =new OutputFactory(); // From types
    private static final JAXBContext jaxbContext;
    
    static{
        try{
            jaxbContext = JAXBContext.newInstance(JavaRequestType.class, JavaResponseType.class);
            
        }
        
        
    }
    
    private JavaMediator(){};
    
    public static ExtractRequestType requestFromXml(String xml){
        
        Unmarshaller unmarshaller;
        JAXBElement<ExtractRequestType> req = null;
        try{
            unmarshaller = jaxbContext.createUnmarshaller();
            req = unmarshaller.unmarshal(xmlFactory.createXMLStreamReader(new StringReader(xml)), ExtractRequestType.class);
        } catch (JAXBException | XMLStreamException ex) {
            Logger.getLogger(JavaMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return req.getValue();
    }
            
    public static String requestToXml(ExtractRequestType req){
        Writer sw = new StringWriter();
        
        
        try{
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ObjectFactory.createRequest(req), sw);
        } catch (JAXBException ex) {
            Logger.getLogger(JavaMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sw.toString();
    } 
    
    public static String responseToXml(ExtractResponseType response){
        Writer sw = new StringWriter();
        try{
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ObjectFactory.createResponse(response), sw);
        } catch (JAXBException ex) {
            Logger.getLogger(JavaMediator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sw.toString();
    } 

}
