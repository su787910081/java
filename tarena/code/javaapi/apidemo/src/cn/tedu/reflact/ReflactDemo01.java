package cn.tedu.reflact;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflactDemo01 {
	public static void main(String[] args) {
		test("aa");
//		test(1);
	}

	public static void test(Object obj) {
		System.out.println(obj);
		/*
		 * 在运行期判断出obj 对象的真实类型
		 */
		Class cls = obj.getClass();
		System.out.println(cls);
		
		// 获取类的成员变量
//		Field[] fields = cls.getDeclaredFields();
//		for (Field f : fields) {
//			System.out.println(f);
//		}
		
		// 获取类的方法
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m);
		}
	}
}
