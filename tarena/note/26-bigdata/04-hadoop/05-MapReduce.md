

- ## 概述
    - > 是Hadoop 中的一个用于进行分布式计算的框架
    - > map(映射阶段) 
    - > Reduce(规约阶段)

- ## job 任务执行流程
    - > 收集环境信息进行校验
        > - 校验数据类型是否一致
        > - 校验文件是否存在
        > - 校验输出路径是否存在(不允许输出路径存在)
    - > 如果校验成功，分配一个job（本质上是一个进程），并且给job分配一个id，并且分配一个HDFS地址，将地址返回给客户端
    - > 客户端将程序提交到hdfs上
        > - 提交job，开始执行MapReduce程序
        - > 将job提交到JobTracker上，然后JobTracker将文件进行切片，并且计算MapTask以及ReduceTask的数量
    - > TaskTracker定期向JobTracker发送心跳信息，如果此时JobTracker上有了任务，这个时候JobTracker就要进行任务的分配，在分配/领取任务的时候，MapTask要满足数据的本地化；ReduceTask会分配到相对空闲的节点上
    - > TaskTracker将jar包下载到对应的节点上，开始执行MapTask或者ReduceTask - 体现了逻辑移动而数据固定的思想
    - > TaskTracker会在当前节点上开启一个JVM子进程执行MapTask或者ReduceTask，每执行完一个MapTask或者ReduceTask就会关闭这个JVM子进程；如果当前节点有多个MapTask，那么每执行一个MapTask都会开启一个JVM子进程

- ## 补充
    - > MR框架为了确保行读取的完整性，会做行的追溯。这个过程肯定会发生网络数据传输。

- ## 名词
    - > <ResourceManager>JobTracker job任务管理者
        > - 负责管理TaskTracker 
        > - 负责对外接收请求
    - > <NodeManager>TaskTracker 任务执行者(包括MapTask 任务和ReduceTask 任务)
        > - MapTask 任务
        >> - 每一个分片(Split)对应一个MapTask任务; 
        >> - 每一个MapTask 任务会被分配到对应的DataNode 节点上去执行这个任务;
        >> - 每一个MapTask 任务都满足数据本地化策略，目的是提升执行效率，减少网络传输。
        >> - 为了提高效率，每一个逻辑分片(Split)对应一个物理分块(Block)。
        > - ReduceTask 任务
        >> - 每一个分区对应一个ReduceTask 任务。
        >> - 每一个ReduceTask 都要接收MapTask 的结果做为其任务依据(参数)
        >> - 每一个ReduceTask 会产生一个结果文件








