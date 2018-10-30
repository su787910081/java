package en.tedu.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DemoBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> queue = 
				new LinkedBlockingQueue<String>(3);
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					String str = queue.take();
					System.out.println("take: " + str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(queue);
			}
		};
		thread.start();
		
		queue.put("1");
		queue.put("2");
		queue.put("3");
		System.out.println("开始放入元素");
		queue.put("4");
		System.out.println("放入成功");
	}
}
