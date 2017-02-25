package uk.co.brett.maths.plotter.messages;

import com.mycompany.generics.messages.AbstractInputMessage;

public class PlotterInputMessage extends AbstractInputMessage {

	private double x;
	
	public PlotterInputMessage(int threadNumber, double inX){
		
		this.setThreadNumber(threadNumber);
		setX(inX);
		
	}

	private void setX(double inX){
		x = inX;
	}
	
	public double getX(){
		return x;
	}
	
}
