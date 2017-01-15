package co.uk.trickster.banana.co;

public interface Statistics {

	void begin();

	double sum(double[] list);

	double average(double[] list);

	double stDev(double[] list);

	void end();
}
