package uk.co.brett.jms.maths.plotter.jms;

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

public abstract class JMSDriver {

	private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String DEFAULT_USERNAME = "jmsuser";
	private static final String DEFAULT_PASSWORD = "jmsuser@123";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

	private String DEFAULT_REQUEST_DESTINATION = "/queue/maths/driver/request";
	private String DEFAULT_RESPONSE_DESTINATION = "/queue/maths/driver/response";

	private final String REQUEST_QUEUE;
	private final String RESPONSE_QUEUE;

	private static final Logger log = Logger.getLogger(JMSDriver.class.getName());

	public JMSDriver(String requestQueue, String responseQueue) {
		REQUEST_QUEUE = requestQueue;
		RESPONSE_QUEUE = responseQueue;
	}

	public JMSDriver() {
		REQUEST_QUEUE = DEFAULT_REQUEST_DESTINATION;
		RESPONSE_QUEUE = DEFAULT_RESPONSE_DESTINATION;
	}

	private InitialContext defaultContext() throws JMSException {
		try {
			String userName = System.getProperty("username", DEFAULT_USERNAME);
			String password = System.getProperty("password", DEFAULT_PASSWORD);

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
			env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
			env.put(Context.SECURITY_PRINCIPAL, userName);
			env.put(Context.SECURITY_CREDENTIALS, password);

			return new InitialContext(env);
		} catch (NamingException e) {
			throw new JMSException("");
		}
	}

	private JMSContext createJMSContext(ConnectionFactory connectionFactory) throws JMSException {
		return connectionFactory.createContext(DEFAULT_USERNAME, DEFAULT_PASSWORD);
	}

	private Destination createDestination(Context namingContext, boolean isRequest) throws JMSException {
		try {
			String destinationString;
			if (isRequest) {
				destinationString = System.getProperty("destination", REQUEST_QUEUE);
			} else {
				destinationString = System.getProperty("destination", RESPONSE_QUEUE);
			}
			log.config("Attempting to acquire destination \"" + destinationString + "\"");
			return (Destination) namingContext.lookup(destinationString);
		} catch (NamingException e) {
			throw new JMSException("");
		}
	}

	private ConnectionFactory createConnectionFactory(Context namingContext) throws JMSException {
		try {
			String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
			log.config("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
			log.config("Found connection factory \"" + connectionFactoryString + "\" in JNDI");
			return connectionFactory;
		} catch (NamingException e) {
			throw new JMSException("");
		}
	}

	public String sendMessage(String content) {

		try {
			Context namingContext = defaultContext();
			Destination requestDestination = createDestination(namingContext, true);
			ConnectionFactory connectionFactory = createConnectionFactory(namingContext);

			try (JMSContext context = createJMSContext(connectionFactory)) {

				log.config("Sending message with content: " + content);

				JMSProducer producer = context.createProducer();

				log.config("Creating connection");
				try (Connection connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {

					log.config("Creating session");
					Session session = connection.createSession();

					log.config("Creating message");
					Message message = session.createTextMessage(content);

					producer.send(requestDestination, message);
					String id = message.getJMSMessageID();

					log.config("The message Id is: " + message.getJMSMessageID());
					log.config("The correlation Id is: " + id);
					connection.close();
					return id;

				}
			}
		} catch (JMSException e) {

			throw new RuntimeException("It's broken");
		}

	}

	public String listen(String id) throws JMSException {

		Context namingContext = defaultContext();
		Destination responseDestination = createDestination(namingContext, false);
		ConnectionFactory connectionFactory = createConnectionFactory(namingContext);

		try (JMSContext context = createJMSContext(connectionFactory)) {

			String jmsId = "JMSCorrelationID = '" + id + "'";

			log.config("Selector = " + jmsId);

			JMSConsumer consumer = context.createConsumer(responseDestination);
			// Then receive the same number of messages that were sent
			log.config("Consumer Created");
			Message text = consumer.receive();
			log.config("Receiving");

			TextMessage tm = (TextMessage) text;
			log.config("Received message with content " + text);

			log.config(text.getJMSMessageID());
			log.config(text.getJMSCorrelationID());
			log.config(tm.getText());

			return tm.getText();

		}
	}
}