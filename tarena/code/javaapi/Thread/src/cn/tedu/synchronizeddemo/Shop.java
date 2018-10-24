package cn.tedu.synchronizeddemo;

public class Shop implements Runnable {

	@Override
	public void run() {
		run1();
//		run2();
	}

	// 对整个方法加锁
	synchronized void run1() {
		System.out.println(Thread.currentThread().getName() + "正在选衣服");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "已选好衣服");

		System.out.println(Thread.currentThread().getName() + "正在试衣服");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "试衣结束");
	}

	// 针对某一代码块加锁
	void run2() {
		System.out.println(Thread.currentThread().getName() + "正在选衣服");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "已选好衣服");

		// // 针对某一代码块加锁
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + "正在试衣服");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "试衣结束");
		}
	}
}
