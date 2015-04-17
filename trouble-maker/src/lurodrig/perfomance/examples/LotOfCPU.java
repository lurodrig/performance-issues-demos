package lurodrig.perfomance.examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Commons;

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
		Commons.printUsage(out);
		long start = System.currentTimeMillis();
		long length = Commons.getMillis(request);
		long end = start + length;
		long currentTime = System.currentTimeMillis();
		long iterations = 0;
		while (currentTime < end) {
			String data = "";
			for (int i = 0; i < length; i++) {
				data += UUID.randomUUID().toString();
			}
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			digest.update(data.getBytes());
			currentTime = System.currentTimeMillis();
			iterations++;
		}
		out.println("Number of iterations: " + iterations);
		Commons.printUsage(out);
	}
}
