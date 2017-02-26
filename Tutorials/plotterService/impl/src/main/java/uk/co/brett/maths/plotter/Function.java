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
		
		return Math.pow(x, 5) - 2.* Math.pow(x, 4)+ 3.*Math.pow(x, 3)+ Math.pow(x, 2) + x +8;
		
	}


}
