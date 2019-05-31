

# HBase 物理存储
- ## HTable 表
    - > 一个HTable 表按行键化分出多个HRegion 

- ## HRegion
    - > HRegion是一个Table 中的一个Region 在一个HRegionServer 中的表达
    - > HRegion是Hbase中分布式存储和负载均衡的最小单元
        > - 也就是说HRegion 中可能包含很多个小单元，这些单元可以单独作为一个文件进行存储。
        > - 但是，这些多个小单元必须作为一个整体(HRegion)存储在一个物理机上面。
        > - HMaster 负责对HRegion 的负载均衡
    - > HRegion 默认的最大大小为10GB
        > - 这个大小值的由配置文件(`hbase-site.xml`)的`hbase.hregion.max.filesize`属性指定
        > - HRegion 的大小达到阈值的时候将会被拆分并做转移，这个工作由HMaster 自动完成，用户没有感知
    - > HRegion 中记录了StartKey 和EndKey，为了快速查找定位

- ## HStore
    - > 一个HRegion 中的一个列族的数据存储到一个HStore
        > - 因而最好将具有相近I/O特性的Column存储在一个Column Family，以实现高效读取(数据局部性原理，可以提高缓存的命中率)

- ## MemStore (写缓存)
    - > 每一个HStore 有一个MemStore，默认是128M。这个与DataNode 的块大小一样。
    - > 在MemStore 中会根据RowKey、Column Family、Columng正序以及TimeStamp 倒序排序。
    - > 所有数据的写操作，在完成WAL 日志写后，才会被写入MemStore 中
    - > MemStore 中的数据在达到一定条件(三个条件)时会一次性冲刷到磁盘中。
        > - 数据一次性冲刷到磁盘(顺序写)，高性能写。
        > - 1. MemStore 超过了`hbase.hregion.memstore.flush.size`(默认是128MB) 
        >> - 这个值刚好对应一个HDFS 中Block
        > - 2. HRegionServer 中所有的MemStore 大小之和超过了(物理机总内存的)35%
        >> - 这个阈值配置属性为: `hbase.regionserver.global.memstore.upperLimit`
        >> - 按MemStore 使用的内存大小，将多的优先刷新到磁盘。并非所有都冲刷到磁盘。
        > - 3. 当HRegionServer 中的WAL 文件的大小超过了1GB
        >> - 1GB = `hbase.regionserver.hlog.blocksize`(32MB) * `hbase.regionserver.max.logs`(32)
    - > MemStore 根据一定的算法(LSM-TREE)将数据 flush 到HStoreFile(HFile) 中
        > - The Log-Structured Merge-Tree

- ## HStoreFile(HFile)
    - > <mark>HRegion是分布式存储的最小单位，StoreFile(HFile)是存储的最小单位</mark>
    - > 在HFile中的数据是按RowKey、Column Family、Column排序，对相同的Cell(即这三个值都一样)，则按timestamp倒序排列
    - > 通过索引(类B + Tree)机制 +Bloom 过滤器加快查找速度
    - > HFile 的尾部会存储一些meta数据，其中就有MemStore 冲刷时的最大WAL sequence值。

- ## BlockCache 读缓存
    - > 一台HRegionServer 共用一块BlockCache
    - > 引用局部性原理，每次存储一片数据
        > - 分空间局部性和时间局部性
        > - 空间局部性是指CPU在某一时刻需要某个数据，那么有很大的概率在一下时刻它需要的数据在其附近；
        > - 时间局部性是指某个数据在被访问过一次后，它有很大的概率在不久的将来会被再次的访问
        > - 这样设计的目的是为了提高读缓存的命中率
    - > HBase中默认采用LRU 算法来回收缓存数据
        > - LRU -evicted，是一种数据的回收策略
        > - LRU– 最近最少使用的：移除最长时间不被使用的对象。

- ## WAL (Write Ahead Log)  早期版本中也叫HLog
    - > 一个日志文件，用于存储所有的写操作记录
        > - 所有的写操作都会先存储到这个文件，然后才会到MemCache
    - > 它最主要的作用是数据恢复
        > - 当HRegionServer 宕机之后数据操作可以从WAL 文件中恢复
    - > 它是HDFS上的一个文件
        > - HLog文件就是一个普通的Hadoop Sequence File，Sequence File 的Key是HLogKey对象，HLogKey中记录了写入数据的归属信息，除了table和region名字外，同时还包括 sequence number和timestamp，timestamp是”写入时间”，sequence number的起始值为0，或者是最近一次存入文件系统中sequence number。HLog Sequece File的Value是HBase的KeyValue对象，即对应HFile中的KeyValue。
        > - 这个Log文件会定期Roll出新的文件而删除旧的文件(那些已持久化到HFile中的Log可以删除)。WAL文件存储在/hbase/WALs/${HRegionServer_Name}的目录中(在0.94之前，存储在/hbase/.logs/目录中)，一般一个HRegionServer只有一个WAL实例，也就是说一个HRegionServer的所有WAL写都是串行的(就像log4j的日志写也是串行的)。一个RS服务器只有一个HLOG文件，在0.94版本之前，写HLOG的操作是串行的，所以效率很低，所以1.0版本之后，Hbase引入多管道并行写技术，从而提高性能。

# 逻辑进程
- # HRegionServer 服务器进程
    - > HRegionServer 包含WAL(HLog)、BlockCache、MemStore、HFile组成
    - > 一个HRegionServer 中管理着多个HRegion存储单元
    - > 一般情况下HRegionServer 节点跟DataNode 节点放在一台物理计算机上(数据本地化策略)
    - > 它负责读写HDFS，管理Table中的数据。
    - > Client直接通过HRegionServer读写数据
        > - Client 从HMaster中获取元数据，找到RowKey 所在的HRegion/HRegionServer后直接从HRegionServer 读取数据。
    - > 一个HRegionServer 可以存放1000(来自于Google 的BigTable 论文) 个HRegion

- # HMaster 服务进程
    - > HMaster 是HBase 中的管理节点
    - > HMaster 没有单点故障问题，它可以有多个back-up 节点。
        > - 一般我们只会启用两个。
        >> - 按照启动顺序第一个作为Active HMaster ，后一个作为back-up HMaster
        >> - 这样可以减少实时热备带来的网络开销。
    - > 负责管理HRegionServer, 实现其负载均衡
        > - 通过ZooKeeper 来监控集群中的所有HRegionServer 的状态。
        > - 使用心跳机制与ZooKeeper 通信。
    - > 管理和分配HRegion
        > - 在HRegion 拆分后，将新的HRegion 单元分配新的HRegionServer 节点上；
        > - 在HRegionServer退出时迁移其内的HRegion到其他HRegionServer上。
    - > HMaster 在启动之后会自动在ZooKeeper 上注册一个临时节点 `/hbase/master`
        > - back-up HMaster 注册一个临时节点`/hbase/backup-masters`
        > - HMaster 定期向ZooKeeper 来发送心跳，维护这个临时节点，心跳时间默认是180s
    - > 实现DDL操作
        > - DDL(Data Definition Language)，namespace和table的增删改，column familiy(列族)的增删改等。
    - > 管理namespace和table的元数据（实际存储在HDFS上）。
    - > 权限控制（ACL）。

- # HBase 的ZooKeeper 集群(协调者)
    - > 管理着HMaster和HRegionServer的状态
        > - 在 Active HMaster 宕机时，将Back-up HMaster 提升为Active
        > - 在HRegionServer 宕机时，通知HMaster 做HRegion 数据的恢复。
    - > ZooKeeper 存储Active HMaster 的一个临时节点(`/hbase/master`)
    - > ZooKeeper 存储Back-up HMaster 的临时节点(`/hbase/backup-masters`)
    - > ZooKeeper 存储HRegionServer 的临时节点(`/hbase/rs`)
    - > 存放整个 HBase集群的元数据
        > - HBase 集群的元数据文件所在HDFS 上的位置存放在
    - > 存放HBase 集群的状态信息。
    - > 存放HRegionServer服务器的运行状态
    - > 实现HMaster主备节点的failover。


# 技术点

- # HBase 内部通信
    - > HBase Client 通过RPC 方式和HMaster、HRegionServer 通信；


- # HBase 的第一次读写
    - ## 0.96 前的老版本
        - > Client 从ZooKeeper 中读取-ROOT- Table 所在的HRegionServer
            > - -ROOT- Table 中存的是.META. Table 的位置
        - > 然后从该HRegionServer 中请求TableName, RowKey 读取.META. Table 所在HRegionServer
            > - 老版本的.META. Table 有多个，散落在各个HRegionServer 节点上
            > - .META. Table 中存储的是元数据
            >> - 1、表名；
            >> - 2、此表被切分成几个HRegion；
            >> - 3、每个HRegion的StartKey；
            >> - 4、每个HRegion所归属的HRegionServer
        - > 最后从该 HRegionServer 中读取.META. Table 的内容，从而获取此交请求需要访问的HRegion 所在的位置
        - > 然后第四次请求开始获取真正的数据。

    - ## 0.96 之后的版本
        - > 砍掉了 -ROOT- Table 表
        - > .META. Table 表只有一个，存储在某一节点上面
        - > .META. Table 表的位置存储在ZooKeeper 的`/hbase/meta-region-server` 上
        - > 客户端会缓存 .META. Table 的元数据信息
            > - 这样下次再去访问时就不需要到ZooKeeper 中获取了。
            > - 如果数据发生变化或者HRegionServer 宕机以及HRegion 发生切分。客户端才会再次去访问ZooKeeper

    - ## 整理
        - > HBase 表数据是存到HFile ，再存储到HDFS 中
        - > ZooKeeper 存储的是HBase 元表(.META.) 的位置信息
        - > .META. 表是HBase 的元数据信息，存到某一台HRegionServer 节点上。
            > - 新版本的 .META. 表只存在一个(不切分)


- # HBase 写流程
    - > 客户端 发起put 请求
        > - 首先从hbase:meta 表中查出该 put 操作最终需要去的HRegionServer。
        > - 然后将put 请求发送给相应的HRegionServer
        >> - 在HRegionServer中它首先会将该Put操作写入WAL日志文件中(Flush到磁盘中)。
        >> - 然后会将数据写到Memstore，在Memstore按Rowkey排序以及用LSM-TREE对数据做合并处理
        > - HRegionServer根据Put中的TableName和RowKey找到对应的HRegion
        > - 根据Column Family找到对应的HStore，最后将Put写入到该HStore的MemStore中
        >> - 在写入之前还有一步操作就是先将写操作写入WAL
        > - 最后返回写入结果


- # HBase 读流程
    - > 一个数据当前可能在HBase 中的三个位置
        > - BlockCache(读缓存)、MemStore(写缓存)、StoreFile(HFile)


- # Compaction 机制
    - > MemStore每次Flush会创建新的HFile，而过多的HFile会引起读的性能问题
        > - Compaction 机制的引入就是解决这个问题的。

    - ## Minor Compaction
        - > 选取一些小的、相邻的StoreFile将他们合并成一个更大的StoreFile
            > - <mark>在这个过程中不会处理已经Deleted或Expired的Cell</mark>
        - > 一次Minor Compaction的结果是更少并且更大的StoreFile。
        - > 操作
            > - API: `admin.compact("tab2".getBytes());`
            > - 命令: `hbase(main):0800:0>  compact('tab2')`

    - ## Major Compaction
        - > 将一个HStore 中的所有HFile 文件合并成一个HFile 
        - > 将所有的StoreFile合并成一个StoreFile
            > - <mark>标记为Deleted的Cell会被删除</mark>
            > - <mark>已经Expired的Cell会被丢弃</mark>
            > - <mark>已经超过最多版本数的Cell会被丢弃</mark>
        - > Major Compaction可以手动或自动触发
            > - 它会引起很多的I/O操作而引起性能问题
            > - 一般会安排在周末、凌晨等集群比较闲的时间做这个操作
        - > 操作
            > - API: `admin.majorCompact("tab2".getBytes());`
            > - 命令: `hbase(main):0800:0>  major_compact('tab2')`

- # HBase 表设计
    - ## RowKey
        - > 将RowKey 以字典序按从大到小排序
            > - 默认是从小到大
        - > 尽量散列设计
        - > 长度尽量短
            > - 最好不超过16字节
        - > 建议用String 类型
            > - 底层是按字节数组形式存储和比较的
        - > 建议指定长度
            > - 操作系统位数的整数倍(8 的整数倍)，有利于操作系统的处理

    - ## 列族
        - >  不宜过多







