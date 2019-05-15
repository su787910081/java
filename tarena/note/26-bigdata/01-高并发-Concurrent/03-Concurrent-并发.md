

## Concurrent 
> - 并发
> - JDK1.5开始出现: 阻塞式队列、并发映射、线程池、锁、原子性操作。

- ## BlockingQueue - 阻塞式队列  - 继承自queue
    >> - 使用时需要指定容量，并且容量不可变；
    > 1. ### `ArrayBlockingQueue` 阻塞顺序队列
    >> - 必须指定容量
    >>> - queue.add("a");
    >>>> - 若队列 满，则抛出异常
    >>> - queue.offer("b");
    >>>> - 若队列满将返回false
    >>> - queue.offer("g", 1000, TimeUnit.MILLISECONDS);
    >>>> - 指定等待时间，若时间到了还不能正常添加，则返回false
    >>> - queue.put("c");
    >>>> - 直到插入成功为止
    > 2. ### `LinkedBlockingQueue` 阻塞链式队列
    >> - 可以不指定容量，但是默认容量为(2^31-1)，一般情况下都会自定义一个容量大小
    > 3. ### `PriorityBlockingQueue` 具有优先级的阻塞式队列
    >> - 所谓的优先级，是指它会对放入其中的元素进行排序
    >> - 如果是自定义类必须实现`Comparable` 接口，指定比较规则
    >> - 迭代遍历的时候不保证排序，只能通过普通for 循环进行遍历，才能得到排序的结果
    >> - 每次take 都会使得size() 的返回值少1 ，所以遍历的时候我们需要提前将数组大小取出来。
    > 4. ### `SynchronousQueue` 同步队列
    >> - 容量固定，且容量为1
    > 5. ### `BlockingDeque` 阻塞式双端队列(了解)

- ## ConcurrnetMap 并发映射 - 继承自map
    >> 1. `ConcurrnetHashMap`
    >>> - JDK 1.8，引入无锁算法CAS(Compare And Swap)
    >>> - 我认为V的值应该是A，如果是，那么将V的值更新为B，否则不修改并告诉V的值实际为多少
    >>> - 节点超过8 个扭转成红黑树，少于等于6 个时扭转成链表
    >>> - 当桶的数量达到64时才会将链式结构转换成红黑树
    >> 2. `ConcurrentNavigableMap` 并发导航映射 - 接口
    >>> - 提供了用于截取子映射的方法: `subMap` `headMap` `tailMap`
    >>> - 实现类: `ConcurrentSkipListMap` 并发跳跃表
    >>>> - 时间复杂度：O(logn)
    >>>> - 有序、不适合增删、最上层的表至少要有2到3个节点
    >>>> - 空间换时间
    >>>> - 新添元素时，这个元素是否要往上一层跳表提取遵循“抛硬币”原则

- ## 线程池
    > - 查看 `06-线程池.md`

- ## 锁

    - > `Lock` 接口类
        >> - `ReentrantLock` 实现类
        >> - fair 锁的公平与非公平策略
        > - `ReadWriteLock` 接口类
        >> - `ReentrantReadWriteLock` 实现类
        >> - 读锁加锁: readLock().lock();    unlock();
        >> - 写锁加锁: writeLock().lock();     unlock();

    - > `CountDownLatch` 闭锁/线程递减锁
        > - 使用
        >> - 指定一个总计数为6 的闭锁 `CountDownLatch cdl = new CountDownLatch(6);`
        >> - 阻塞当前线程，等待闭锁的计数归零 `cdl.await();`
        >>> - 阻塞的线程是总调度线程
        >> - 总计数减1 `cdl.countDown();`
        > - 与栅栏的区别：阻塞的是调度线程，所有的任务线程都在工作了之后调度线程才能做后续工作。
    - > `CyclicBarrier`  - 栅栏
        > - 使用
        >> - 构造一个计数为4 的栅栏: `yclicBarrier cb = new CyclicBarrier(4);`
        >> - 阻塞 且计数 -1 `cb.await();`
        >>> - 阻塞的线程是执行任务的线程，控制所有的任务线程在同一时刻放开运行
        >> - 当计数归零之后阻塞放开执行后续代码
        > - 与闭锁的区别：阻塞的是任务线程，所有的任务线程都准备好了之后，所有的任务线程一起开始工作。调度线程自由工作，不受影响。
    - > `Exchanger<T>` - 交换机，用于交换两个线程之间的信息
        > - 使用
        >> - 构造: `Exchanger<String> ex = new Exchanger<>();`
        >>> - 与另一线程交换的类型是String 
        >> - 交换方法: `String info = ex.exchange("货物");`
        >>> - 将`"货物"` 给另一线程，并等待对面交换过来的数据，通过返回值(`"info"`)来获取
    - > `Semaphore` - 信号量 计数 
        > - 每次占用一个计数，或者释放一个计数。当计数为0 时再来获取时将被阻塞，直到有计数被释放，它才能获取到。
        > - 使用
        >> - 构造，创建一个计数为5 的信号量: `Semaphore s = new Semaphore(5);`
        >> - 获取一个计数: `s.acquire();`
        >>> - 若当前计数为0，则阻塞
        >> - 释放一个计数: `s.release();`

    - >  扩展: `Condition`



- ## 原子操作
    > - `volatile`



