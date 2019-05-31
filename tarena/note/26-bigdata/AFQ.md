
- ## hashmap 中的桶，以及扩容?
    - > HashMap 中有一个数组，这个数组存放的是一个链表类型的元素，而这个链表类型的元素，就被 称为桶
        >> - 每一个桶是一个链表，这个链表中的元素就是HashMap 的实际元素(Key-Value对)。
        >>> - 原来HashMap 中桶的容量就是这个HashMap 的最大容量size。如果一个桶里面只存放一个元素那么刚刚好。但是如果出现hash碰撞，那么就一定有桶里面存放了不只一个元素且一定肯桶里面是空的。
        >> - HashMap 会有一个加载因子，当map 中的实际元素个数大于了  ？？？？？？？
        >>> - HashMap 的原始想法是一个数组中每个元素以KEY 的HASH 值对桶的数量求余，这个余值作为下标来查找对应的位置，但是这样做肯定会存在hash碰撞，这样在一个桶里面就可能出现多个元素。所以桶的数量就是这个MAP 当前的最大容量。这里的扩容就是当实际的数量达到了MAP 的当前容量与加载因子的积，那么这个MAP 就会扩容，按当前容量的双倍进行扩容。

    - > HashMap 中链表与红黑树转换时机
        >> - 当桶的数量达到64(枚举)个，且当前链表中元素的个数达到8(枚举)。这两个条件同时满足时那么这个链表就会转换成红黑树结构。
        >> - 当链表是红黑树结构且个数降到6(枚举) 个时，那么它就会被 转换成链表结构

- ## ZooKeeper
    - > ZooKeeper 中的临时节点在什么情况下会被 移除。这个移除是由ZooKeeper 来完成的吗?
        > - 临时节点的生命周期和客户端会话绑定在一起，客户端会话失效，则这个节点就会被自动清除。

- ## Hadoop
    - > 在Hadoop 中 NameNode 切块是按128MB 大小进行切块的。而MapReduce 默认是按行进行处理的。显然在很多情况下都不是刚好分到换行符，那么被切到两个块中的一整行数据Hadoop 中的MapReduce  是怎么处理这个问题的呢？
        - > 我的猜测
            > - 1. 非第一个MapTask 都将获取到的第一行数据分出来，通过网络的形式传输给上一个块所对应的MapTask 让它去处理。
            > - 2. 或者前一个将最后的数据转给下一个块所对应的MapTask

    - > MapTask 产生的多个分区，是放在同一个文件中还是为每一个分区创建一个文件？
        > - 所有的分区合到一个结果文件中。

    - > MapReduce
        - > 2.0 与1.0 相比 到底是TaskTracker 通过心跳去取任务还是在2.0 中由ApplicationMaster 往下分发任务？？？


    - > Yarn 中一个Job 所需要的Container 资源由谁计算出来的？
		> - 但是这个Container 对象是由ApplicationManager 来计算的，因为所有的资源都由它来管理。
		> - 一个Job 对应一个ApplicationMaster 这个ApplicationMaster 的其中一个作用就是需要计算它所对应的Job 会产生多少个Task, 这一个Task 就会被封装成一个Container 对象
		> - ApplicationMaster 会将Task 的数量上传给ApplicationManager，ApplicationManager 会跟Task 的数量以及对应的分片所在的DataNode 来分配资源，这个资源就被封装成一个Container 对象



- ## HBase
    - > HBase 完全分布式环境中，为什么在任意一台主机上启动HBase 那么所有的HBase 的HRegionServer 都会被启动呢，它这个是通过什么实现的？
    - > 在完全分布式HBase 时。为什么在任意一个节点上面启动 sh start-hbase.sh 在其他的节点上面的HRegionServer 进程也会运行呢？它这个是怎么实现的呀？
        > - ZooKeeper ?
    - > 写文件时，列存储是一行写完接着写下一行还是中间有间隔呢？






- ## kafka
    - > 在有三个KAFKA broker 三个ZooKeeper 节点的环境下。我使用代码往kafka 的某一个主题里面插入了数据, 从 0 ~ 99 100 个数字，但是我使用消费者客户端连接到KAFKA 服务器上，却只收到一半的数据(全为奇数据 或者全为偶数)。
        > -  客户端 连接使用的命令如下
        >> - `sh kafka-console-consumer.sh --zookeeper HBase01:2181,HBase02:2181,HBase03:2181 -topic  jpbook  --from-beginning`



