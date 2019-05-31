

- # 简介
    - > 分布式发布以及订阅消息

- # 特点
    - > poll 模式，消息消费的速率是由下游消费者决定
    - > 提供了消费的持久化机制
        > - 无论消费者是否消费数据都会存储
        > - 数据不会被系统删除，除非明确配置或者指定删除
    - > 分布式架构，可以吞吐海量数据，可以保证数据不丢
        > - 但是不能确保精确的事务性。
        > - 牺牲了精确的事务性，从而提高了数据的吞吐量。


- # 概念
    - > topic: 主题。
        > - 生产者和消费者都是基于主题来生产和消费数据的
        > - 一个topic 对应一个queue
        > - 创建主题，主题名为"video"
        >> - `sh kafka-topics.sh --create --zookeeper HBase01:2181 --replication-factor 1 --partitions 1 --topic video`
        > - 每创建一个topic 在`log.dirs` 产生一个目录

    - > partitions 分区
        > - 分区机制的意义在于分布式持久化存储数据
        > - 一个主题可以有多个分区。
        >> - 每一个分区物理上对应一个目录，编号从0 开始
        >> - 底层数据结构是一个队列
        > - 生产者生产出来的数据都会被持久化到某个分区中的文件中
        >> - 以负载均衡的方式存储到各个分区目录中
        > - 如果有多个kafka 节点，这些分区将会分布到各个节点上
        > - `sh kafka-topics.sh --create --zookeeper HBase01:2181 --replication-factor 1 --partitions 2 --topic cnbook`
        > - 一个topic 中的每一个`partitions` 在`log.dirs` 中对应一个目录
        > - `ls /root/software/kafka_2.11-1.0.0/kafka-logs`
        >>      cnbook-0/
        >>      cnbook-1/
        > - 主题的分区数直接决定了消费的并行度
        >> - 每一个分区将分对应一个线程
        - >  每一个分区可以有多个复本
            > - 各个复本之间有leader 和follower 之分

    - > replication-factor: 复本因子
        > - 每一个分区的副本数量
        > - 复本的数量不能超过 broker (节点)的数量
        > - 提高分区的副数，并不能提高并发度，因为无论是生产者还是消费者，都是和副本的leader 交互。


    - > broker 
        > - 代表着一个KAFKA 服务器节点
        > - 每一个broker 之间都是平等的没有leader follower  之分

    - > 消费者
        - > 测试环境
            > -     sh kafka-console-consumer.sh --zookeeper HBase01:2181 -topic music  --from-beginning
            > - `--from-beginning` 指定从头开始消费，如果不加则之前的数据忽略掉，从新的数据开始消费。
            > -  带消费者组
            >> -    sh 

    - > 生产者
        - > 测试环境
            > -     sh kafka-console-producer.sh --broker-list HBase01:9092 -topic music
            > - `--broker-list HBase01:9092` 指定有哪几个KAFKA 节点
            > - `-topic music` 指定往哪个topic 里面写消息
        - > 实际环境下可以是Flume、Web 等其他源

    - > 删除topic
        > - `sh kafka-topics.sh --delete --zookeeper HBase01:2181 --topic "video"`
        > - 当我们在kafka 中删除topic 时，它不会被立即删除
        > - 在经过一段短暂的时间后才会被真正删除

    - > Controller 
        > - 会在某一台 broker 中运行
        >> - 他负责分区复本leader 的选举

    - > 消费者组(Consumer Group)
        > - 组间共享数据，组内竞争数据
        >> - 各个组之间可以拿到相同的数据的复本
        >> - 但是到一个组里面，一个数据只能由这个组内的某一个成员获得。
        > - 消费者在启动的时候可以为其指定组名，相同组名的消费者在同一个组中。

    - > Offset
        > - 每条数据在分区中都有一个偏移量 (Offset)

    - > 历史数据问题
        > - KAFKA 的数据是不会主动删除的，不管是否有人消费这些数据都会保留下来
        >> - 这样就会出现一个问题，那就是磁盘空间越来越小
        >> - KAFKA 就提供了一个定期清理磁盘或者判断日志文件的大小在一定大小时清理数据。
        > - 配置一定时间清理历史数据
        >>      log.retention.hours = 168
        > - 配置一定大小后清理历史数据
        >>      log.segment.bytes=1073741824
        > - 这个好像是间隔时间吧??
        >>      log.retention.check.interval.ms=300000


- ## KAFKA 的好处
    - > 可以屏蔽掉生产者和消费者之间的异构性，实现解耦
    - > 在WEB 环境下可以实现消峰限流


- ## 命令
    - > 查看当前KAFKA 中有哪些主题
        > - `sh kafka-topics.sh --list --zookeeper HBase01:2181`


- # 服务端启动
    - > `sh kafka-server-start.sh ../config/server.properties`


- ## KAFKA 持久化数据
    - > enbook 主题持久化目录，在配置的目录下面会新建一个对应主题的目录。并在后面添加下标
        > -     /root/software/kafka_2.11-1.0.0/kafka-logs/enbook-0
        > -  `ls enbook-0/` 4 个文件
        >>      00000000000000000000.index  00000000000000000000.timeindex
        >>      00000000000000000000.log    leader-epoch-checkpoint
        > - `00000000000000000000.log` 中记录着一个分区中的顺序的数据


- ## KAFKA 的写流程
    - > `sh kafka-topics.sh --create --zookeeper HBase01:2181 --replication-factor 3 --partitions 1 --topic enbook`
        > - 以5 个broker 节点为例
        > - 一个分区三个复本
        > - 生产者生产数据
        >>      sh kafka-console-producer.sh --broker-list HBase01:9092,HBase02:9092,HBase03:9092,HBase04:9092,HBase05:9092 -topic enbook
        > - 通过ZooKeeper 找leader 的broker id
        >> - ZooKeeper 知道哪个broker 中的分区是leader
        > - leader 将数据写到自己的log 文件中
        > - leader 将数据发给分区的其他复本(follower)节点上
        > - leader 最后给客户端响应ACK

- ## ISR 机制
    - > 比如有5个broker 节点 
        > - 假设某个主题在各broker 节点中的角色为 `1: leader 3: follower 4: follower`
        > - 在同步数据的过程中，3 同步成功，但是4 同步失败
        > - 那么1 和3 是一组ISR 成员，4 不是ISR 成员
        >> - 在选举的时候会用到ISR 机制
        >> - 选举的时候就会从1 和3 两个ISR  成员中选出来

- ## 配置
    - > `config/server.properties`
        > - `broker.id=0` 多节点的话这个值不能重复
        >> - 由此ID 区分多节点
        > - `log.dirs=/root/software/kafka_2.11-1.0.0/kafka-logs`
        >> - 用来存储消息数据日志的目录
        > - `zookeeper.connect=HBase01:2181,HBase02:2181,HBase03:2181`
        >> - kafka 用到的ZooKeeper 集群

- ## Kafka 依赖ZooKeeper  做分布式
    - > /cluster
    - > /brokers
        - > `ls /brokers/ids`
            > -     [0]
            > - 每一台KAFKA 节点运行时在ZooKeeper 上注册的临时节点，以表示该节点为活动节点
            > - 节点宕机或者停机该节点将消失
        - > `get /brokers/ids/0`
            > - `get /brokers/ids/0`
            >>       {"listener_security_protocol_map":{"PLAINTEXT":"PLAINTEXT"},"endpoints":["PLAINTEXT://HBase01:9092"],"jmx_port":-1,"host":"HBase01","timestamp":"1559128852749","port":9092,"version":4}
            >>       cZxid = 0x600000014
            >>       ctime = Wed May 29 04:20:53 PDT 2019
            >>       mZxid = 0x600000014
            >>       mtime = Wed May 29 04:20:53 PDT 2019
            >>       pZxid = 0x600000014
            >>       cversion = 0
            >>       dataVersion = 0
            >>       aclVersion = 0
            >>       ephemeralOwner = 0x36b034b33c40001
            >>       dataLength = 184
            >>       numChildren = 0
    - > /brokers/topics
        - > `ls /brokers/topics`
            > -     [music, enbook]
            >> - 当前KAFKA 中有哪些主题在ZooKeeper 中的存储位置
            >> - 每一个主题都是一个节点信息



- ## kafka 清理环境到初始环境
    - > 清理KAFKA 服务器的历史数据
        > - 找到KAFKA 服务器保存数据的目录
        >> - 配置文件`server.properties` 中的配置项 `log.dirs=/root/software/kafka_2.11-1.0.0/kafka-logs`
        > - 删除该目录
        >> - `rm -rf /root/software/kafka_2.11-1.0.0/kafka-logs`
    - > 清理KAFKA 在ZooKeeper 中的数据
        > - 连接到ZooKeeper 服务器
        > -  `./zkCli.sh -server HBase01:2181`
        >>      [zk: HBase01:2181(CONNECTED) 2] rmr /cluster
        >>      [zk: HBase01:2181(CONNECTED) 3] rmr /brokers
        >>      [zk: HBase01:2181(CONNECTED) 4] rmr /admin
        >>      [zk: HBase01:2181(CONNECTED) 5] rmr /isr_change_notification
        >>      [zk: HBase01:2181(CONNECTED) 6] rmr /log_dir_event_notification
        >>      [zk: HBase01:2181(CONNECTED) 7] rmr /controller_epoch
        >>      [zk: HBase01:2181(CONNECTED) 8] rmr /consumers
        >>      [zk: HBase01:2181(CONNECTED) 9] rmr /latest_producer_id_block
        >>      [zk: HBase01:2181(CONNECTED) 10] rmr /config







