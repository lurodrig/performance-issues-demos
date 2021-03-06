package lurodrig.perfomance.examples;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Commons;

/**
 * Servlet implementation class SlowRequest
 */
@WebServlet("/SlowRequest")
public class SlowRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date start = new Date();
		long millis = Commons.getMillis(request); 
		Commons.sleep(SlowRequest.class.getName(), millis);
		Date finish = new Date();
		long duration = finish.getTime() - start.getTime();
		out.println("SLOW REQUEST DURATION: " + duration);
	}
}
