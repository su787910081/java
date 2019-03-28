#Java 中的反射机制

## 普通反射的使用
- 通过类名创建一个对象: **<span style="color:red">无参构造</span>**
	
		Class<?> c = Class.forName("java.util.Date");
		java.util.Date d = c.newInstance();
- 通过对象创建一个对象: **<span style="color:red">带参构造</span>**

		Class<?> c = Class.forName("reflect.Point");
		reflect.Point p1 = c.newInstance();
		Constructor<?> con1 = c.getDeclaredConstructor(int.class, int.class);
		reflect.Point p2 = (Point)con1.newInstance(10, 20);

## 反射API
- `getDeclaredConstructors()` 获取所有的构造方法
	- 遍历所有的构造方法
		
			Class<?> c = Class.forName("reflect.Point");
			Constructor<?>[] cs = c.getDeclaredConstructors();
			for (Constructor<?> cc : cs) {
				// ...
			}
