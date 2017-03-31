package uk.co.brett.jms.maths.plotter.driver;

import com.brett.services.maths.PlotterDriverRequestType;

import uk.co.brett.jms.maths.plotter.jms.JMSDriver;
import uk.co.brett.jms.maths.plotter.old.PlotterMediator;

public class DriverRequest {
	JMSDriver driver;
	public static void main() {
		PlotterDriverRequestType req = new PlotterDriverRequestType();

		String id = sendDriverMessage(req);
		
		System.out.println(id);
		
		
	}
	
	public static String sendDriverMessage(PlotterDriverRequestType req){
		req.setLowerLimit(0);
		req.setNumberOfMessages(10);
		req.setUpperLimit(10);
		String xmlMessage = PlotterMediator.driverRequestToXml(req);
		
		
		JMSDriver driver = new JMSDriver("/queue/maths/driver/request","/queue/maths/driver/response");
		String id = driver.sendMessage(xmlMessage);
		
		System.out.println(id);
		String s = driver.receiveMessage(id);
		System.out.println(s);
		return id;
	}
	
	
	

}
