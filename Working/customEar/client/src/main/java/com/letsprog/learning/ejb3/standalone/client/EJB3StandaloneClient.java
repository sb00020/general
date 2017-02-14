package com.letsprog.learning.ejb3.standalone.client;
 
import java.util.Hashtable;
 
import javax.naming.Context;
import javax.naming.InitialContext;

import co.uk.trickster.banana.co.RemoteStatistics;
 

 
public class EJB3StandaloneClient {
 //ejb-client
//	Pwd1234+

	public static void main(String[] args) throws Exception {
 
		// Defining JNDI properties - (!) Other properties are set in jboss-ejb-client.proerties
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		
		// Setting JNDI Context
		final Context context = new InitialContext(jndiProperties);
		
		// Using the JNDI name of the EJB following this pattern :
		// "ejb:{EarName}/{ModuleName}/{DeploymentName}/{EJBClassName}!{EJBInterfaceFullyQualifiedName}?{Stateful}"
		RemoteStatistics remoteQuiz = (RemoteStatistics) context.lookup("java:app/statistics/StatisticsBean!co.uk.trickster.banana.co.RemoteStatistics");
		
		double[] list = {1,2,3,4,5};
		
		remoteQuiz.sum(list);
		
		
		remoteQuiz.end();
	}
 
}