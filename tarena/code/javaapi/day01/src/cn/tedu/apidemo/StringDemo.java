package cn.tedu.apidemo;

public class StringDemo {
	public static void main(String[] args) {
//		String s1 = "hello";
//		String s2 = "hello";
//		
//		/**
//		 * == 
//		 * 如果两边放的是数值类型，比较的是数值的值是否相等
//		 * 如果两边放的是引用类型，此时比较的是两个引用是否指向同一个对象
//		 * 
//		 */
//		System.out.println(s1 == s2);
//		
//		String s3 = "world";
//		String s4 = "helloworld";
//		String s5 = "hello" + "world";
//		// s4 与s5 是否指向同一个对象
//		System.out.println(s4 == s5);
//		
//		String s6 = s2 + s3;
//		System.out.println(s4 == s6);
		
		
		String str = "hhhhllo";
		int nIndex = str.lastIndexOf('l', 9);
		System.out.println(nIndex);
		nIndex = str.indexOf('l');
		System.out.println(nIndex);
	}
}
