package cn.tedu.reflact;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CopyToMap {
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Student student = new Student(1, 11, "aa");
		Map<String, Object> map = new HashMap<>();

		copy(student, map);
		System.out.println(map);
	}

	public static void copy(Object obj, Map<String, Object> map)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*
		 * 实现从obj 中复制数据到map 中
		 */
		Class cls = obj.getClass();
		// 查找该类中所有get 方法
		Method[] methods = cls.getDeclaredMethods();
		// 判断是否是get 方法
		for (Method m : methods) {
			String name = m.getName();
			if (name.startsWith("get")) {
				// 截取name 得到key
				name = name.substring(3);
				String first = name.substring(0, 1).toLowerCase();
				String last = name.substring(1);
				name = first + last;
				// 执行方法得到val
				Object val = m.invoke(obj);
				map.put(name, val);
			}
		}
	}
}
