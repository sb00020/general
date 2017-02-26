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

import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;

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

				addResponse(callFunction(msg.getText()), msg);

			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private String callFunction(final String msg) {

		PlotterRequestType request = PlotterMediator.requestFromXml(msg);
		PlotterResponseType response = new PlotterResponseType();

		response.setId(request.getId());
		response.setX(request.getX());
		response.setY((float) Function.function(request.getX()));
		String s = PlotterMediator.responseToXml(response);
		System.out.println(s);

		return s;

	}

	private void addResponse(final String response, final TextMessage inputMessage) {

		LOGGER.info("Adding Response");

		try {
			final Destination destination = queue;
			LOGGER.info("Set dest");

			Message message = context.createTextMessage("");
			LOGGER.info("Created Message");

			message.setJMSCorrelationID(inputMessage.getJMSCorrelationID());

			LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());

			context.createProducer().send(destination, response);
			LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());

		} catch (JMSException e) {

			e.printStackTrace();
		}

	}
}
