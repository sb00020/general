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
package uk.co.brett.jms.maths.plotter;

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

import javax.jms.Connection;

public class JMSFeeder {
    private static final Logger log = Logger.getLogger(JMSFeeder.class.getName());

    // Set up all the default values
    //private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_REQUEST_DESTINATION = "/queue/maths/driver/request";
    private static final String DEFAULT_RESPONSE_DESTINATION = "/queue/maths/driver/response";
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    private static final String DEFAULT_USERNAME = "jmsuser";
    private static final String DEFAULT_PASSWORD = "jmsuser@123";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    public String feeder(String content) {

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

            String destinationString = System.getProperty("destination", DEFAULT_REQUEST_DESTINATION);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination requestDestination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
           // String content = message;

            try (JMSContext context = connectionFactory.createContext(userName, password)) {
            	
            	String destinationRespString = System.getProperty("destination", DEFAULT_RESPONSE_DESTINATION);
            	
            	Destination reponseDestination = (Destination) namingContext.lookup(destinationRespString);
            	//DEFAULT_RESPONSE_DESTINATION
            	
                log.info("Sending " + count + " messages with content: " + content);
                // Send the specified number of messages
                
                JMSProducer producer = context.createProducer();
                
                log.info("Creating connection");
               try( Connection connection = connectionFactory.createConnection(DEFAULT_USERNAME, DEFAULT_PASSWORD)){
                
                log.info("Creating session");
                Session session = connection.createSession();
                
                
                log.info("Creating message");
                Message message = session.createTextMessage(content);

                producer.send(requestDestination, message);
                String id = message.getJMSMessageID();
                
                System.out.println("The message Id is: " + message.getJMSMessageID());
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
