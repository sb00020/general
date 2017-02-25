package uk.co.brett.maths.plotter;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;

@MessageDriven(name = "PlotterMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/queue/maths/request"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class PlotterMDB implements MessageListener {

	@Resource(lookup = "java:jboss/exported/queue/maths/response")
	private Queue queue;

	@Inject
	private JMSContext context;

	private final static Logger LOGGER = Logger.getLogger(PlotterMDB.class.toString());

	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Received Message from queue: " + msg.getText());
				LOGGER.info("Received Message Id: " + msg.getJMSMessageID());

				addResponse(msg);

			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private void addResponse(TextMessage inputMessage) {

		String id = "";
		String text = "";
		try {
			id = inputMessage.getJMSCorrelationID();
			text = inputMessage.getText();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		LOGGER.info("Adding Response");
		
		
		
		
		try {
			final Destination destination = queue;
			LOGGER.info("Set dest");
			
			LOGGER.info(context.getClientID());

			
			
			Message message = context.createTextMessage("");
			LOGGER.info("Created Message");

			message.setJMSCorrelationID(id);
			message.setJMSMessageID(id);
			LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());

			context.createProducer().send(destination, text);
			LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());

		} catch (JMSException e) {

			e.printStackTrace();
		}

	}
}
