package cn.tedu.demo;

import java.util.ArrayList;
import java.util.Collection;

public class Demo01 {
	public static void main(String[] args) {
		demoAdd();
		demoContains();
		demoStudentTest();
	}

	public static void demoAdd() {
		// 创建集合对象
		Collection c = new ArrayList();
		// 向集合中添加元素
		c.add(2);
		c.add("abc");
		System.out.println(c);
	}

	public static void demoContains() {
		Collection c = new ArrayList();
		c.add("java");
		c.add("php");
		c.add("c++");
		c.add(".net");
		
		String str = "java";
		boolean f = c.contains(str);
		System.out.println(f);
	}

	public static void demoStudentTest() {
		Collection c = new ArrayList();
		Student stu1 = new Student(1, "张三", 23);
		Student stu2 = new Student(1, "张三", 23);
		
		c.add(stu1);
		boolean bFlag = c.contains(stu2);
		System.out.println(bFlag);
		System.out.println(c);
	}
	
	public static void demo04() {
		// 错误，集合中不允许基本数据类型，只能是引用数据类型
		// collection<int> col = ArrayList<int>();
		// 泛型
		Collection<String> col = new ArrayList<String>();
		
		col.add("1");
	}
}
