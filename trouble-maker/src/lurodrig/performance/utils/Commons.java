package lurodrig.performance.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javassist.ClassPool;

import javax.servlet.http.HttpServletRequest;

import de.svenjacobs.loremipsum.LoremIpsum;
import lurodrig.perfomance.examples.WaitingOnIO;

public class Commons {

	public static Connection getConnection(String username, String password,
			String driver, String url) throws ClassNotFoundException,
			SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put(Constants.DB_CONNECTION_USER, username);
		connectionProps.put(Constants.DB_CONNECTION_PASSWORD, password);
		Class.forName(driver);
		conn = DriverManager.getConnection(url, connectionProps);
		return conn;
	}

	public static void sleep(String sleeper, long millis) {
		try {
			System.out.println(sleeper + " is having a little nap of " + millis
					/ 1000 + " seconds");
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static long getMillis(HttpServletRequest request) {
		long millis;
		try {
			millis = Long.valueOf(request.getParameter("millis"));
		} catch (NumberFormatException e) {
			millis = 30000;
			System.out.println("Request from "
					+ request.getHeader(Constants.USER_AGENT)
					+ " no millis parameter, set default millis=30000");
		}
		return millis;
	}

	public static Properties getProperties(String filePath) throws IOException {
		Properties properties = new Properties();
		Reader inStream = new BufferedReader(new FileReader(filePath));
		properties.load(inStream);
		return properties;
	}

	public static void print(PrintWriter out, Iterator it) {
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public static void printUsage(PrintWriter out) {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory
				.getOperatingSystemMXBean();
		out.print("\n");
		for (Method method : operatingSystemMXBean.getClass()
				.getDeclaredMethods()) {
			method.setAccessible(true);
			if (method.getName().startsWith("get")
					&& Modifier.isPublic(method.getModifiers())) {
				Object value;
				try {
					value = method.invoke(operatingSystemMXBean);
				} catch (Exception e) {
					value = e;
					e.printStackTrace();
				}
				out.println("\t" + method.getName() + " = " + value);
			}
		}
	}

	public static Class generate(String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		return pool.makeClass(name).toClass();
	}

	public static void writeAndSleep(String filePath, String sleeper,
			long millis) throws IOException {
		LoremIpsum loremIpsum = new LoremIpsum();
		File file = new File(filePath);
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < 1000; i++) {
			String words = loremIpsum.getWords(100);
			output.write(words);
			System.out.println(sleeper + " writing " + words.substring(0, 5)
					+ "...");
			Commons.sleep(WaitingOnIO.class.getName(), millis);
		}
		output.close();
	}
}
