创建线程：
	得到当前线程的线程名称
	Thread.currentThread().getName();

	构建线程类方式一: 继承 Thread 类
		定义一个类继承自 Thread 类，此时这个类就是一个线程 类，我们创建程的目的是执行一个任务，所以在类中我们需要重写 Thread::run() 方法。
		JAVA 中线程的调度是抢占式的
		启动线程应该调用 start() 方法，而不应该直接调用 run() 方法。如果直接调用run() 方法，那么只是调用一个类的一个方法，而线程并没有真正运行起来。

	构建线程类方式二: 自定义类，实现 Runnable 接口
		虽然实现了Runnable 接口的 run() 方法，但该类并不是一个线程类。这里只是将线程 的任务创建出来了，还需要将该任务添加到一个线程类中才能创建一个真正的线程。
		{
			// 创建线程对象执行RunnableDemo 中的任务
			RunnableDemo demo = new RunnableDemo();
			
			Thread thd = new Thread(demo);
			thd.start();
		}

	直接采用第一种方法，偶合度将会比较高。我们通常使用第二种方法创建线程。
	还有一个就是继承只能是单继承，而实现接口我们可以实现多个接口。

	通常我们可以通过匿名内部类的方式创建线程，使用该方式可以简化编写代码的复杂度，当一个线程 仅需要一个实例时我们通常使用这种方式来创建。
		// 两种临时任务方式创建线程对象
		Thread thd1 = new Thread() {
			@Override
			public void run() {
				System.out.println("匿名创建Thread 对象1。");
			};
		};
		thd1.start();
		
		Thread thd2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("接口创建Thread 对象2。");
			}
		});
		thd2.start();


	线程操作API
		static Thread.currentThread() - Thread 获取当前线程对象
		static Thread.getName() - String 获取当前线程名称
		static Thread.sleep(long ms) - void 

		static yield() - void 礼让
			调用 yield() 方法，此时让当前线程并没有进入阻塞状态而是直接进入就绪状态，此时当前线程有可能会再次抢到时间片。

		给线程起名字
			setName(String name) - void
			Thread(Runnable, String name);	构造方法

		获取线程相关信息方法：
			long getId(): 线程标识符
			String getName();
			Boolean isAlive();
			int getPriority(); 	优先级
			boolean isDaemon(); 守护线程
			boolean isInterrupted(); 是否已中断


局部内部类调用方法的局部变量的注意点：
	main() {
		// final int a = 3; // 1.8 版本之前要使用局部变量必须是final 修饰的
		int a = 3;	// 1.8 版本就没有此限制
		class A {
			public void test() {
				System.out.println(a);
			}
		}
	}

线程优先级：
	线程的切换是由线程调度控制的，我们无法通过代码来干涉，但是我们可以通过提高线程的优先级来最大程度的改善线程获取时间片的几率。
	线程的优先级被划分为10 级，值分别是1-10 ，其中1最低，10 最高。线程提供了3 个常量来表示最低，最高，以及默认优先级：
		Thread.MIN_PRIORITY,
		Thread.MAX_PRIORITY,
		Thread.NORM_PRIORITY,
	设置优先级的方法: 
		setPriority(int prority);

守护线程：
	守护线程与普通线程在表现上没有什么区别，我们只需要通过Thread 提供的方法来设定即可：
		void setDaemon(boolean)
	守护线程与普通线程唯一的区别是在结束的时机上。当一个进程中所有前台线程都结束时，进程结束，无论该进程中的守护线程是否还在运行都要强制将它们结束。
	GC 就是一个守护线程。







线程同步
	实现方式:
		关键字: synchronized: 同步锁
	锁机制:
		JAVA 提供了一种内置的锁机制来支持原子性：
		同步代码块(synchronized 关键字)，同步代码块包含两个部分：一个作为锁的对象的引用，一个作为由这个锁保护的代码块。
			synchronized 修饰方法:
			// 如果用于修饰方法，那么给调用这个方法的对象(方法所属对象 this)加锁。
			// 也就是说所有这个类的对象实例(都是同一个实例)访问这个方法都会被锁同步
			示例：
			public synchronized void test() {
				
			}
			synchronized (同步监视器--锁对象引用) {
				// 代码块
				// ...
			}
			// 一般都用this 作为监视器
			示例：
			synchronized (this) { }

		若方法所有代码都需要同步可以给方法直接加锁。
		每个JAVA 对象都可以胳膊做一个实现同步的锁，线程进入同步代码块之前会自动 获得锁，并且 在退出同步代码块时自动 释放锁，而且无论是通过正常途径退出还是通过抛异常退出都一样，获取内置锁的唯一途径就是进入由这个锁保护的同步代码块或者方法
		synchronized 修饰代码块
			有效缩小同步范围可以在保证并发安全的前提下提高并发执行效率。
			同步块，同步块可以更精确的控制需要同步的代码片段

		使用 synchronized 需要对一个对象上锁以保证线程同步。那么这个锁对象应当注意: 
			* 多个需要同步的线程在访问该同步块时，看到的应该是一个锁对象引用。否则达不到同步效果。
			* 通常我们会使用this 来作为锁对象。

		静态方法使用 synchronized 修饰后，该方法一定具有同步效果
			synchronized 修饰静态方法，添加锁的对象是当前类.class - Class 对象
			示例：
			public static synchronized void doSomething() { 
				// do something
			}
		
		synchronized 的互斥性
			synchronized 修饰多段代码，但是这些同步块的同步监视器对象是同一个时，那么这些代码间就是互斥的。调用这些多个线程不能同时执行，必须等待。
			class A {
				public synchronized void methodA() { }
				public synchronized void methodb() { }
			}
			以 A 为例，若线程1 调用 methodA 而此时线程2 调用 methodB 那么这两个线程也存在一个锁的竞争。


线程安全API
	StringBuilder 是非线程安全的， StringBuffer 是线程安全的
	对于集合而言，常用的实现类; ArrayList, LinkedList, HashSet 它们都不是线程安全的。
	Collections 可以将现有的集合转换为线程安全的
		- Collections.synchronizedList(List)
		- Collections.synchronizedSet(Set)
		- Collections.synchronizedMap(Map)

线程池
	ExecutorService(返回接口) Executors.newFixedThreadPool(int nThreads); - ExecutorService(接口)

	将任务提交给线程池
		execute(Runnable) - Executor
	
	关闭: 
		shutdown()
		shutDownNow()
























