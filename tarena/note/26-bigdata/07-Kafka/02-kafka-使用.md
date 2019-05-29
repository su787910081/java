


- ## 配置
    - > `config/server.properties`
        > - `broker.id=0` 多节点的话这个值不能重复
        >> - 由此ID 区分多节点
        > - `log.dirs=/root/software/kafka_2.11-1.0.0/kafka-logs`
        >> - 用来存储消息数据日志的目录
        > - `zookeeper.connect=HBase01:2181,HBase02:2181,HBase03:2181`
        >> - kafka 用到的ZooKeeper 集群


- ## KAFKA 的运行
    - > 开启ZooKeeper 集群服务器
        > - KAFKA 的运行依赖ZooKeeper 
    - > `sh kafka-server-start.sh ../config/server.properties`
        > - 指定配置文件启动KAFKA 服务器

- ## ZooKeeper 
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


