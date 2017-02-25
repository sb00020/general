package uk.co.brett.jms;

import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import uk.co.brett.jms.data.Data;

public abstract class AbstractJmsUtil {

	private static final Logger log = Logger.getLogger(AbstractJmsUtil.class.getName());
	private Data data = null;

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public String addMessage(final String inputMessage) {
		return AbstractJmsUtil.feed(inputMessage, getData());
	}

	public String receiveMessage(String id) throws JMSException {
		System.out.println(getData().getRequestQueue());
		return AbstractJmsUtil.listen(id, getData());
	}

	public String receiveMessage() throws JMSException {
		System.out.println(getData().getRequestQueue());
		return AbstractJmsUtil.listen(getData());
	}

	private static String feed(final String inputMessage, final Data data) {
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

			String destinationString = System.getProperty("destination", data.getRequestQueue());
			log.info("Attempting to acquire destination \"" + destinationString + "\"");
			Destination requestDestination = (Destination) namingContext.lookup(destinationString);
			log.info("Found destination \"" + destinationString + "\" in JNDI");

			int count = Integer.parseInt(System.getProperty("message.count", Data.DEFAULT_MESSAGE_COUNT));
			String content = System.getProperty("message.content", inputMessage);

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

	private static String listen(String id, Data data) throws JMSException {

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
				String destinationRespString = System.getProperty("destination", data.getResponseQueue());
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

	private static String listen(Data data) throws JMSException {

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
				String destinationRespString = System.getProperty("destination", data.getResponseQueue());
				Destination reponseDestination = (Destination) namingContext.lookup(destinationRespString);


				log.info("Creating Consumer");

				// Create the JMS consumer
				JMSConsumer consumer = context.createConsumer(reponseDestination);
				Message text = consumer.receive();

				TextMessage tm = (TextMessage) text;
			//	log.info("First 100 Charachters of message: " + tm.getText().substring(0, 100));

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
