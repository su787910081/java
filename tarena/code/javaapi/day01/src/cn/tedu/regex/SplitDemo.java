package cn.tedu.regex;

import java.util.Arrays;

/**
 * split(String regex) - String[]
 * 根据给定的正则表达式作为分隔符，
 * @author suyh
 *
 */
public class SplitDemo {
	public static void main(String[] args) {
		demoSplit();
		demoSplitAll();
		demoChines();
	}
	
	public static void demoSplit()
	{
		String str = "java,php, c++,   end";
		String[] arr = str.split(",\\s*");
		System.out.println(Arrays.toString(arr));
	}
	
	public static void demoSplitAll()
	{
		String str = "java123php8c++34net";
		String strRes = str.replaceAll("\\d+", "数字");
		System.out.println(strRes);
	}

	public static void demoChines()
	{
		String name = "张三";
		String regex = "[\u4e00-\u9af5]{2,4}";
		boolean flag = name.matches(regex);
		System.out.println(flag);
	}
}
