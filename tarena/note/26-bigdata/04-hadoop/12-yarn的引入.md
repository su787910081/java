

- ## Yarn 架构图
![](./img/Yarn.png)

- ## 分布式集群的问题
    - > 经过试验，JobTracker 所能管理的节点数量最多不要超4000，一但超过4000 个节点，则整个集群的性能会成倍下降
    - > 为了解决性能下降的问题，引入了Yarn 来进行管理。Hadoop2.0 开始，MapReduce 就只负责计算。

- ## Yarn 
    - > ResourceManager
        > - ApplicationsManager
        >> - 封装 Container 对象(资源数据的封装)，并交给Schedular
        >> - 如果不指定 Container 则默认会分配1G内存以及1个CPU核
        > - Schedular
        >> - 负责将任务以及资源（Container） 发给ApplicationMaster
    - > ApplicationMaster(并不是一个主进程，在启动Yarn 的时候自动启动，不会单独占用一个节点)
        > - 任务调试：负责任务的分配和监控
        >> - 将从ResourceManager 得到的Contaniner ，知道了有多少MapTask和ReduceTask
        >> - 然后将这些MapTask 和ReduceTask 分发到NodeManager 去执行。
        >> - 如果收到NodeManager 的失败信息，那么它会给这个任务重新申请资源然后换个节点继续执行此任务。
    - > NodeManager
        > - 执行任务的节点
        > - NodeManager 会向ApplicationMaster 汇报任务的执行情况(成功与失败)，并且向ApplicationsManager 汇报资源的使用情况(回收资源)






