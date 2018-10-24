package cn.tedu.pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 7; i++) {
			Task task = new Task(i);

			service.execute(task);
		}

		System.out.println("½áÊø¡£");
		// service.shutdown();
		List<Runnable> ls = service.shutdownNow();
		System.out.println(ls.size());
	}
}
