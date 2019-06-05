
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
        >> - `export JAVA_HOME=/home/software/jdk1.8`
        > - 编辑`slaves` 添加所有加入集群工作的的主机
        >>     hadoop01
        >>     hadoop02
        >>     hadoop03
    - > 集群模式的启动(`sbin` 目录)
        > - `sh start-all.sh`
        > - 在一个节点启动，其他的节点会自动开启相应的工作节点
        > - 集群模式下，在哪个节点启动，那么哪个节点就有一个`Master`
    - > 运行自定义Spark 代码
        > - 将自定义代码打成一个jar 包并上传服务器
        > - 在`bin` 目录下执行命令
        >> - `$ sh spark-submit --class day02.cn.tedu.wordcount.Driver wordcount.jar `
        >> - 指定要执行的哪一个Driver, 并提供jar 包
        
# 相关配置
- ## 配置文件：`spark-defaults.conf`
    - > 从提供的模板拷贝一个复本
        > - `cp spark-defaults.conf.template spark-defaults.conf`



