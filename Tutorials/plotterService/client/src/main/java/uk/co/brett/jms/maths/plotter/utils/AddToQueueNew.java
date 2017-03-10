//package uk.co.brett.jms.maths.plotter.utils;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import uk.co.brett.jms.maths.plotter.temp.XYPlotter;
//
//public class AddToQueueNew implements Runnable {
//	
//	private static final int MAX_DELAY=100; //ms
//	private static final int MAX_X = 20;
//	public void run() {
//		try {
//			XYPoint p = new XYPoint();
//			p.setX(Math.random() * MAX_X);
//			p.setY(function(p.getX()));
//			data.add(p);
//
//			Thread.sleep((long)(MAX_DELAY*Math.random()));
//			executor.execute(this);
//		} catch (InterruptedException ex) {
//			Logger.getLogger(XYPlotter.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}
//	
//	private double function(double x) {
//		return 10 * Math.sin(2*x) * Math.exp(-0.1 * x);
//	}
//}
