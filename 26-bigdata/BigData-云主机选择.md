- # 云主机
    - > 内存型
        > - 
    - > IO 型
    - > 磁盘型
    - > 计算型

    - # Hadoop
        - > NameNode
            > - 内存型
        - > DataNode(存储)/NodeManager(计算)
            > - IO 型(贵)、计算型、磁盘型(便宜，最差的选择)
        - > ResourceManager
            > - 内存型、通用型也够用
        - > JornalNode
            > - IO 型、磁盘、通用
            > - 随意

    - # Flume:
        - > Memory Channel: 内存型
        - > File Channel: IO型

    - # HBase:
        - > HMaster: 内存型
        - > HRegionServer: DataNode
            > - IO 型

    - # Kafka:
        - > IO 型

    - # ZooKeeper:
        - > 内存型







