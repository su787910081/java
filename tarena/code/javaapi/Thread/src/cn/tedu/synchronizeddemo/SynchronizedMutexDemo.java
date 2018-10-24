package cn.tedu.synchronizeddemo;

public class SynchronizedMutexDemo {
	public static void main(String[] args) {
		// 创建两个 线程对象一个执行methodA 一个执行methodB 方法
		// 线程同时启动
		TestMutex test = new TestMutex();
		
		Thread thread1 = new Thread(){
			public void run() {
				test.syncA();
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
				test.syncB();
			}
		};
		
		thread1.start();
		thread2.start();
	}
}

class TestMutex {
	public synchronized void syncA() {
		methodA();
	}
	public synchronized void syncB() {
		methodB();
	}
	
	public void methodA() {
		System.out.println("A方法开始执行...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A方法执行结束！");
	}
	
	public void methodB() {
		System.out.println("B方法开始执行...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("B方法执行结束！");
	}
}
