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

@WebServlet(urlPatterns = "/deleteevent")
public class DeleteEvents extends HttpServlet {
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

			
			PreparedStatement insert = conn
					.prepareStatement("DELETE FROM EVENT");

			insert.execute();
			
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