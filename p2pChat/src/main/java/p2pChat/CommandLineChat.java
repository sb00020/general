package p2pChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CommandLineChat implements MessageListener {

	public static void main(String[] args) throws NamingException, JMSException, IOException {
		if (args.length != 3) {
			System.out.println("Three arguements not submitted");
		} else {

			String username = args[0];
			String queueName1 = "queue/" + args[1];
			String queueName2 = "queue/" + args[2];

			CommandLineChat cmd = new CommandLineChat();
			Context initialContext = cmd.getInitialContext();

			Queue queue1 = (Queue) initialContext.lookup(queueName1);
			Queue queue2 = (Queue) initialContext.lookup(queueName2);

			QueueConnectionFactory qcf = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");

			QueueConnection qc = qcf.createQueueConnection();

			cmd.subscribe(qc, queue1, cmd);
			cmd.publish(qc, queue2, username);

		}

	}

	public void onMessage(Message message) {
		try {
			System.out.println(((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	public void subscribe(QueueConnection queueConnection, Queue queue, CommandLineChat commandLineChat)
			throws JMSException, IOException {

		QueueSession subscribeSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueReceiver queueReceiver = subscribeSession.createReceiver(queue);
		queueReceiver.setMessageListener(commandLineChat);

	}

	public void publish(QueueConnection queueConnection, Queue queue, String userName)
			throws JMSException, IOException {
		QueueSession publishSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueSender queueSender = publishSession.createSender(queue);
		queueConnection.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String messageToSend = reader.readLine();
			if (messageToSend.equalsIgnoreCase("exit")) {
				queueConnection.close();
				System.exit(0);

			} else {
				TextMessage message = publishSession.createTextMessage();
				message.setText("{" + userName + ": " + messageToSend + "}");
				queueSender.send(message);
			}
		}

	}

	public static Context getInitialContext() throws NamingException {

		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
		props.setProperty("java.naming.provider.url", "localhost:8080");
		Context context = new InitialContext(props);

		return context;
		
//		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
//		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//		
//		// Setting JNDI Context
//		final Context context = new InitialContext(jndiProperties);
//		
//		return context;
		
		

	}

}
