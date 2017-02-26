package uk.co.brett.jms.maths.plotter;

import java.util.ArrayList;

import javax.jms.JMSException;

import com.brett.services.maths.PlotterDriverRequestType;
import com.brett.services.maths.PlotterDriverResponseType;
import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;

public class PlotterJMSDriver {

	public static void main(String... args) throws InterruptedException, JMSException {

		int num = 50;

		System.out.println("Hello World");

		PlotterDriverRequestType req = new PlotterDriverRequestType();

		req.setLowerLimit(0);
		req.setUpperLimit(10);
		req.setNumberOfMessages(num);

		JMSFeeder feeder = new JMSFeeder();
		String ids = feeder.feeder(PlotterMediator.driverRequestToXml(req));

		System.out.println("\n\n" + ids);

		JMSListener listener = new JMSListener();

		// for (int i = 0; i < num; i++) {
		String t = listener.listen(ids);

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

		System.out.println("\n\n\n");
		
		for (String s: responses){
			System.out.println(s);
			
		}
		
		
		// PlotterRequestType request = new PlotterRequestType();
		//
		// request.setId(0);
		// request.setX(1.f);
		//
		// String content = PlotterMediator.requestToXml(request);
		//
		//// JMSFeeder feeder = new JMSFeeder();
		//// String ids = feeder.feeder(content);
		////
		//// System.out.println("Expected id: " + ids);
		////
		//// JMSListener listener = new JMSListener();
		////
		////
		//// String t = listener.listen(ids);
		//
		// System.out.println(content);

	}

}