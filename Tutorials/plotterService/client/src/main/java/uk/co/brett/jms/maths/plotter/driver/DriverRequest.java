package uk.co.brett.jms.maths.plotter.driver;

import com.brett.services.maths.PlotterDriverRequestType;

import uk.co.brett.jms.maths.plotter.jms.JMSFeeder;
import uk.co.brett.jms.maths.plotter.jms.MockJMSFeederImpl;
import uk.co.brett.jms.maths.plotter.old.PlotterMediator;

public class DriverRequest {

	public DriverRequest(PlotterDriverRequestType req) {
		String queueName = "";
		JMSFeeder feeder = new MockJMSFeederImpl(queueName);
		
		
		String xmlMessage = PlotterMediator.driverRequestToXml(req);
		
		feeder.feed(xmlMessage);
		
	}

}
