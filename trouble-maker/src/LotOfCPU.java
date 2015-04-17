import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LotOfCPU
 */
@WebServlet("/LotOfCPU")
public class LotOfCPU extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		long start = System.currentTimeMillis();
		long end = start + Utils.getMillis(request);
		Map<String, Double> logarithms = new HashMap<String, Double>();
		long currentTime = System.currentTimeMillis();
		while (currentTime < end) {
			logarithms.put(
					"Logarithm (base e) of " + System.currentTimeMillis()
							+ ": ", Math.log(currentTime));
			currentTime = System.currentTimeMillis();
		}
		Iterator it = logarithms.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}
