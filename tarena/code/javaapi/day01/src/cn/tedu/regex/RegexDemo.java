package cn.tedu.regex;

public class RegexDemo {
	public static void main(String[] args) {
//		String regex = "[\\d]";
		String regex1 = "1[34578]\\d{9}";
		String phone = "14567898765";
		boolean b1 = phone.matches(regex1);
		System.out.println(b1);

	}
}