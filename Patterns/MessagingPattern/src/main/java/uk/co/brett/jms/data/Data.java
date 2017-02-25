package uk.co.brett.jms.data;

public class Data {
	// Set up all the default values
	public static final String DEFAULT_MESSAGE = "Hello, World!";
	public static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private String REQUEST_DESTINATION = "/queue/request";
	private String RESPONSE_DESTINATION = "/queue/response";
	public static final String DEFAULT_MESSAGE_COUNT = "1";
	public static final String DEFAULT_USERNAME = "jmsuser";
	public static final String DEFAULT_PASSWORD = "jmsuser@123";
	public static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	public static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	
	
	public Data (final String requestQueue, final String responseQueue){
		
		REQUEST_DESTINATION = requestQueue;
		RESPONSE_DESTINATION = responseQueue;
		
	}
	
	public String getRequestQueue(){
		return this.REQUEST_DESTINATION;
	}
	
	public String getResponseQueue(){
		return this.RESPONSE_DESTINATION;
	}
}
