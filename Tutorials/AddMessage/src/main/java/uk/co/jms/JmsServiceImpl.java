package uk.co.jms;

import javax.naming.Context;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JmsServiceImpl {

	private final Context context;
	
	public JmsServiceImpl(final String name) throws Exception{
		this.context = ContextUtil.open(name);
	}
	
	public void close(){
		ContextUtil.close(context);
	}
	
	public String sendTextMessage(final String connectionFactoryName, final String destName, final String text){
		
		try {
			Destination requestQueue = (Destination) context.lookup(destName);
			ConnectionFactory connectionFactory = (ConnectionFactory)context.lookup(connectionFactoryName);
			Connection connection = connectionFactory.createConnection();
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				try{
					MessageProducer producer = session.createProducer(requestQueue);
					Message message = session.createTextMessage(text);
					producer.send(message);
					return message.getJMSMessageID();
				} finally {
					session.close();
				}
			} finally {
				connection.close();
			}
		} catch (Exception e){
			return "error";
		}

	}
	
	public String receiveTextMessage(final String connectionFactoryName, final String destName, final String correlationId){
		try {
			Destination responseQueue = (Destination) context.lookup(destName);
			ConnectionFactory connectionFactory = (ConnectionFactory)context.lookup(connectionFactoryName);
			Connection connection = connectionFactory.createConnection();
			try {
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				try{
					MessageConsumer consumer = session.createConsumer(responseQueue, correlationId != null ? "JMSCorrelationID='" + correlationId + "'" : null);
					Message message = consumer.receive();
					if (message instanceof TextMessage){
						return ((TextMessage)message).getText();
					}
					throw new RuntimeException("no messsage");
					
				} finally {
					session.close();
				}
			} finally {
				connection.close();
			}
		} catch (Exception e){
			return "error";
		}
		
	
	}
	
}
