package uk.co.bean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.sql.DataSource;

import uk.co.service.MyService;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;

@MessageDriven(name = "ThisBean", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "myJmsTest/MyConnectionFactory"),
		@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "jms/queue/request")
})
public class BeanMDB implements MessageListener {

	@Resource(mappedName="myJmsTest/MyConnectionFactory")
	private ConnectionFactory responseConnectionFactory;
	
	@Resource(mappedName="jms/queue/response")
	private Destination responseQueue;
	
	//@Resource(mappedName="jdbc/DataSource")
	//private DataSource dataSource;
	
	private MyService service;
	
	@PostConstruct
	public void postConstruct(){
		service = new MyService();
	}
	
	@Override
	public void onMessage(final Message message) {
		if (message instanceof TextMessage){
			onMessage((TextMessage)message);
			
		}else{
			throw new EJBException("Message not text message");
		}
		
	}
	
	public void onMessage (final TextMessage message){
		try {
			Connection responseConnection = responseConnectionFactory.createConnection();
			try {
				Session responseSession = responseConnection.createSession(false, Session.SESSION_TRANSACTED);
				try{
					String responseXml = extractResult(message.getText());
					
					TextMessage responseMessage = responseSession.createTextMessage(responseXml);
					responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
					
					MessageProducer responseProducer = responseSession.createProducer(responseQueue);
					responseProducer.send(responseMessage);
					
				} finally {
					responseSession.close();
				}
			} finally {
				responseConnection.close();
			}
		} catch (JMSException e){
			throw new EJBException("An error occured ", e);
		}
	}

	private String extractResult(final String request){
		return service.execute(request);
	}
	
}
