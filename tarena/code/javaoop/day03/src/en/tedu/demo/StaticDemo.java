package en.tedu.demo;

public class StaticDemo {
	static {
		System.out.println("静态代码块。");
	}
	
	public static void demo01() {
		System.out.println("调用静态方法demo01() 方法.");
	}
	
	public void demo02() {
		System.out.println("调用非静态方法demo02() 方法.");
	}
}
