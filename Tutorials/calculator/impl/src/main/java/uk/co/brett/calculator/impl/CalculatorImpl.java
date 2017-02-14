package uk.co.brett.calculator.impl;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import uk.co.brett.calculator.api.Calculator;
import uk.co.brett.calculator.api.LocalCalculator;
import uk.co.brett.calculator.api.RemoteCalculator;
import uk.co.brett.maths.statistics.Statistics;

@Stateful
@Remote(RemoteCalculator.class)
@Local(LocalCalculator.class)
public class CalculatorImpl implements Calculator {

	@Override
	public double add(double x, double y) {

		return x + y;
	}

	@Override
	public double subtract(double x, double y) {

		return x - y;
	}

	@Override
	public double multiply(double x, double y) {

		return x * y;
	}

	@Override
	public double divide(double x, double y) {

		return x / y;
	}
	
	@Override
	public double average(double x, double y){
		
		Statistics stats = new Statistics();
		ArrayList<Double> list = new ArrayList<>();
		list.add(x);
		list.add(y);
		return stats.mean(list);
		
	}

	@Override
	public void start() {
		System.out.println("Calculator Service starting");
		
	}

	@Override
	public void end() {
		System.out.println("Calculator Service finish");
		
	}

}
