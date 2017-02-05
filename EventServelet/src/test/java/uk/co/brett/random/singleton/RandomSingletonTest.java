package uk.co.brett.random.singleton;

import org.junit.Test;

public class RandomSingletonTest {

	@Test
	public void test() {

		RandomSingleton rs = RandomSingleton.getInstance(12);

		System.out.println(rs.nextInt());
		System.out.println(rs.nextInt());
		System.out.println(rs.nextInt());
		System.out.println(rs.nextInt()+ "\n");
		
		RandomSingleton r2 = RandomSingleton.getInstance(13);

		System.out.println(r2.nextInt());
		System.out.println(r2.nextInt());
		System.out.println(r2.nextInt());
		System.out.println(r2.nextInt());


	}

}
