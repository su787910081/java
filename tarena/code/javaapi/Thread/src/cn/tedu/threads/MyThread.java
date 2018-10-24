package cn.tedu.threads;

public class MyThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; ++i) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
//			System.out.println(i);
		}
	}
}
