package lurodrig.performance.examples.memory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Key;

/**
 * Servlet implementation class MemoryLeak
 */
@WebServlet("/HeapSpace")
public class HeapSpace extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Good by heap...");
		Map m = new HashMap();
		while (true) {
			for (int i = 0; i < 10000; i++) {
				if (!m.containsKey(i)){
					Key key = new Key(i);
					m.put(key, "Number:" + i);
					out.println(m.get(key));
				}
			}
		}
	}

}
