package uk.co.brett.jms;

import java.util.logging.Logger;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uk.co.brett.jms.data.Data;

import javax.jms.Connection;

public class JMSFeeder {
	private static final Logger log = Logger.getLogger(JMSFeeder.class.getName());

	public String feeder() {

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

			String destinationString = System.getProperty("destination", Data.DEFAULT_REQUEST_DESTINATION);
			log.info("Attempting to acquire destination \"" + destinationString + "\"");
			Destination requestDestination = (Destination) namingContext.lookup(destinationString);
			log.info("Found destination \"" + destinationString + "\" in JNDI");

			int count = Integer.parseInt(System.getProperty("message.count", Data.DEFAULT_MESSAGE_COUNT));
			String content = System.getProperty("message.content", Data.DEFAULT_MESSAGE);

			try (JMSContext context = connectionFactory.createContext(userName, password)) {

				log.info("Sending " + count + " messages with content: " + content);
				JMSProducer producer = context.createProducer();

				log.info("Creating connection");
				try (Connection connection = connectionFactory.createConnection(Data.DEFAULT_USERNAME,
						Data.DEFAULT_PASSWORD)) {

					log.info("Creating session");
					Session session = connection.createSession();

					log.info("Creating message");
					Message message = session.createTextMessage(content);

					producer.send(requestDestination, message);
					String id = message.getJMSMessageID();

					System.out.println("The correlation Id is: " + id);
					connection.close();
					return id;

				}
			} catch (JMSException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
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
