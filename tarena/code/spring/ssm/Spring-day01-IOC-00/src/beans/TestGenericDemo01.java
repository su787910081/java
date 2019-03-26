package beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestGenericDemo01 {
	public static void main(String[] args) throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		// list.add(100);
		
		Class<?> c = list.getClass();
		// 获取Method 对象
		Method m = c.getDeclaredMethod("add", Object.class);
		m.invoke(list, 100);
		
		
	}
}
