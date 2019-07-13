


- ## 集群
    - > 准备三台计算机
    - > 配置(`conf/zoo.cfg`)
        >> - 指定一个数据目录
        >>>     dataDir=/root/software/zookeeper-3.4.8/tmp
        >> - 配置三个节点
        >>> - server是关键字, 后面跟的数字是myid
        >>> - 然后是主机IP，跟一个原子广播端口和一个选举端口
        >>> - `server.${myid(选举ID)}=${host}:{原子广播端口}:{选举端口}`
        >>>>        server.1=192.168.220.130:2888:3888
        >>>>        server.2=192.168.220.131:2888:3888
        >>>>        server.3=192.168.220.132:2888:3888
        >> - 配置选举ID(`myid`)
        >>> - 在指定的数据目录`dataDir=/root/software/zookeeper-3.4.8/tmp` 下面创建一个叫`myid` 的文件，并在里面添上相对应的选举ID值. 
        >>> - 也就是配置`server.1` 后面的数字1
    - > 启动
        > - `sh zkServer.sh start`

- ## 节点状态
    | 节点状态  | 状态说明   |
    | :----:   | :-----:   |
    | looking  |  选举状态  |
    | follower |   追随者   |
    | leader   | 领导者     |
    | abserver |   观察者   |

- ## 配置说明

    - > `tickTime  `
        >> - zookeeper中使用的基本时间单位, 毫秒值.  默认是2000 毫秒。 
    - > `dataDir   `
        >> - 数据目录. 可以是任意目录.  存放快照文件
    - > `dataLogDir`
        >> - log目录, 同样可以是任意目录. 如果没有设置该参数, 将使用和dataDir相同的设置.  
        >> - 日志主要存放的是一些操作记录，比如会话连接与断开，创建删除更新节点等操作都会记录在日志文件中。
    - > `clientPort`
        >> - 监听client连接的端口号  
    - > `initLimit `
        >> - 默认情况下是 10 * tickTime
        >> - zookeeper集群中的包含多台server, 其中一台为leader, 集群中其余的server为follower. 
        >> - initLimit参数配置初始化连接时, follower和leader之间的最长心跳时间. 此时该参数设置为5, 说明时间限制为5倍  tickTime, 即5*2000=10000ms=10s.
    - > `syncLimit `
        >> - leader 与follower  之间处理原子广播时，最大等待时间。
        >> - 该参数配置leader和follower之间发送消息, 请求和应答的最大时间长度. 此时该参数设置为2, 说明时间限制为2倍tickTime, 即4000ms. 
    - > `minSessionTime`  `maxSessionTime`






