


- ## Shuffle
    - > 这个数据重新打乱然后汇聚到不同节点的过程就是Shuffle

- ## Shuffle Write
    - > 数据持久化到文件
    - > Hash Based Shuffle Write
        > - 产生过多的Shuffle 文件(MapTask * ResultTask)
        > - 每打开一个文件需要一个线程，每一个线程都需要内存。
        > - 过多文件被打开，那么内存消耗过大
    - > Sort Based Shuffle Write
        > - Spark 1.2
        > - Spark 默认是不排序的。这里的Sort 是对MapTask 分区编号进行排序，而分区中的数据不排序。
        > - 排序的作用是为建立索引。
        > - 每一个MapTask 只生成一个Shuffle 文件，同时为这个文件生成索引
        > - 子RDD 来取自己的数据时，通过索引来读自己对应的数据


# SparkShuffle 调优
- ## 配置文件位置
    - > `spark-2.0.1-bin-hadoop2.7/conf/spark-defaults.conf`

- ## 配置文件参数
    - > `spark.shuffle.manager`
        > - 默认是`sort`
        > - 可以修改为: `spark.shuffle.manager=hash`
        > - 当产生的临时文件不是很多时，性能可能会比sort shuffle要好。
    - > `spark.shuffle.spill`
        > - 默认：`true`
        > - 用于指定Shuffle过程中如果内存中的数据超过阈值（参考`spark.shuffle.memoryFraction`的设置）时是否需要将部分数据临时写入外部存储
        > - 如果设置为false，那么这个过程就会一直使用内存，会有内存溢出的风险。因此只有在确定内存足够使用时，才可以将这个选项设置为false。
    - > `spark.shuffle.memoryFraction`
        > - 前置条件: `spark.shuffle.spill=true`
        > - 决定了当Shuffle过程中使用的内存达到总内存多少比例的时候开始spill
        >> - **这里的总内存并不是计算机的总内存，而是分配给这个任务的内存。**
        > - 在Spark 1.2.0里，这个值是0.2。此参数可以适当调大，可以控制在0.4~0.6。
        >> - 调大此参数之后，可以减少磁盘I/O次数
    - > `spark.shuffle.blockTransferService`
        > - 在Spark 1.2.0中这个配置的默认值是**netty**, 而在之前的版本中是nio
        > - 除非有特殊需求，否则采用默认配置即可。
    - > `spark.shuffle.consolidateFiles`
        > - 这个配置的默认值是false。
        > - 官方说了这个一起不稳定
    - > `spark.shuffle.compress` 和 `spark.shuffle.spill.compress`
        > - 这两个参数的默认配置都是true
        > - 都是用来设置Shuffle过程中是否对Shuffle数据进行压缩
        > - 前者(`spark.shuffle.compress`)针对最终写入本地文件系统的输出文件
        >> - 需要评估压缩解压时间带来的时间消耗和因为数据压缩带来的时间节省。
        >> - 如果网络成为瓶颈，比如集群普遍使用的是千兆网络，那么将这个选项设置为true可能更合理；
        >> - 如果计算是CPU密集型的，那么将这个选项设置为false可能更好。
        > - 后者(`spark.shuffle.spill.compress`)针对在处理过程需要写入到外部存储的中间数据，即针对最终的shuffle输出文件
        >> - 如果设置为true，代表处理的中间结果在spill到本地硬盘时都会进行压缩
        >> - 在将中间结果取回进行merge的时候，要进行解压。
        >> - 因此要综合考虑CPU由于引入压缩、解压的消耗时间和Disk IO因为压缩带来的节省时间的比较。
        >> - 在Disk IO成为瓶颈的场景下，设置为true可能比较合适；
        >> - 如果本地硬盘是SSD，那么设置为false可能比较合适。
    - > `spark.reducer.maxMbInFlight`
        > - 这个参数用于限制一个Result Task向其他的Executor请求Shuffle数据时所占用的最大内存数，默认是64MB。
        > - 尤其是如果网卡是千兆和千兆以下的网卡时。默认值是设置这个值需要综合考虑网卡带宽和内存。
        > - 一般64MB 足够用了。





