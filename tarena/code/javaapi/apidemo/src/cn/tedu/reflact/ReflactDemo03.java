package cn.tedu.reflact;

import java.util.Scanner;

public class ReflactDemo03 {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// 加载Foo 类 -- 类名由用户从控制台输入
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入类名: ");
		String className = sc.nextLine();
		
		Class cls = Class.forName(className);
		System.out.println(cls);
		
		// 创建Foo 对象
		Object obj = cls.newInstance();
		
		System.out.println(obj);
	}
}

class Foo {
	public void test() {
		System.out.println("Foo 类中的测试方法");
	}
}
