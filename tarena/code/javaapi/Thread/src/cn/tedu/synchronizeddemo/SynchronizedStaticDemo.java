package cn.tedu.synchronizeddemo;

/**
 * synchronized 可以用来修饰静态方法，此时这个静态方法的调用也是同步的。
 * 这里， 加锁的对象是当前类.class - Class
 * @author suyh
 *
 */
public class SynchronizedStaticDemo {
	public static void main(String[] args) {
		// 创建两个线程对象分别执行以下任务
		Thread thread = new Thread() {
			public void run() {
				StaticDemo.doSomething();
			}
		};
		
		Thread thread2 = new Thread() {
			public void run() {
				StaticDemo.doSomething();
			}
		};
		
		thread.start();
		thread2.start();
	}
}

class StaticDemo {
	public static synchronized void doSomething() {
		System.out.println(Thread.currentThread().getName() + "开始执行任务！");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "任务执行完毕！");
	}
}



