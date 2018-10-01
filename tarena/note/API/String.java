String 是不可变的对象，本质是字符数组

String 对象的创建 - 2种方法
	方法一：
		String str = new String();
		String str = new String("abc");

	方法二：
		// "" 双引号引起来的叫字符串直接量，字面值
		// 对象的存储位置：字符串常量池
		// -- JDK 1.7 之前在方法区，之后在堆中
		// 字符串常量池保存字符串直接旱
		// 	 特点：字符串常量池中保存的是字符串直接量，保存的对象是唯一存在的
		//		创建String 直接量对象时先去找这个值是否存在，如果存在直接让引用指向它，如果不存在则创建
		String str = "hello";	// 创建了一个String 对象
		
		String s1 = new String("abc");
		String s2 = "abc";
		assert(s1 != s2);

		// 可能创建2 个对象
		//	1. "abc" 字符串常量
		// 	2. new String()
		String str = new String("abc");




