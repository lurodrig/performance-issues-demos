package lurodrig.perfomance.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestHeaderExample
 */
@WebServlet("/RequestHeaderExample")
public class RequestHeaderExample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestHeaderExample() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");

		String title ="REQUEST HEADER EXAMPLE";
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<h3>" + title + "</h3>");
		out.println("<table border=0>");
		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String headerName = e.nextElement();
			String headerValue = request.getHeader(headerName);
			out.println("<tr><td bgcolor=\"#CCCCCC\">");
			out.println(headerName);
			out.println("</td><td>");
			out.println(headerValue);
			out.println("</td></tr>");
		}
		out.println("</table>");
	}

}
