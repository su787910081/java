

- # 安装文件
    - > `hbase-0.98.17-hadoop2-bin.tar.gz`

- # 依赖准备
    - > JDK
    - > HADOOP 伪分布式
    - > ZOOKEEPER

- # 配置免密登录

- # HBase 全分布式配置
    - ## 全分布式的各节点的配置基本一致，可以配置一台然后将其配置拷贝到其他节点即可。

    - > 配置文件(`conf/hbase-env.sh`)
        > - JDK 
        >>      export JAVA_HOME=/usr/local/src/java/jdk1.8.0_51
        > - <mark>禁止使用自带ZooKeeper</mark>(自带的ZooKeeper好像端口是2181)
        >>      export HBASE_MANAGES_ZK=false
        > - 使配置生效
        >>      source conf/hbase-env.sh

    - > 配置文件(`conf/hbase-site.xml`)
        > - 添加配置
        > - 如果Hadoop是完全分布式，那么`hbase.roodir` 属性就必须对应Hadoop 的配置文件`core-site.xml`中的`fs.defaultFS` 属性值
	    >>      <property>
	    >>          <name>hbase.rootdir</name>
	    >>          <value>hdfs://hadoop01:9000/hbase</value>
	    >>      </property> 
	    >>      <property>
	    >>          <name>hbase.cluster.distributed</name>
	    >>          <value>true</value>
	    >>      </property>
	    >>      <!--#配置Zookeeper的连接地址与端口号-->
	    >>      <property>
	    >>          <name>hbase.zookeeper.quorum</name>
	    >>          <value>ZooKeeper01:2181,ZooKeeper02:2181,ZooKeeper03:2181</value>
	    >>      </property>
        >>

    - > 配置文件(`conf/regionservers`)
        > - 每个主机名独占一行，hbase启动或关闭时会按照该配置顺序启动或关闭主机中的hbase:
        > - 指定哪些节点是干活的节点
        >>      HBase01
        >>      HBase02
        >>      HBase03

- ## 启动ZooKeeper 服务
- ## 启动Hadoop 服务
- ## HBase 服务
    - > 启动
        > - 在任意一个节点启动，其他节点中的也会启动相应的进程
        >>     cd bin/
        >>     sh start-hbase.sh
        > - 检查是否有相关进行
        >> - `HMaster` 进程只有一个节点上有
        >> - `HRegionServer`  进程在三个节点上都有
        > - 这个时候只有一个主Master，如果想要增加备份Master 则需要在对应的节点上执行启动
        >>     sh start-hbase.sh
        > - 主Master 是按顺序来控制的，后启动的Master 会自动作为备份Master 存在
    - > 停止
        > -     sh stop-hbase.sh



- # 校验查看
    - > 查看各节点的java进程是否正确，或者通过浏览器管理hbase 页面查看
        > - 访问网页: `http://HBase01:60010`
        > - 主Master
        >>      Master HBase01
        > - 备份Master
        >>      Backup Master HBase02
        >>      Current Active Master: HBase01











