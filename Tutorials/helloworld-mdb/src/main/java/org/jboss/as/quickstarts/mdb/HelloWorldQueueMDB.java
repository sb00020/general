/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.mdb;

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

/**
 * <p>
 * A simple Message Driven Bean that asynchronously receives and processes the
 * messages that are sent to the queue.
 * </p>
 *
 * @author Serge Pagop (spagop@redhat.com)
 *
 */
@MessageDriven(name = "HelloWorldQueueMDB", activationConfig = {
		// @ActivationConfigProperty(propertyName = "destinationLookup",
		// propertyValue = "/exported/queue/request"),
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/queue/request"),
		// @ActivationConfigProperty(propertyName = "destinationLookup",
		// propertyValue = "queue/HELLOWORLDMDBQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class HelloWorldQueueMDB implements MessageListener {

	@Resource(lookup = "java:jboss/exported/queue/response")
	private Queue queue;

	@Inject
	private JMSContext context;

	private final static Logger LOGGER = Logger.getLogger(HelloWorldQueueMDB.class.toString());

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Received Message from queue: " + msg.getText());
				LOGGER.info("Received Message Id: " + msg.getJMSMessageID());
				LOGGER.warning("This is magic");

				addResponse(msg.getJMSMessageID());
				
				Thread.sleep(1000);

			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void addResponse(String id) {
		try {
			final Destination destination = queue;

			String text = "my witty reposnse";
			Message message = context.createTextMessage(text);

			
			message.setJMSCorrelationID(id);
			message.setJMSMessageID(id);
			LOGGER.info("Sending Response Message Id: " + message.getJMSCorrelationID());
			
			context.createProducer().send(destination, text);
			LOGGER.info("Sent Response Message Id: " + message.getJMSCorrelationID());
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
