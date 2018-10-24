package cn.tedu.threads;

public class Demo {
	public static void main(String[] args) throws InterruptedException {
//		demo01();
		demo02();
	}

	public static void demo01() {
		MyThread thd = new MyThread();
		thd.start();
		// thd.run(); // 不应该直接调用 run() 方法，而应该调用start() 方法，这样才是真正的启动线程，否则只是调用方法而以
	}
	
	public static void demo02() {
		// 创建线程对象执行RunnableDemo 中的任务
		RunnableDemo demo = new RunnableDemo();
		
		Thread thd = new Thread(demo);
		
		thd.start();
	}
}
