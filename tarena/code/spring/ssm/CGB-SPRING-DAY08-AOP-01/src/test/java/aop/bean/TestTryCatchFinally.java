package aop.bean;

public class TestTryCatchFinally {
	static int doMethod01() {
		int a = 10;
		try {
			a++;
		} finally {
			return a;
		}
	}
	static int doMethod02() {
		int a = 10;
		try {
			return a;
		} finally {
			a++;
		}
	}
	
	static  int method03() {
        int a = 10;
        try {
            // 业务代码
        	int num = a / 0;
            return a;    // 如果核心业务出问题，则此切点将不会执行
        } catch(Exception e) {
            System.out.println("catch");
            throw new RuntimeException(e);
        } finally {
            ++a;
            System.out.println("finally");
            return a;
        }
    }
	
	public static void main(String[] args) {
		int result = method03();
		System.out.println(result);
	}
}
