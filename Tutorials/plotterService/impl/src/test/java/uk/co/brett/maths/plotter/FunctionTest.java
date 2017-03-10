package uk.co.brett.maths.plotter;

import org.junit.Test;

import org.junit.Assert;

public class FunctionTest {

	@Test
	public void zeroTest() {
		
		double x = 0.d;
		double result = Function.function(x);
		Assert.assertEquals(0.d, result,0.005);
		
	}
	
	@Test
	public void oneTest() {
		
		double x = 1.d;
		
		double result = Function.function(x);

		Assert.assertEquals(8.227d, result, 0.005);
		
	}
	
	@Test
	public void tenTest() {
		
		double x = 10.d;
		
		double result = Function.function(x);
		
		Assert.assertEquals(3.356d, result,0.005);
		
	}

}
