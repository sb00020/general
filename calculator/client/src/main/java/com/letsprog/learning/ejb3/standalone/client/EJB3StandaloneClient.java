package com.letsprog.learning.ejb3.standalone.client;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
 
import javax.naming.Context;
import javax.naming.InitialContext;
 
import com.letsprog.learning.ejb3.server.api.IRemoteQuiz;

import uk.co.brett.calculator.api.RemoteCalculator;
 
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
		//IRemoteQuiz remoteQuiz = (IRemoteQuiz) context.lookup("ejb:/ejb3-server-war-0.0.1-SNAPSHOT//QuizBean!com.letsprog.learning.ejb3.server.api.IRemoteQuiz?stateful");
		//calc-server-war-0.0.1-SNAPSHOT/CalculatorImpl!uk.co.brett.calculator.api.RemoteCalculator
		
		RemoteCalculator remoteCalc = (RemoteCalculator) context.lookup("ejb:/calc-server-war-0.0.1-SNAPSHOT/CalculatorImpl!uk.co.brett.calculator.api.RemoteCalculator?stateful");
		remoteCalc.start();

		double x = 3;
		double y = 4;
		
		System.out.println(remoteCalc.add(x, y));
		System.out.println(remoteCalc.subtract(x, y));
		System.out.println(remoteCalc.multiply(x, y));
		System.out.println(remoteCalc.divide(x, y));
		System.out.println(remoteCalc.average(x, y));
 
		remoteCalc.end();
	}
 
}