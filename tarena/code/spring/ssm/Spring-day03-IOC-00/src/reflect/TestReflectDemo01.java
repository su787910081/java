package reflect;

import java.lang.reflect.Constructor;

class Point {
	private int x;
	private int y;
	
	public Point() {
		super();
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
	
	
}

public class TestReflectDemo01 {
	public static void main(String[] args) throws Exception {
		Class<?> c = Class.forName("reflect.Point");
		Point p1 = (Point)c.newInstance();
		Constructor<?> con1 = c.getDeclaredConstructor(int.class, int.class);
		Point p2 = (Point) con1.newInstance(10, 20);
		
		System.out.println(p1);
		System.out.println(p2);
	}
}
