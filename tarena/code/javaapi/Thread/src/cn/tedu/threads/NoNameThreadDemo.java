package cn.tedu.threads;

/**
 * 用匿名内部类创建两个线程对象并启动
 * 
 * @author suyh
 *
 */
public class NoNameThreadDemo {
	public static void main(String[] args) {
		// 创建两个线程对象
		Thread thd1 = new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				System.out.println("匿名创建Thread 对象1。");
			};
		};


		Thread thd2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("接口创建Thread 对象2。");
			}
		});
		
		thd1.setName("ThreadName");
		thd2.setName("ThreadName");
		thd1.start();
		thd2.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main 线程休眠2秒，已经结束。");

//		thd2.interrupt();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main 线程结束");
	}
}
