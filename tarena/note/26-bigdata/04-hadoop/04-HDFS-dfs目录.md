

- ## dfs 目录
    - > 在NameNode 进行格式化的时候创建
        >> - tmp/dfs/
    - > dfs - {data、name、namesecondary}  DataNode NameNode 以及SecondaryNameNode 的存储目录
    - > 当启动HDFS 的时候，在name 目录下面会生成一个in_use.lock 文件
        >> - in_use.lock 防止一个节点上多次启动HDFS
    - > 当停止之后，in_use.lock 文件将会删除
    - > 新的操作写到 edits_inprogress，当这个文件写满之后自动更名为edits_xxxxxxxxxxx1-xxxxxxxxxx2
    - > HDFS 中会对每一次的写操作分配一个递增的编号 - 事务ID - txid
    - > 在HDFS 第一次启动的时候，一分钟之后会自动合并edits 和fsimage 文件。仅在第一次启动的时候。之后就按照配置，每一个小时合并一次。
    