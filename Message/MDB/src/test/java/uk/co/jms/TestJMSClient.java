package uk.co.jms;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.Test;

public class TestJMSClient {

	private String MESSAGE = "Hello, World!";
	private String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private String DESTINATION = "jms/queue/exampleQueue";

	@Test
	public void testSendReceive() throws Exception {

		Context namingContext = null;
		JMSContext context = null;
		String user = "userSam";
		String password = "userPassword1!";

		try {

			// Set up the namingContext for the JNDI lookup
			final Properties env = new Properties();
			//env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			//env.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
			//env.put(Context.SECURITY_PRINCIPAL, user);
			//env.put(Context.SECURITY_CREDENTIALS, password);
			
			//env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory"); 
			//env.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.naming"); 
			//env.setProperty(Context.PROVIDER_URL, "localhost:1099"); 
			
			
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447");
			// username
			env.put(Context.SECURITY_PRINCIPAL, user);
			// password
			env.put(Context.SECURITY_CREDENTIALS, password);
			// This is an important property to set if you want to do EJB invocations via the remote-naming project
			env.put("jboss.naming.client.ejb.context", true);
			
			System.out.println("context ");
			namingContext = new InitialContext(env);
			System.out.println("context made");
			ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(CONNECTION_FACTORY);
			System.out.println("Got ConnectionFactory " + CONNECTION_FACTORY);

			Destination destination = (Destination) namingContext.lookup(DESTINATION);
			System.out.println("Got JMS Endpoint " + DESTINATION);

			// Create the JMS context
			context = connectionFactory.createContext(user, password);

			context.createProducer().send(destination, MESSAGE);
			System.out.println("Sent message " + MESSAGE);

			// Create the JMS consumer
			JMSConsumer consumer = context.createConsumer(destination);
			// Then receive the same number of messages that were sent

			String text = consumer.receiveBody(String.class, 5000);
			if (text == null)
				System.out.println("No message Received! Maybe another Consumer listening on the Queue ??");
			System.out.println("Received message with content " + text);
			assertEquals(text, MESSAGE);

		} catch (Exception e) {
			System.out.println("blh "+e.getMessage());
			throw e;
		} finally {
			if (namingContext != null) {
				namingContext.close();
			}

			// closing the context takes care of consumer too
			if (context != null) {
				context.close();
			}
		}
	}
}