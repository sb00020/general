package uk.co.brett.jms;

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

import uk.co.brett.jms.data.Data;

public class JMSListener {
	private static final Logger log = Logger.getLogger(JMSListener.class.getName());

	public String listen(String id) throws JMSException {

		Context namingContext = null;

		try {
			String userName = System.getProperty("username", Data.DEFAULT_USERNAME);
			String password = System.getProperty("password", Data.DEFAULT_PASSWORD);

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, Data.INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, Data.PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);
			namingContext = new InitialContext(env);

			// Perform the JNDI lookups
			String connectionFactoryString = System.getProperty("connection.factory", Data.DEFAULT_CONNECTION_FACTORY);
			log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
			log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

			try (JMSContext context = connectionFactory.createContext(userName, password)) {

				// Set up response destination
				String destinationRespString = System.getProperty("destination", Data.DEFAULT_RESPONSE_DESTINATION);
				Destination reponseDestination = (Destination) namingContext.lookup(destinationRespString);

				// Create a selector
				String jmsId = "JMSCorrelationID = '" + id + "'";
				log.info("Selector = " + jmsId);
				log.info("Creating Consumer");

				// Create the JMS consumer
				JMSConsumer consumer = context.createConsumer(reponseDestination, jmsId);
				Message text = consumer.receive();

				TextMessage tm = (TextMessage) text;
				log.info("First 100 Charachters of message: " + tm.getText().substring(0, 100));

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
