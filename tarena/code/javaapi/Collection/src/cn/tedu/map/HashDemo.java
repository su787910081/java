package cn.tedu.map;

import cn.tedu.demo.Student;

/*
 * hashCode() - int Object 类中的一个方法：将对象在内存中的地址返回
 */
public class HashDemo {
	public static void main(String[] args) {
		demo01();
	}
	
	public static void demo01()
	{
		Student student = new Student();
		int addr = student.hashCode();
		System.out.println(addr);
		System.out.println(student);
	}
}
