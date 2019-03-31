package reflect;

import java.lang.reflect.Method;
import java.util.Scanner;

public class TestReflectDemo01 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please input class: ");
		String pkgcls = sc.nextLine();
		System.out.print("Please input method: ");
		String methodName = sc.nextLine();
		
		// 1. 构建类对象
		Class<?> c = Class.forName(pkgcls);
		// 2. 获取类中方法对象
		Method m = c.getDeclaredMethod(methodName);
		// 3. 执行类的对象的方法
		Object obj = c.newInstance();
		
		Object result = m.invoke(obj);
		System.out.println(result);
		
		sc.close();
	}
}
