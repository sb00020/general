package com.letsprog.learning.ejb3.local.client;
 
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
 
//import com.letsprog.learning.ejb3.server.api.IRemotePlayedQuizzesCounter;
//import com.letsprog.learning.ejb3.server.api.IRemoteQuiz;
import com.letsprog.learning.jndi.lookup.ejb3.LookerUp;

import co.uk.trickster.banana.co.LocalStatistics;
import co.uk.trickster.banana.co.RemoteStatistics;
 
@ManagedBean(name="statisticsManagedBean")
@SessionScoped
public class StatisticsManagedBean {
 
private RemoteStatistics statsProxy;

	@PostConstruct
	public void setup(){
		System.out.println("Setting up after creating the JSF managed bean.");
		
	}
	
	public void start() throws NamingException{
		
		
		
		//--- EJB Lookup in Same EAR (Such configuration could be externalized in a file for example)
		String ejbsServerAddress = "127.0.0.1";
		int ejbsServerPort = 8080;
		String earName = "custom-client-ear";
		String moduleName = "custom-ear-war";
		String deploymentDistinctName = "";
		String beanName = "StatisticsBean";
		String interfaceQualifiedName = LocalStatistics.class.getName();
		
		// No password required <= Component deployed in the same container
		LookerUp wildf9Lookerup = new LookerUp(ejbsServerAddress, ejbsServerPort);
 
		// We could instead use the following method by giving the exact JNDI name :
//		 quizProxy = (IRemoteQuiz) wildf9Lookerup.findSessionBean("ejb:ejb3-server-client-ear/ejb3-server-war//QuizBean!com.letsprog.learning.ejb3.server.api.IRemoteQuiz?stateful");
		statsProxy = (RemoteStatistics) wildf9Lookerup.findRemoteSessionBean(LookerUp.SessionBeanType.STATEFUL, earName, moduleName, deploymentDistinctName, beanName, interfaceQualifiedName);		
		
		statsProxy.begin();

	}
 
	@PreDestroy
	public void cleanUp(){
		System.out.println("Cleaning up after destroying the JSF managed bean.");
		statsProxy.end();
		statsProxy = null;
	}
 

}
