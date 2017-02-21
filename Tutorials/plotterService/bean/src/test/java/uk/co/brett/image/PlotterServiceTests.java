package uk.co.brett.image;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.brett.services.maths.ObjectFactory;
import com.brett.services.maths.PlotterRequestType;
import com.brett.services.maths.PlotterResponseType;

import randomthreadpool.OutputMessage;
import uk.co.brett.maths.plotter.FunctionServiceDriver;
import uk.co.brett.maths.plotter.PlotterMediator;
import uk.co.brett.maths.plotter.messages.ConcreteThreadPool;
import uk.co.brett.maths.plotter.messages.PlotterInputMessage;
import uk.co.brett.maths.plotter.messages.PlotterOutputMessage;

public class PlotterServiceTests {

	@Test
	public void test() {

		ObjectFactory ob = new ObjectFactory();
		PlotterRequestType req = ob.createPlotterRequestType();
		
		req.setLowerLimit((float)0.1d);
		req.setUpperLimit((float) 10.);
		req.setNumberOfMessages(10);
		
		String s = PlotterMediator.requestToXml(req);
		System.out.println(s);
		
		PlotterRequestType var = PlotterMediator.requestFromXml(s);
		
		FunctionServiceDriver fsd = new FunctionServiceDriver();
		ArrayList<PlotterResponseType> resp = FunctionServiceDriver.run(var);
		
		for (PlotterResponseType p : resp){
			System.out.println(p.getX() + "  " +p.getY());
		}
		
	}
	
	@Test
	public void test2() {
		
		ConcreteThreadPool c = new ConcreteThreadPool(100,8);
		ArrayList<PlotterInputMessage> input = new ArrayList<PlotterInputMessage> ();
		Random r = new Random();
		for (int i = 0; i < 100; i++){
			
			PlotterInputMessage p = new PlotterInputMessage(i, r.nextDouble());
			
			input.add(p);
		}
		
		
		
		//ArrayList<Callable<PlotterOutputMessage>> o = c.createCallables(input);
        ArrayList<PlotterOutputMessage> outputs = (ArrayList<PlotterOutputMessage>) c.execute(c.createCallables(input));
		
        for (PlotterOutputMessage o : outputs) {
        	System.out.println(o.thread + "   " +o.getX() +"  "+ o.getY());
        }
        
	}
	
	@Test
	public void test3() throws InterruptedException {
		ConcreteThreadPool c = new ConcreteThreadPool(100,8);
		CompletionService compService = new ExecutorCompletionService(
			    Executors.newFixedThreadPool(5));
		
		ArrayList<PlotterInputMessage> input = new ArrayList<PlotterInputMessage> ();
		Random r = new Random();
		for (int i = 0; i < 100; i++){
			
			PlotterInputMessage p = new PlotterInputMessage(i, r.nextDouble());
			
			input.add(p);
		}
		
		int remainingFutures = 0;
		 
		for (Callable<PlotterOutputMessage> blah: c.createCallables(input)) {
		    remainingFutures++;
		 
		    compService.submit(blah);
		}
		 
		Future<PlotterOutputMessage> completedFuture;
		PlotterOutputMessage newWidget;
		 
		while (remainingFutures > 0) {
			
			
		    // block until a callable completes
		    completedFuture = compService.take();
		    remainingFutures--;
		 
		    // get the Widget, if the Callable was able to create it
		    try {
		        newWidget = completedFuture.get();
		    } catch (ExecutionException e) {
		        Throwable cause = e.getCause();
		       
		        continue;
		    }
		    System.out.println(newWidget.getX() + "  " +newWidget.getY());
		}
	}
	

}
