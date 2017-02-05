package uk.co.brett.datasource.test;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/createevent")
public class CreateEvents extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "java:/MySqlDS")
	DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Message Received");

		try (Connection conn = dataSource.getConnection()) {

			System.out.println("Connection closed: " + conn.isClosed());

			PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) FROM EVENT");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getBigDecimal("COUNT(*)"));
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// System.out.println(dateFormat.format(date)); //2016/11/16
			// 12:08:43

			String message = "My Event at " + dateFormat.format(date);

			PreparedStatement insert = conn
					.prepareStatement("INSERT INTO EVENT(EVENT, COMMENT) VALUES ('New Event', ?)");

			insert.setString(1, message);
			boolean res = insert.execute();
			System.out.println("result: " + res);

			rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getBigDecimal("COUNT(*)"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}