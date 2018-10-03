package cn.tedu.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoList {
	public static void main(String[] args) {
//		demo01();
		demo02();
		demo03();
	}
	
	public static void demo01()
	{
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("web");
		list.add("c++");
		
		String str = list.get(2);
		System.out.println(str);
		
		String original = list.set(1, ".net");
		System.out.println(original);
		System.out.println(list);
	}
	
	public static void demo02()
	{
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("web");
		list.add("c++");
		
		// 不可以将数组转换成String[] 类型，会抛类型转换异常
//		String[] strs = (String[])list.toArray();
//		System.out.println(strs);
		
		// toArray(T[] a) - T[] 
		String[] arys = list.toArray(new String[0]);
		System.out.println(Arrays.toString(arys));
	}
	
	public static void demo03()
	{
		// 数组转集合
		// Arrays static asList()
		String[] arys = new String[]{"a", "v", "c"};
		List<String> list1 = Arrays.asList(arys);
//		list1.add("d");	// 不支持该操作 不允许对转过来的List 进行增删操作
		list1.set(0, "test");	// 但是可以修改
		System.out.println(list1);
	}
}
