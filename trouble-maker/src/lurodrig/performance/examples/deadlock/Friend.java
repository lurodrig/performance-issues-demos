package lurodrig.performance.examples.deadlock;

import java.io.PrintWriter;

public class Friend {

	private final String name;
	private final PrintWriter out;

	public Friend(String name, PrintWriter out) {
		this.name = name;
		this.out = out;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Friend bower) {
		out.format("%s: %s" + "  has bowed to me!%n", this.name,
				bower.getName());
		out.flush();
		bower.bowBack(this);
	}

	public synchronized void bowBack(Friend bower) {
		out.format("%s: %s" + " has bowed back to me!%n", this.name,
				bower.getName());
		out.flush();
	}
	
	
}
