package uk.co.brett.calculator.api;

public interface Calculator {
	void start();
	double add(double x, double y);
	double subtract(double x, double y);
	double multiply(double x, double y);
	double divide(double x, double y);
	double average(double x, double y);
	void end();
}
