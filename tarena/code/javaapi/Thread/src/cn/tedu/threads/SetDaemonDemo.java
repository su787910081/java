package cn.tedu.threads;
/**
 * 模拟后台线程
 * @author suyh
 *
 */
public class SetDaemonDemo {
	public static void main(String[] args) {
		// 创建 rose 前台线程
		Thread rose = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10; ++i){
					System.out.println("jack, help me!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("噗通！");
			};
		};
		
		// 创建如果台线程：jack
		Thread jack = new Thread() {
			@Override
			public void run() {
				while (true) {
					System.out.println("you jump, I jump!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			};
		};
		
		jack.setDaemon(true);
		
		System.out.println("rose: " + rose.isDaemon());
		System.out.println("jack: " + jack.isDaemon());
		
		rose.start();
		jack.start();
	}
}
