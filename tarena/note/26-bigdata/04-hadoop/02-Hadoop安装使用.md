

- ## 安装模式
    - > 单机模式
        >> 只能启用mapReduce,不能启用HDFS 以及Yarn
    - > 伪分布式
        >> 在一台机器 来模拟集群，启动Hadoop的所有进程
    - > 全分布式
        >> 多台机器来搭建


- ## 主机名
    - > `vim /etc/sysconfig/network`
        >>      HOSTNAME=hadoopalone
    - > `vim /etc/hosts`
        >>      192.168.220.130 hadoopalone
    - > `reboot` 重启系统使主机名修改生效
    - > <mark>注意：</mark>
        >> - 主机名里不能有下滑线，或者特殊字符 `"_ - # $"`，不然会找不到主机导致无法启动
        >> - 这种方式更改主机名需要重启才能永久生效，因为主机名属于内核参数。
        >> - 如果不想重启，可以执行：hostname hadoopalone。
        >> - 但是这种更改是临时的，重启后会恢复原主机名。
        >> - 所以可以结合使用。先修改配置文件，然后执行:hostname hadoopalone 。可以达到不重启或重启都是主机名都是同一个的目的

- ## 配置免密登录
    - > ssh-keygen
        >>      (/root/.ssh/id_rsa)
    - > ssh-copy-id root@hadoopalone

- ## 伪分布式配置
    - > `vim ${HADOOP_DIR}/etc/hadoop/hadoop-env.sh`
        >>     export JAVA_HOME=/usr/local/src/java/jdk1.8.0_51
        >>     export HADOOP_CONF_DIR=/root/software/hadoop-2.7.1/etc/hadoop
        > - 使配置生效 `source hadoop-env.sh `
    - > `vim ${HADOOP_DIR}/etc/hadoop/core-site.xml`
        > - `fs.defaultFS` 指定nameNode 所在节点
        > - `hadoop.tmp.dir` 指定元数据的存储目录
        >>      <configuration>
        >>          <property>
        >>              <name>fs.defaultFS</name>
        >>              <value>hdfs://hadoopalone:9000</value>
        >>          </property>
        >>          <property>
        >>              <name>hadoop.tmp.dir</name>
        >>              <value>/root/software/hadoop-2.7.1/tmp</value>
        >>          </property>
        >>      </configuration>
    - > `vim ${HADOOP_DIR}/etc/hadoop/hdfs-site.xml`
        > - `dfs.replication` 复本数量，默认情况下是3。这里指定为1(伪分布式必须为1，否则它将处于安全模式无法退出)
        >>      <configuration>
        >>          <property>
        >>              <name>dfs.replication</name>
        >>              <value>1</value>
        >>          </property>
        >>      </configuration>
    - > `cp mapred-site.xml.template mapred-site.xml   vim `
        > - 这两个值不能随便改变
        >>
        >>      <configuration>
        >>          <property>
        >>              <name>mapreduce.framework.name</name>
        >>              <value>yarn</value>
        >>          </property>
        >>      </configuration>
    - > `vim ${HADOOP_DIR}/etc/hadoop/yarn-site.xml`
        > - 第一个是主机名，第二个配置固定
        >>      <configuration>
        >>          <property>
        >>              <name>yarn.resourcemanager.hostname</name>
        >>              <value>hadoopalone</value>
        >>          </property>
        >>          <property>
        >>              <name>yarn.nodemanager.aux-services</name>
        >>              <value>mapreduce_shuffle</value>
        >>          </property>
        >>      </configuration>
    - > `vim ${HADOOP_DIR}/etc/hadoop/slaves `
        > - 主机名
        >>      hadoopalone
    - > `vim /etc/profile` 环境变量
        >>      export HADOOP_HOME=/root/software/hadoop-2.7.1
        >>      export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

- ## 启动hadoop
    - > <mark>第一次启动时需要格式化hadoop</mark>
        > - `hadoop namenode -format`
        > - 成功提示
        >>      common.Storage: Storage directory /root/software/hadoop-2.7.1/tmp/dfs/name has been successfully formatted.
        > - 如果配置出错，则需要则配置的`vim core-site.xml` 中的 `hadoop.tmp.dir` 的目录删除之后再重新执行格式化 `hadoop namenode -format`
    - > `start-all.sh`
        > - 若成功，使用JPS 会有以下5 个进程
        >>      3155 NodeManager
        >>      2646 NameNode
        >>      2919 SecondaryNameNode
        >>      3064 ResourceManager
        >>      2735 DataNode
    - > 浏览器管理
        > - `http://192.168.220.130:50070`















