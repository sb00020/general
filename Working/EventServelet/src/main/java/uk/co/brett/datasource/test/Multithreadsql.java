package uk.co.brett.datasource.test;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import uk.co.brett.random.singleton.RandomFactory;
import uk.co.brett.random.singleton.RunnableRandoms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@WebServlet(urlPatterns = "/multithreadSQL")
public class Multithreadsql extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:/MySqlDS")
	DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Message Received");

		int threads = 50;

		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

		ArrayList<Random> rnds = RandomFactory.GetRands(threads);

		for (int j = 0; j < threads; j++) {
			RunnableRandoms task = new RunnableRandoms(rnds.get(j), dataSource);
			task.setThreadId(j);
			executor.execute(task);
		}
		executor.shutdown();

	}
}