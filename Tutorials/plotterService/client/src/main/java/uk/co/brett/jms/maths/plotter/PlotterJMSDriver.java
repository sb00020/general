package uk.co.brett.jms.maths.plotter;

import java.util.ArrayList;

import javax.jms.JMSException;

import com.brett.services.maths.PlotterDriverRequestType;

public class PlotterJMSDriver {

	public void driver() throws InterruptedException, JMSException {

		int num = 20;

		System.out.println("Hello World");

		PlotterDriverRequestType req = new PlotterDriverRequestType();

		req.setLowerLimit(0);
		req.setUpperLimit(10);
		req.setNumberOfMessages(num);

		JMSFeeder feeder = new JMSFeeder();
		String ids = feeder.feeder(PlotterMediator.driverRequestToXml(req));

		System.out.println("\n\n" + ids);

		JMSListener listener = new JMSListener();

		String t;

		// for (int i = 0; i < num; i++) {
		t = listener.listen(ids);

		System.out.println(t);
		System.out.println(ids);
		// }

		ArrayList<String> responses = new ArrayList<String>();

		JMSListener2 listener2 = new JMSListener2();
		for (int i = 0; i < num; i++) {
			t = listener2.listen(ids);
			responses.add(t);
			
			
			System.out.println(t);
		}


	}

}