package cn.tedu.synchronizeddemo;

public class Test {
	public static void main(String[] args) {
		// 创建两个线程 对象
		MyThread myThread = new MyThread();
		
		Thread thread = new Thread(myThread, "线程A");
		Thread thread2 = new Thread(myThread, "线程B");
		
		thread.start();
		thread2.start();
	}
}
