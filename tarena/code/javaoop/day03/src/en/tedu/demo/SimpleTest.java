package en.tedu.demo;

public class SimpleTest {
	static {
		System.out.println("a");
	}
	
	public SimpleTest() {
		System.out.println("c");
	}
	
	public static void main(String[] args) {
//		new SimpleTest();
		Super s = new Sub();
		System.out.println(s.a);
		s.test();
	}
}

class Super {
	int a = 6;
	
	public void test() {
		System.out.println(a);
	}
}

class Sub extends Super {
	int a = 7;
	public void test() {
		System.out.print(a);
	}
}

