package uk.co.brett.random.singleton;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolDriver {

	public static void main(String[] args) {

		int threads = 10;
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
		
		ArrayList<Random> rnds = RandomFactory.GetRands(threads);
		
		for (int i = 0; i < threads; i++) {
			RunnableRandoms task = new RunnableRandoms(rnds.get(i));
			task.setThreadId(i);
			executor.execute(task);
		}
		executor.shutdown();
	}
}
