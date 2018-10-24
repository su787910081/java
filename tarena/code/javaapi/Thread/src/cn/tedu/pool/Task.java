package cn.tedu.pool;

public class Task implements Runnable {
	private int nIndex = 0;
	Task(int i) {
		nIndex = i;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ", [" + nIndex + "]开始执行...");
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
//			e.printStackTrace();
			System.out.println("线程被中断：[" + nIndex + "]");
		}
		
		System.out.println(Thread.currentThread().getName() + ", [" + nIndex + "]执行结束。。。");
	}
}
