package uk.co.brett.maths.plotter;

import java.util.Random;
import java.util.logging.Logger;

public class Function {
	
	private static final Logger log = Logger.getLogger(Function.class.getName());
	
	public static double function(double x){
		
		Random rnd = new Random();
		
		log.info("Entered Function, x = " + x);
		
		try {
			Thread.sleep((long)(5000*rnd.nextDouble()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return 10 * Math.sin(2*x) * Math.exp(-0.1 * x);;
		
	}


}
