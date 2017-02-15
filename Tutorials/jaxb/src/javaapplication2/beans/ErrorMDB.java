/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2.beans;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
        

@MessageDriven(name="blahErrorMdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
     @ActivationConfigProperty(propertyName = "connectionFactory", propertyValue = "jms/ConnectionFactory"),
     @ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/error-queue"),})

public class ErrorMDB implements MessageListener {
    
    @Resource(mappedName = "weblogic/jms/XAConnectionFactory")
    private ConnectionFactory responseConnectionFactory;
    
    @Resource(mappedName = "jms/blah-response-queue")
    private Destination responseQueue;
    
    public void onMessage(Message message){
        if (message instanceof TextMessage){
            onMessage((TextMessage)message);
        } // else throw
    }
    
    public void onMessage (TextMessage message){
        try{
            Connection responseConnection = responseConnectionFactory.createConnection();
            try{
                Session responseSession= responseConnection.createSession(false, Session.SESSION_TRANSACTED);
                try{
                    String resp = "Error response";
                    TextMessage responseMessage = responseSession.createTextMessage(resp);
                            
                    responseMessage.setJMSCorrelationID(message.getJMSMessageID());
                    MessageProducer responseProducer = responseSession.createProducer(responseQueue);
                    responseProducer.send(responseMessage);
                    
                } finally {
                    responseSession.close();
                }
            } finally {
                
                responseConnection.close();
            } 
        } catch (JMSException e)  {
            throw new EJBException("" + e);
        }
    }
    
    public String onErrorMessage(String message){
        return "An error occured";
    }
    
}