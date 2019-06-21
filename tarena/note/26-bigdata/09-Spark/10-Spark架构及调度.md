# Spark 架构模块
- ## 模块图
    > <details>
    > 
    >> ![](./img/Spark架构图.png)
    > </details>

- ## Claster Master 集群管理器
    - > 对应图中Claster Master 进程
        > - 集群管理器负责集群的资源高度
        > - 比如为Worker Node 分配CPU、内存等资源
        > - 并实时监控Worker 的资源使用情况。
    - > Claster Master 只负责资源管理高度，而任务的分配和结果处理它不管。
        > - 所以，这个进程可以被Yarn 替换

- ## Driver Program
    - > 用户编写的Spark 程序称为一个Driver Program。
        > - 每个Driver Program 仅包含一个集群环境的SparkContext 对象。
        > - 程序的执行从Driver 程序开始，最后回到Driver 程序。

- ## SparkContext 对象
    >> <details>
    >> 
    >>> ![](./img/Saprk任务调度流程图.png)
    >> </details>
    >> <details>
    >>
    >>> ![](./img/Saprk任务调度流程图.jpg)
    >> </details>
    >> 
    - > 每一个Driver Program 里都有一个SparkContext 对象
    - > 它的职责如下
        > - SparkContext 对象联系Cluster Manager(集群管理器), 让Cluster Manager 为 Worker Node 分配CPU、内存等资源。
        > - 与Executor 进程交互，负责任务的调度分配。
    - > DAGScheduler 模块
        > - 将一个DAG划分为多个Stage
        >> - 将多个窄依赖合并为一个Task 形成一个Task 集合。这一组集合就是一个Stage
        > - 把Task Set 交给TaskScheduler模块。
    - > TaskScheduler 模块
        > - 为属主（SparkContext）调度任务
        > - 主要是为Task 模块分配计算资源，并向Master 申请资源 
        > - 将Task 提交到Worker 进行任务的执行
        > - 同时还会监控Task 在Worker 上面的执行情况，当失败了之后会再次提交，然后再次运行。
    - > SchedulerBackend
        > - 是一个trait，作用是分配当前可用的资源，具体就是向当前等待分配计算资源的Task分配计算资源（即Executor），并且在分配的Executor上启动Task，完成计算的调度过程。

- ## Worker Node
    - > Worker 节点，集群上的计算节点，对应一台物理机

- ## Worker 进程
    - > 用于与Master 进程交互向Master 注册和汇报自身节点的资源使用情况，并管理和启动Executor 进程

- ## Executor
    - > Executor 是一个进程，它会为每一个Task 开启一个线程来运行。
    - > 负责运行Task 计算任务，并将计算结果回传到Driver 中。

- ## Task 
    - > 在执行器上执行的最小单元
        > - 比如RDD Transformation 操作时对RDD 内每个分区的计算都会对应一个Task


# Spark 的调度
- ## 调度
    - > 示例图
        >> <details>
        >> <summary><mark>示例图</mark></summary>
        >> 
        >>> ![](./img/cluster-overview.png)
        >> </details>
        >> 

- ## 调度流程
    - > 示例图
        >> <details>
        >> <summary><mark>示例图1</mark></summary>
        >> 
        >>> ![](./img/Saprk任务调度流程图.png)
        >> </details>
        >> 
        >> <details>
        >>
        >> <summary><mark>示例图2</mark></summary>
        >> 
        >>> ![](./img/Saprk任务调度流程图.jpg)
        >> </details>
        >> 
    - > 流程解析
        > - RDD Objects 可以理解为用户实际代码中创建的RDD，这些代码逻辑上组成了一个DAG
        >> - 也就是一个SparkContext 中的处理逻辑
        >> - 这些逻辑形成一个DAG(有向无环图)
        > - DAGScheduler 主要负责分析依赖关系，然后将DAG 划分为不同的Stage(阶段)
        >> - 其中每个Stage 由一组TaskSet 组成
        >> - 这些Task 执行逻辑完全相同，只是作用于不同的数据源
        >> - DAGScheduler 将TAG 划分完成之后，会将Stage 中的TaskSet 提交到TaskScheduler
        > - TaskScheduler 向ClusterManager 申请计算资源，并提交到对应的Worker 节点去执行相应的逻辑
        >> - 在Worker 中计算的结果会回传到Driver 或者保存到本地磁盘
        > - SchedulerBackend 这是一个类对象，它会分配当前的可用资源


- ## 调度模块
    - > Driver Program
        > - 每个Driver 都有一个SpackContext(sc)
        >> - sc 负责与Executor 交互，完成任务的分配的高度
