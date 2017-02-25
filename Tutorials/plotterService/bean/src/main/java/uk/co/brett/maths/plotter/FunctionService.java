package uk.co.brett.maths.plotter;

import java.util.Random;
import java.util.concurrent.Callable;

import uk.co.brett.maths.plotter.messages.PlotterInputMessage;
import uk.co.brett.maths.plotter.messages.PlotterOutputMessage;

public class FunctionService implements Callable  {

	PlotterInputMessage input;
	
	public FunctionService (PlotterInputMessage input){
		this.input = input;
	}
	
	public double function(double x){
		
		Random rnd = new Random();
		
		//System.out.println(Thread.currentThread().getName() + "   " + input.getThreadNumber());
		
		try {
			Thread.sleep((long)(5000*rnd.nextDouble()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return Math.pow(x, 5) - 2.* Math.pow(x, 4)+ 3.*Math.pow(x, 3)+ Math.pow(x, 2) + x;
		
	}

	@Override
	public PlotterOutputMessage call() throws Exception {
		
		double y = function(input.getX());
		
		PlotterOutputMessage out = new PlotterOutputMessage();
		
		out.setX(input.getX());
		out.setY(y);
		out.thread = input.getThreadNumber();
		
		return out;
	}


}
