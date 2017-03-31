package uk.co.brett.jms.maths.plotter.driver;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.co.brett.jms.maths.plotter.old.JMSFeeder;
import uk.co.brett.jms.maths.plotter.old.JMSListener;

public class DriverRequestTest {

	@Test
	public void test() {
		DriverRequest.main();
	}
	
	@Test
	public void test2() {
		JMSFeeder feed = new JMSFeeder();
		feed.feeder("");
		
	}

}
