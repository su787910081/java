


- ## 配置主机名
    - > `vim /etc/sysconfig/network`
        >>      HOSTNAME=hadoopalone
    - > `vim /etc/hosts`
        >>      192.168.220.130 hadoopalone
    - > `reboot` 重启系统使主机名修改生效
    - > <mark>注意：</mark>
        >> - 主机名里不能有下滑线，或者特殊字符 `"_ - # $"`，不然会找不到主机导致无法启动
        >> - 这种方式更改主机名需要重启才能永久生效，因为主机名属于内核参数。
        >> - 如果不想重启，可以执行：`hostname hadoopalone`。
        >> - 但是这种更改是临时的，重启后会恢复原主机名。
        >> - 所以可以结合使用。先修改配置文件，然后执行:`hostname hadoopalone` 。可以达到不重启或重启都是主机名都是同一个的目的

- ## 配置ssh 免密登录
    - > `ssh-keygen`   生成密钥
        >>      (/root/.ssh/id_rsa)
    - > `ssh-copy-id root@hadoopalone`
        > - 发送密钥到目标服务器，这样使用ssh 协议登录目标服务器就可以只在第一次输入密码，之后就不再需要密码了。

- ## 配置文件(伪分布式)
    - ### 约定`${HADOOP_DIR}` 为Hadoop 的安装 根目录，下面的这些配置文件都是基于这个目录的。
    - > `vim ${HADOOP_DIR}/etc/hadoop/hadoop-env.sh`
        >>     export JAVA_HOME=/usr/local/src/java/jdk1.8.0_51
        >>     export HADOOP_CONF_DIR=/root/software/hadoop-2.7.1/etc/hadoop
        > - 使配置生效 `source hadoop-env.sh `
    - > `vim ${HADOOP_DIR}/etc/hadoop/core-site.xml`
        > - `fs.defaultFS` 指定nameNode 所在节点
        > - `hadoop.tmp.dir` 指定元数据的存储目录
        > - `fs.trash.interval` 回收站
        > <details>
        > <summary><mark><font color=darkred>core-site.xml</font></mark></summary>
        > 
        >>      <configuration>
        >>          <property>
        >>              <name>fs.defaultFS</name>
        >>              <value>hdfs://hadoopalone:9000</value>
        >>          </property>
        >>          <property>
        >>              <name>hadoop.tmp.dir</name>
        >>              <value>/root/software/hadoop-2.7.1/tmp</value>
        >>          </property>
        >>          <property>
        >>              <!-- 开启回收站，属性表示文件在回收站中存在多长时间，单位：分钟 -->
        >>              <!-- 时间到了会被清除 -->
        >>              <name>fs.trash.interval</name>
        >>              <value>1440</value>
        >>          </property>
        >>      </configuration>
        > </details>
        > 
    - > `vim ${HADOOP_DIR}/etc/hadoop/hdfs-site.xml`
        > - `dfs.replication` 复本数量，默认情况下是3。这里指定为1(伪分布式必须为1，否则它将处于安全模式无法退出)
        > <details>
        > <summary><mark><font color=darkred>hdfs-site.xml</font></mark></summary>
        > 
        >>      <configuration>
        >>          <property>
        >>              <name>dfs.replication</name>
        >>              <value>1</value>
        >>          </property>
        >>      </configuration>
        > </details>
        > 
    - > `cp mapred-site.xml.template mapred-site.xml   vim `
        > - 这两个值不能随便改变
        > <details>
        > <summary><mark><font color=darkred>mapred-site.xml</font></mark></summary>
        > 
        >>
        >>      <configuration>
        >>          <property>
        >>              <name>mapreduce.framework.name</name>
        >>              <value>yarn</value>
        >>          </property>
        >>      </configuration>
        > </details>
        > 
    - > `vim ${HADOOP_DIR}/etc/hadoop/yarn-site.xml`
        > - 第一个是主机名，第二个配置固定
        > <details>
        > <summary><mark><font color=darkred>yarn-site.xml</font></mark></summary>
        > 
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
        > </details>
        > 
    - > `vim ${HADOOP_DIR}/etc/hadoop/slaves `
        > - DataNode 所在的主机名
        >>      hadoopalone
    - > `vim /etc/profile` 环境变量
        >>      export HADOOP_HOME=/root/software/hadoop-2.7.1
        >>      export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

- ## 启动hadoop
    - > <mark>第一次启动时需要格式化NameNode</mark>
        > - `hadoop namenode -format`
        > - 成功提示
        >>      common.Storage: Storage directory /root/software/hadoop-2.7.1/tmp/dfs/name has been successfully formatted.
        > - <mark>如果配置出错</mark>，则需要则配置的`vim core-site.xml` 中的 `hadoop.tmp.dir` 的目录删除之后再重新执行格式化 `hadoop namenode -format`
    - > `start-all.sh`
        > - 若成功，使用JPS 会有以下5 个进程
        >>      3155 NodeManager
        >>      2646 NameNode
        >>      2919 SecondaryNameNode
        >>      3064 ResourceManager
        >>      2735 DataNode
        - > `NameNode` HDFS 核心节点，负责管理DataNode 以及存储元数据的
            > - 全分布式中也最多只能有两个
        - > `DataNode` HDFS 存储单元
            > - 负责存储实际的分块数据
        - > `ResouceManage` 在1.0 中被 称为`JobTracker` 
            > - 负责管理NodeManager 同时对外接收请求
        - > `NodeManager` 在1.0 中被称为`TaskTracker`
            > - 负责执行计算任务，它包括mapTask 和reduceTask
    - > 浏览器管理
        > - `http://192.168.220.130:50070`















