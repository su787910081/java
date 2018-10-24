package cn.tedu.exception;

public class Demo {
	public static void main(String[] args) {
		// demo01();
		// demo02();
		demo03();
	}

	public static void demo01() {
		// test(1, 0);
		try {
			test2(1, 0);
		} catch (ArithmeticException e) {
			e.printStackTrace();
			System.out.println("程序出错了！");
		}
	}

	public static void demo02() {
		Person person = new Person();
		person = null;

		System.gc();
	}

	public static void demo03() {
		throw new UserNotFoundException("用户未找到异常！");
	}

	public static int test(int a, int b) throws ArithmeticException, NullPointerException {
		return a / b;
	}

	public static int test2(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("分母不能为0.");
		}

		return a / b;
	}
}
