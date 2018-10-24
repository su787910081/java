package cn.tedu.threads;

public class RunnableDemo implements Runnable {

	@Override
	public void run() {
		// хннЯ
		for (int i = 0; i < 10; ++i) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
//			System.out.println(i);
		}
	}

}
