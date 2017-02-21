package uk.co.brett.maths.plotter.messages;


import com.mycompany.generics.messages.AbstractOutputMessage;

public class PlotterOutputMessage extends AbstractOutputMessage {

	private double x, y;
	
	
	
	@Override
	public int compareTo(AbstractOutputMessage arg0) {
		// TODO Auto-generated method stub
		return 0;
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}

}
