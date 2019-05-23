

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
        >> - 将job提交到JobTracker上，然后JobTracker将文件进行切片，并且计算MapTask以及ReduceTask的数量
    - > TaskTracker定期向JobTracker发送心跳信息，如果此时JobTracker上有了任务，这个时候JobTracker就要进行任务的分配，在分配/领取任务的时候，MapTask要满足数据的本地化；ReduceTask会分配到相对空闲的节点上
    - > TaskTracker将jar包下载到对应的节点上，开始执行MapTask或者ReduceTask - 体现了逻辑移动而数据固定的思想
    - > TaskTracker会在当前节点上开启一个JVM子进程执行MapTask或者ReduceTask，每执行完一个MapTask或者ReduceTask就会关闭这个JVM子进程；如果当前节点有多个MapTask，那么每执行一个MapTask都会开启一个JVM子进程

- ## 补充
    - > MR框架为了确保行读取的完整性，会做行的追溯。这个过程肯定会发生网络数据传输。

- ## 名词
    - > JobTracker(ResourceManager) job任务管理者
        > - 负责管理TaskTracker 
        > - 负责对外接收请求
    - > TaskTracker(NodeManager) 任务执行者(包括MapTask 任务和ReduceTask 任务)
        > - MapTask 任务
        >> - 每一个分片(Split)对应一个MapTask任务; 
        >> - 每一个MapTask 任务会被分配到对应的DataNode 节点上去执行这个任务;
        >> - 每一个MapTask 任务都满足数据本地化策略，目的是提升执行效率，减少网络传输。
        >> - 为了提高效率，每一个逻辑分片(Split)对应一个物理分块(Block)。
        > - ReduceTask 任务
        >> - 每一个分区对应一个ReduceTask 任务。
        >> - 每一个ReduceTask 都要接收MapTask 的结果做为其任务依据(参数)
        >> - 每一个ReduceTask 会产生一个结果文件

- ## Hadoop 中的MapReducer 相关进程与线程
    - > `ResourceManager(JobTracker)`
        > - 负责管理TaskTracker(MapTask、ReduceTask) 的进程，同时对外提供服务，接收请求
        >> - TaskTracker 会以心跳的形式到JobTracker 来判断并领取是否有自己的工作任务。如果有它就会将自己的任务领取，同时带走相关的一些数据信息。
    - > `NodeManager(TaskTracker)`
        > - 负责执行MapTask 和ReduceTask 任务的进程。
    - > `MapTask` 执行mapper 任务的线程，它附属在`NodeManager` 进程中
        > - mapper 任务会接收来自 `FileInputFormat<Key, Value>` 的输出Key-Value
        > - 然后将其按业务逻辑处理之后再自己生成一个Key-Value 结果。
        > - 如果Driver 中指定了Combine 处理类，那么MapTask 会将这些结果按其指定的逻辑进行排序。
        >> - 如果没有则按默认的方法进行排序。
        >> - 合并的目的是为了减小reduceTask 的压力。
        > - 如果产生的结果数据太大，那么就会产生溢写，将缓冲区中的数据写到溢写文件中。
        > - 如果滴定文件多于3 个则会发生合并(merge)。
        > - 最后结果文件往往是写到磁盘，等待ReduceTask 的fetch 线程来读取。
    - > `ReduceTask` 执行Reducer 任务的线程，它也附属在`NodeManager` 进程中
        > - 每一个ReduceTTask 会接收自己处理的那一部分分区的Key 所对应的mapper 输出
        > - 每一个Reduce 都会产生一个结果文件。
        > - 默认情况下，当MapTask 完成总任务的5% ReduceTask 才会启动，同时默认情况下会生成5 个fetch 线程到MapTask对应的节点去取结果数据。
        >> - fetch 线程它只会取属于自己分区的数据。
        > - 默认情况下ReduceTask 会按10 个合1 个的方式将结果进行多次合并。最后生成一个结果文件。
        > - ReduceTask 会将结果进行排序








