package com.letsprog.learning.ejb3.server.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import co.uk.trickster.banana.co.LocalStatistics;
import co.uk.trickster.banana.co.RemoteStatistics;
import co.uk.trickster.banana.co.Statistics;

@Stateful
@Remote(RemoteStatistics.class)
@Local(LocalStatistics.class)
public class StatisticsBean implements Statistics {

	@Override
	public double sum(double[] list) {

		double sum = 0d;

		for (double d : list) {
			sum += d;
		}

		return sum;
	}

	@Override
	public double average(double[] list) {

		return sum(list)/((double)list.length);
	}

	@Override
	public double stDev(double[] list) {
		
		double average = average(list);
		
		double sum = 0d;
		for (double d : list) {
			sum += Math.abs(d - average);
		}
		
		return sum/((double)list.length);
	}

	@Override
	public void begin() {
		System.out.println("Starting stats bean..");
	}

	@Remove
	@Override
	public void end() {
		System.out.println("StatsBean instance will be removed..");
	}
}
