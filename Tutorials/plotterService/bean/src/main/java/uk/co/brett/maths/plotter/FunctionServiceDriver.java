package uk.co.brett.maths.plotter;

import java.util.ArrayList;
import java.util.Random;

import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;

import uk.co.brett.maths.plotter.messages.PlotterInputMessage;

public class FunctionServiceDriver {

	public void blah (){
		double x = 0.1; 
		PlotterInputMessage im = new PlotterInputMessage(8, x);
	}
	
	
	
	public static ArrayList<PlotterResponseType> run(PlotterRequestType request) {
		ArrayList<PlotterResponseType> response = new ArrayList<PlotterResponseType>();
		Random rand = new Random();

//		double range = request.getUpperLimit() - request.getLowerLimit();
//		for (int i = 0; i < request.getNumberOfMessages(); i++) {
//			PlotterResponseType localResp = new PlotterResponseType();
//			double x = rand.nextDouble() * range + request.getLowerLimit();
//
//			double y = FunctionService.function(x);
//			localResp.setId(i);
//			
//			localResp.setX((float) x);
//			localResp.setY((float) y);
//			
//			response.add(localResp);
//		}

		return response;
	}

}
