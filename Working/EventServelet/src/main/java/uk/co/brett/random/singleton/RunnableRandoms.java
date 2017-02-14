package uk.co.brett.random.singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;

public class RunnableRandoms implements Runnable {
	private final Random random;

	private int threadId;
	private DataSource dataSource;

	public RunnableRandoms(Random inRand, DataSource inDataSource) {
		random = inRand;
		dataSource = inDataSource;
	}

	public RunnableRandoms(Random inRand) {
		random = inRand;
	}

	@Override
	public void run() {

		for (int i = 0; i < 5; i++) {

			try (Connection conn = dataSource.getConnection()) {

				System.out.println("Connection closed: " + conn.isClosed());

				PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM EVENT");
				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					System.out.println(rs.getBigDecimal("COUNT(*)"));
				}

				int value = random.nextInt();

				String message = "Thread" + threadId + ":Loop:" + i + ":Value:" + value;

				System.out.println(message);

				String sql = "insert into threadtest (thread, value, message) values (?,?,?)";

				PreparedStatement insert = conn.prepareStatement(sql);

				insert.setInt(1, threadId);
				insert.setInt(2, value);
				insert.setString(3, message);

				insert.execute();

				Thread.sleep(100);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void setThreadId(int i) {
		threadId = i;

	}

}
