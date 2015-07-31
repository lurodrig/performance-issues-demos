package lurodrig.performance.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleTester {

	public static void main(String[] args) throws InterruptedException {
		List<A> list = new ArrayList<A>();
		int i = 0;
		while (true) {
			A a = new A();
			if (i % 2 == 1) {
				a.setColor("red");
			} else {
				a.setColor("green");
			}
			list.add(a);
			if (i % 100000000 == 0) {
				list.clear();
				System.out.println(new Date());
			}
			i++;
		}
	}
}

class A {
	private String color;

	public A(String color) {
		this.color = color;
	}

	public A() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
