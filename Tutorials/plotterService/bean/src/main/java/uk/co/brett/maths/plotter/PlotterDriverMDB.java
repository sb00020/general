package uk.co.brett.maths.plotter;

import java.util.ArrayList;
import java.util.Random;
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

import com.brett.services.maths.PlotterDriverRequestType;
import com.brett.services.maths.PlotterDriverResponseType;
import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;

@MessageDriven(name = "PlotterDriverMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/queue/maths/driver/request"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class PlotterDriverMDB implements MessageListener {

	@Resource(lookup = "java:jboss/exported/queue/maths/driver/response")
	private Queue queue;

	@Resource(lookup = "java:jboss/exported/queue/maths/request")
	private Queue subQueue;

	@Inject
	private JMSContext context;

	private final static Logger LOGGER = Logger.getLogger(PlotterDriverMDB.class.toString());

	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Received Message from queue: " + msg.getText());
				LOGGER.info("Received Message Id: " + msg.getJMSMessageID());

				Mixed m = callFunction(msg);

				addResponse(PlotterMediator.reponseToXml(m.response), msg);

				addRequests(m.requests, msg.getJMSMessageID());

			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

	private Mixed callFunction(final TextMessage msg) {

		try {
			PlotterDriverRequestType request = PlotterMediator.driverRequestFromXml(msg.getText());

			Mixed m = generateFunctionRequests(request, msg.getJMSMessageID());

			return m;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Mixed generateFunctionRequests(PlotterDriverRequestType request, String messageId) {
		ArrayList<PlotterRequestType> requests = new ArrayList<PlotterRequestType>();

		Random r = new Random();
		double range = (request.getUpperLimit() - request.getLowerLimit());

		// do some error checking maybe

		for (int i = 0; i < request.getNumberOfMessages(); i++) {
			PlotterRequestType singleRequest = new PlotterRequestType();
			double x = r.nextDouble() * range + request.getLowerLimit();
			singleRequest.setId(i);
			singleRequest.setX((float) x);
			requests.add(singleRequest);

		}

		// addRequests(requests, messageId);

		PlotterDriverResponseType response = new PlotterDriverResponseType();
		response.setNumberOfMessages(request.getNumberOfMessages());

		Mixed m = new Mixed();
		m.requests = requests;
		m.response = response;

		return m;

	}

	private class Mixed {
		ArrayList<PlotterRequestType> requests = new ArrayList<PlotterRequestType>();
		PlotterDriverResponseType response = new PlotterDriverResponseType();

	}

	private void addRequests(ArrayList<PlotterRequestType> requests, String messageId) {

		for (PlotterRequestType p : requests) {
			
			String request = PlotterMediator.requestToXml(p);
			
			try {
				final Destination destination = subQueue;
				LOGGER.info("Set dest");

				Message message = context.createTextMessage("");
				LOGGER.info("Created Message");

				message.setJMSCorrelationID(messageId);

				LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());

				context.createProducer().send(destination, request);
				LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());

			} catch (JMSException e) {

				e.printStackTrace();
			}

		}

	}
	
	public void addRequest(String messageId, String response){
		
		try {
			final Destination destination = subQueue;
			LOGGER.info("Set dest");

			Message message = context.createTextMessage("");
			LOGGER.info("Created Message");

			message.setJMSCorrelationID(messageId);

			LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());

			context.createProducer().send(destination, response);
			LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());

		} catch (JMSException e) {

			e.printStackTrace();
		}
		
	}

	private void addResponse(final String response, final TextMessage inputMessage) {

		LOGGER.info("Adding Response");

		try {
			final Destination destination = queue;
			LOGGER.info("Set dest");

			LOGGER.info(context.getClientID());

			Message message = context.createTextMessage("");
			LOGGER.info("Created Message");

			message.setJMSCorrelationID(inputMessage.getJMSMessageID());

			LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());

			context.createProducer().send(destination, response);
			LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());

		} catch (JMSException e) {

			e.printStackTrace();
		}

	}
}
