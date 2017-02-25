package uk.co.brett.maths.plotter.messages;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import com.mycompany.generics.AbstractThreadPool;

import uk.co.brett.maths.plotter.FunctionService;

public class ConcreteThreadPool extends AbstractThreadPool<PlotterOutputMessage> {

	public ConcreteThreadPool(int t, int e) {
		super(t, e);
		
	}

	@Override
	public ArrayList<Callable<PlotterOutputMessage>> createCallables() {
		ArrayList<Callable<PlotterOutputMessage>> callables = new ArrayList<>();
		return callables;
	}

	@Override
	public ArrayList<Callable<PlotterOutputMessage>> createCallables(ArrayList input) {
		ArrayList<Callable<PlotterOutputMessage>> callables = new ArrayList<>();
        for (int i = 0; i < getNumThreads(); i++) {
        	PlotterInputMessage iMess = (PlotterInputMessage) input.get(i);
            callables.add(new FunctionService(iMess));
        }
		
		return callables;
	}

}
