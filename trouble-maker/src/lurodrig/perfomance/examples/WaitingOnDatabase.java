package lurodrig.perfomance.examples;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Constants;
import lurodrig.performance.utils.Commons;

/**
 * Servlet implementation class WaitingOnDatabase
 */
@WebServlet("/WaitingOnDatabase")
public class WaitingOnDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Properties properties = (Properties) request.getServletContext()
					.getAttribute(Constants.TROUBLE_MAKER_PROPERTIES);
			connection = Commons.getConnection(
					properties.getProperty(Constants.DB_CONNECTION_USER),
					properties.getProperty(Constants.DB_CONNECTION_PASSWORD),
					properties.getProperty(Constants.DB_CONNECTION_DRIVER),
					properties.getProperty(Constants.DB_CONNECTION_URL));
			String sql = Constants.DB_PROCEDURE;
			callableStatement = connection.prepareCall(sql);
			long millis = Commons.getMillis(request);
			callableStatement.setLong(1, millis);
			Date start = new Date();
			callableStatement.executeUpdate();
			Date finish = new Date();
			out.println("I have been in the database for so long...(from "
					+ start + " to " + finish + ")");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				callableStatement.close();
			} catch (Exception e) {
				e.printStackTrace(out);
			}
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace(out);
			}
		}
	}

}
