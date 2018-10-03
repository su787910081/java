package cn.tedu.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.tedu.demo.Student;

public class ListSort {
	public static void main(String[] args) {
//		demo01();
		demo02();
		demo03();
	}

	public static void demo01() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(3);
		list.add(2);
		
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println(list);
	}
	
	public static void demo02()
	{
		List<String> list = new ArrayList<String>();
		list.add("c");
		list.add("a");
		list.add("z");
		list.add("d");
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}
	public static void demo03()
	{
		List<Student> lsStudent = new ArrayList<Student>();
		
		Student stu1 = new Student(1004, "张三", 21);
		Student stu2 = new Student(1001, "李四", 23);
		Student stu3 = new Student(1003, "王五", 22);
		lsStudent.add(stu1);
		lsStudent.add(stu2);
		lsStudent.add(stu3);
		
		System.out.println(lsStudent);
		
		ListSort ls = new ListSort();
		ByAge ba = ls.new ByAge();
		// 下面两个 效果是一样的，只是定义一个内部类的方式 不一样而以
		Collections.sort(lsStudent, new ListSort().new ByAge());
		Collections.sort(lsStudent, ba);
		
		System.out.println(lsStudent);
	}
	
	// 外比较器 Comparator
	// 实现学生类按年龄进行排序
	private class ByAge implements Comparator<Student>
	{
		@Override
		public int compare(Student o1, Student o2) {
			return o1.getAge() - o2.getAge();
		}
	}
}
