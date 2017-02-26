package uk.co.brett.maths.plotter;

import org.junit.Test;

import junit.framework.Assert;

public class FunctionTest {

	@Test
	public void zeroTest() {
		
		double x = 0.d;
		
		double result = Function.function(x);
		
		Assert.assertEquals(8.d, result);
		
	}
	
	@Test
	public void oneTest() {
		
		double x = 1.d;
		
		double result = Function.function(x);
		
		Assert.assertEquals(12.d, result);
		
	}
	
	@Test
	public void tenTest() {
		
		double x = 10.d;
		
		double result = Function.function(x);
		
		Assert.assertEquals(83118.d, result);
		
	}

}
