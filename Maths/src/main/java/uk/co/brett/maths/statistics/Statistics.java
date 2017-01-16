package uk.co.brett.maths.statistics;

import java.util.ArrayList;

public class Statistics {

	public double mean(ArrayList<Double> list){
		
		double sum = 0d;
		
		for (Double d:list){
			sum += d;
		}
		
		return sum/((double)list.size());
	}
	
	
}
