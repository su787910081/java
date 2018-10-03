package cn.tedu.regex;

public class RegexDemo {
	public static void main(String[] args) {
//		String regex = "[\\d]";
		String regex1 = "1[34578]\\d{9}";
		String phone = "14567898765";
		boolean b1 = phone.matches(regex1);
		System.out.println(b1);

		phoneRegex();
		phoneRegex_02();
		demo03();
	}
	
	
	// 手机号码的正则表达式
	/*
	 * 	检索手机号码：+86 13838389438
	 * - +86 可有可无
	 * - +86 与后面的号码之间空格可以没有或者有多个
	 * - 电话号码为11 位数字
	 */
	public static void phoneRegex() {
		// 正则表达式
		String regex = "(\\+86)?\\s*1[3578]\\d{9}";
		// 手机号
		String phone = "+86 13567890876";
		// 二者进行匹配
		boolean flag = phone.matches(regex);
		System.out.println(flag);
	}
	
	public static void phoneRegex_02() {
		// 正则表达式
		String regex = "(\\+86|0086)?\\s*1[3578]\\d{9}";
		// 手机号
		String phone = "0086 13567890876";
		// 二者进行匹配
		boolean flag = phone.matches(regex);
		System.out.println(flag);
	}
	
	public static void demo03()
	{
		String regex1 = "\\w{8,10}";
		String regex2 = "^\\w{8,10}$";
		
		String text = "hello_world";
		
		// matches 自带功能，匹配开头和结尾
		boolean bFlag = text.matches(regex1);
		System.out.println(bFlag);
	}
}
