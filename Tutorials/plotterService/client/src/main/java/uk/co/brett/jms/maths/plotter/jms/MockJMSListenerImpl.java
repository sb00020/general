package uk.co.brett.jms.maths.plotter.jms;

import uk.co.brett.jms.maths.plotter.utils.XYPoint;

public class MockJMSListenerImpl implements JMSListener {
	private static final int MAX_X = 20;

	public MockJMSListenerImpl(String queueName) {
		// TODO Auto-generated constructor stub
	}

	public Object listen() {
		XYPoint p = new XYPoint();
		p.setX(Math.random() * MAX_X);
		p.setY(function(p.getX()));
		return p;
	}

	private double function(double x) {
		return 10 * Math.sin(2 * x) * Math.exp(-0.1 * x);
	}
}
