


# Spark调优

## GC 调优
- > 查看回收器相关的笔记

## 更好的序列化机制
- ### Spark 默认的序列化机制
    - > Spark 默认使用的序列化机制是JAVA 原生的序列化。
    - > JAVA 序列化有两个问题
        > - 性能相对较低
        > - 二进制的内容长度较大

- ### Spark 用到序列化的地方
    - > Shuffle时需要将对象写入到外部的临时文件。
    - > 每个Partition 中的数据要发送到Worker 上，Spark 先把RDD 包装成task 对象，将task 通过网络发给worker.
    - > RDD 如果支持内存+磁盘，只要往磁盘中写数据也会涉及序列化

- ### Kryo 序列化机制
    - > 比java 的序列化机制快10 倍以上
    - > 使用Kryo 序列化机制，有一个前提
        > - 必须实现特质`Serializable`
        > - 详细查看DEMO
    - > 引入Kryo 有三种方式
        - > 修改配置文件: `spark-defaults.conf`
            > - `spark.serializer  org.apache.spark.serializer.KryoSerializer`
        - > 启动Spark-shell 或者Spark-submit 时配置参数
            > - `--conf spark.serializer=org.apache.spark.serializer.KryoSerializer`
        - > 在代码中(更灵活，可以指定一个Driver)
            > - `val conf = new SparkConf()`
            > - `conf.set(“spark.serializer”,“org.apache.spark.serializer.KryoSerializer”)`
        - > 上面三种方式实现效果相同，但是优先级为: `配置文件 < 启动参数 < 代码设置`

## 配置多临时文件目录
- ### 产生临时文件
    - > 当shuffle、归并的排序(sort、merge)时都会产生临时文件。
        > - 这个临时文件都在一个指定的目录下。
        > - 这些临时文件都在同一个磁盘上面。
        > - 如果发生读写操作，那么磁盘I/O性能就非常低。

- ### 配置多个临时文件目录
    - > 配置参数
        > - 多个文件目录用',' 隔开
        > - `spark.local.dir=/home/tmp,/home/tmp2`
    - > 每一个临时文件目录分别挂载于不同的磁盘以提升I/O性能
        > - 每一个临时文件目录处于不同的磁盘，那么这些I/O 就会发生在不同的磁盘
        > - 这样以减少磁盘I/O来获取更高性能的读写

## 启用推测执行机制
- ### 配置参数
    - > `spark.speculation  true `

## 减少甚至禁止使用 collect 
- > 我们在讲的时候一直强调，collect只适合在测试时，因为把结果都收集到Driver服务器上，数据要跨网络传输，同时要求Driver服务器内存大，所以收集过程慢。解决办法就是直接输出到分布式文件系统中。

## RDD操作使用MapPartitions替代map
- > `MapPartitions` 是对整个分区做处理，比如每次我们都要对某个资源做打开关闭操作的话。按分区一次打开关闭性能将会得到很大的提高




