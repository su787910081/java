
# 单机模式
- ## 安装与配置
    - > 安装和配置好JDK
    - > 上传和解压Spark安装包
    - > 配置(`conf`目录)
        > - `cp spark-env.sh.template spark-env.sh`
        > - `SPARK_LOCAL_IP=192.168.220.130`
    - > 单机模式启动
        > - `sh spark-shell --master=local`
    - > 访问网址
        > - `http://192.168.220.130:4040`

# 集群模式
- ## 安装与配置
    - > Spark 自身实现了集群模式，并不依赖ZooKeeper
    - > 安装和配置好JDK
    - > 上传解压spark安装包
    - > 配置(`conf`目录)
        > - `cp spark-env.sh.template spark-env.sh`
        >> - `SPARK_LOCAL_IP=192.168.220.130`
        >> - `SPARK_LOCAL_DIRS=/home/software/spark/tmp`
        >>> - Spark Shuffle 产生的临时文件所存放的位置，可以配置多个以','分隔
        >>> - 配置多个的意义在于，挂载多个磁盘，使读写不同的磁盘而提升读写的性能
        >> - `export JAVA_HOME=/home/software/jdk1.8`
        > - 编辑`slaves` 添加所有加入集群工作的的主机
        >>     hadoop01
        >>     hadoop02
        >>     hadoop03
    - > 集群模式的启动(`sbin` 目录)
        > - `sh start-all.sh`
        > - 在一个节点启动，其他的节点会自动开启相应的工作节点
        > - 集群模式下，在哪个节点启动，那么哪个节点就有一个`Master`
    - > 集群模式的管理WEL 界面端口为8080
        > - URL: `http://192.168.234.11:8080`
    - > 在集群模式上运行自定义Spark 代码
        > - 将自定义代码打成一个jar 包并上传服务器
        > - 在`bin` 目录下执行命令
        >> - `$ sh spark-submit --class day02.cn.tedu.wordcount.Driver wordcount.jar `
        >> - 指定要执行的哪一个Driver, 并提供jar 包
    - > 使用 spark-shell 连接Spark 集群
        > - `sh spark-shell --master spark://hadoop01:7077`

- ## spark 动态添加一个新节点
    - > 按上面的集群模式进行配置
        > - 然后使用下面的命令将此节点添加到 Spark 集群
        >> - `sbin/start-slave.sh spark://DEV-HADOOP-01:7077`
        > - 然后就可以通过WEB 页面查看到新添加的节点信息了

- ## 集群进程
    - > Master 进程
        > - 在哪台机器开启的集群，那么这个进程就会在哪台机器运行
    - > Worker 进程

# 访问spark
- > 单机模式端口：4040
- > 集群模式端口: 8080

# 相关配置
- ## 配置文件：`spark-defaults.conf`
    - > 从提供的模板拷贝一个复本
        > - `cp spark-defaults.conf.template spark-defaults.conf`



