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
package uk.co.brett.jms;

import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSListener {
	private static final Logger log = Logger.getLogger(JMSListener.class.getName());

	// Set up all the default values
	private static final String DEFAULT_MESSAGE = "Hello, World!";
	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_REQUEST_DESTINATION = "/queue/image/request";
    private static final String DEFAULT_RESPONSE_DESTINATION = "/queue/image/response";
	private static final String DEFAULT_MESSAGE_COUNT = "1";
	private static final String DEFAULT_USERNAME = "jmsuser";
	private static final String DEFAULT_PASSWORD = "jmsuser@123";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

	public String listen(String id) throws JMSException {

		Context namingContext = null;

		try {
			String userName = System.getProperty("username", DEFAULT_USERNAME);
			String password = System.getProperty("password", DEFAULT_PASSWORD);

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);
			namingContext = new InitialContext(env);

			// Perform the JNDI lookups
			String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
			log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
			log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

			int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
			String content = System.getProperty("message.content", DEFAULT_MESSAGE);

			try (JMSContext context = connectionFactory.createContext(userName, password)) {

				String destinationRespString = System.getProperty("destination", DEFAULT_RESPONSE_DESTINATION);

				Destination reponseDestination = (Destination) namingContext.lookup(destinationRespString);
				// DEFAULT_RESPONSE_DESTINATION

				String jmsId = "JMSCorrelationID = '"+id+"'";


				log.info("Selector = " + jmsId);
				
				// Create the JMS consumer
				//JMSConsumer consumer = context.createConsumer(reponseDestination, jmsId);
				JMSConsumer consumer = context.createConsumer(reponseDestination);
				// Then receive the same number of messages that were sent
				log.info("Consumer Created");
				Message text = consumer.receive();
				log.info("Receiving");
				
				
				StringBuilder buffer = new StringBuilder("Properties: \n");
				Enumeration propertyNames = text.getPropertyNames();
				while(propertyNames.hasMoreElements()) {
					String name = (String)propertyNames.nextElement();
					buffer.append("  ");
					buffer.append(name);
					buffer.append(": ");
					buffer.append(text.getStringProperty(name));
					buffer.append("\n");
				}
				log.info(buffer.toString());
				
				TextMessage tm = (TextMessage) text;
				log.info("Received message with content " + text);
				
				log.info(text.getJMSMessageID());
				log.info(text.getJMSCorrelationID());
				log.info(tm.getText());
				
				return tm.getText();

			}
		} catch (NamingException e) {
			log.severe(e.getMessage());
		} finally {
			if (namingContext != null) {
				try {
					namingContext.close();
				} catch (NamingException e) {
					log.severe(e.getMessage());
				}
			}
		}
		return null;
	}
}
