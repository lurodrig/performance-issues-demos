package lurodrig.performance.examples.memory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Commons;

/**
 * Servlet implementation class PermgenSpace
 */
@WebServlet("/PermgenSpace")
public class PermgenSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Good by permanent generation space...");
		for (int i = 0; i < 100_000_000; i++) {
			try {
				String name = "eu.plumbr.demo.Generated" + i;
				Commons.generate(name);
				out.println(name);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}
