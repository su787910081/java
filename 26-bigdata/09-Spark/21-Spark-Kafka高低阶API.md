


# 官方网文档
- > http://spark.apache.org/docs/1.6.0/streaming-kafka-integration.html

# Kafka + SparkStreaming 消费者模式
## 两种模式
- > Receiver 消费者模式(高阶API)
- > Direct 消费者模式(低阶)
### High Level Consumer API
#### Receiver 消费者模式(高阶API)
- > ### 原理与说明
    > - 需要依赖ZooKeeper 来进行管理Offset
    >> - 向ZooKeeper 中更新Offset
    > - 这种模式SparkStreaming 不关心数据的丢失。
    > - 解决方案，开启WAL(Write Ahead Log) 预写日志机制。
    >> - `spark.streaming.receiver.writeAheadLog.enable`
    >> - 开启WAL 机型，必须要有对应的checkpoint
    >> - 同时将缓存级别降低: StorageLevel.MEMORY_AND_DISK

- > ### 接收数据并备份
    > - 这种模式下，Spark 会有专门的一个Executor 来接收Kafka 中的数据，并备份。
    > - WAL 就是将接收到的数据备份起来

- > ### Receriver 模式的并行度
    > - 由 `spark.streaming.blockInterval = 200` 决定
    >> - 这个值默认是200 ms，管方建议最低不要低于50ms 否则每个block 中的数据将会太少
    >> - receiver 模式接收数据时，每隔多少时间落地一个block
    >> - 假设 `batchInterval = 5s` ，则一个批次内生成25 个block
    >> - 这个block 将会被RDD 的每个分区拿到并处理，所以我们可以降低这个参数从而提高生成block 的个数。再添加RDD 对应的分区，这样来提高并行度。


### Simple Consumer API
#### Direct 消费者模式(低阶)
- > ### 原理说明
    > - SparkStreaming 的分区数与Kafka 中的分区数一致，且分区一一对应
    > - Spark 自己管理offset 没有使用ZooKeeper 管理
    >> - 默认Offset 在内存和checkpoint 的磁盘中都有一份。
    > - 没有将Kafka 中的数据存储到本地，而是直接从Kafka 中拉取数据。







