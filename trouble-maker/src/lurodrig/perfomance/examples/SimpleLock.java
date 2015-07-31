package lurodrig.perfomance.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.utils.Commons;
import lurodrig.performance.utils.Constants;

/**
 * Servlet implementation class DeadlockServlet
 */
@WebServlet("/SimpleLock")
public class SimpleLock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String fileName = System.getProperty(Constants.TMP_DIR)
			+ Constants.IO_FILE;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleLock() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");

		String title ="SIMPLE DEAD LOCK";
		out.println("<title>" + title + "</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<p>THREAD-1 started writing to" + fileName
				+ ". Check your server output</p>");
		new Thread(new Runnable() {
			public void run() {
				try {
					Commons.writeAndSleep(fileName, "THREAD-1", 2000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "THREAD-1").start();
		out.println("<a href=\"SimpleDeadlockServletRead\">Read "+ fileName + " </a>");
	}

}
