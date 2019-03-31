接口: 
	是一个公共的模板，一种规范，是用来让别人在此模板上扩展的。
	interface
	接口是一种 特殊的抽象类
	
	* 接口中只有两个成员，常量和抽象方法
	-- 原因: 
		接口会为其中的变量提供默认的修饰符: public static final
		接口会为其中的方法提供默认的修饰符: public abstract


	* 接口中只有常量没有变量
	* 接口中为所有的变量都添加上了"public static final"
	int A = 4;	// 在接口当中这个被编译器解释为常量
	public static final int A = 4;	// 当前一行的代码与上一行的代码是一样的效果

	* 接口中的方法，只能是抽象方法
	* 接口中的方法都添加上了"public abstract"
	void test();
	public abstract void test();


写法:
	public interface 接口名 {
		// 常量
		// 抽象方法
	}

用法: 
	子类 implements 接口

注意点: 
	0、如果子类没有对接口中的所有抽象方法进行重写，那么此时这个子类就是一个抽象类。
	1、接口中没有构造方法；
	2、java中类的继承只有单继承，为了弥补单继承的不足，所以接口是可以多实现的。
		A implements D, E;	// 类可以同时继承自多个接口
	3、JAVA中不允许类的多继承，但是允许接口的多继承
		A,B,C 类	
		D,E,F 接口        D extends E,F	
	4、允许子类同时继承自父类和实现接口，但是extends在前面，implements在后面
		public class A extends B implements InterfaceC {
			
		}
	5、接口不可以实例化










































































