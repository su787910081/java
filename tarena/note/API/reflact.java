java 反射机制
	运行期检查类的信息：类的名字、属性、方法
	反射的好处：和未来的程序降低耦合
	
	java.lang.reflect.Field;
	
	Class cls = obj.getClass();
	// 获取类的所有成员变量
	Field[] fs = cls.getDeclaredFields();

	
反射的动态执行
	动态加载类到内存方法区
		Class.forName() 加载类到方法区  需要指定完整包路径
		Class cls = Class.forName("cn.tedu.reflact.Foo");
	
	动态创建对象
		newInstance() - Class
		Object obj = cls.newInstance();
		
	动态调用方法
		Object obj = cls.newInstance();
		Method[] methods = cls.getDeclaredMethods();
		
		// 遍历所有方法，判断方法是否以test 开头，如果是则执行此方法
		for (Method m : methods) {
			if (m.getName().startsWith("test")) {
				// 执行此方法
				Object val = m.invoke(obj);	// 参数一：该方法所在的对象, 参数二：参数列表 返回方法的返回值
			}
		}
	
	动态调用不可访问方法
		private 修饰的
		跨包访问
		
		在调用方法之前先调用方法 setAccessible 将不可访问的所有属性变为全可访问
		method.setAccessible(true);
	
	
	




	
	