package lurodrig.perfomance.examples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lurodrig.performance.examples.deadlock.Friend;

/**
 * Servlet implementation class DeadlockServlet
 */
@WebServlet("/ClassicDeadlockServlet")
public class ClassicDeadlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassicDeadlockServlet() {
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
		String name1 = request.getParameter("friend1") != null ? request
				.getParameter("friend1") : "Alphonse";
		String name2 = request.getParameter("friend2") != null ? request
				.getParameter("friend2") : "Gaston";
		out.println("TWO FRIENDS: " + name1 + " & " + name2);
		PrintWriter console = new PrintWriter(System.out);
		final Friend friend1 = new Friend(name1, console);
		final Friend friend2 = new Friend(name2, console);
		out.println(name1 + " starts bowing " + name2 + ". Check your server output");
		new Thread(new Runnable() {
			public void run() {
				friend1.bow(friend2);
			}
		}, name1.toUpperCase() + "-THREAD").start();
		out.println(name2 + " starts bowing " + name1 + ". Check your server output");
		new Thread(new Runnable() {
			public void run() {
				friend2.bow(friend1);
			}
		}, name2.toUpperCase() + "-THREAD").start();
	}

}
