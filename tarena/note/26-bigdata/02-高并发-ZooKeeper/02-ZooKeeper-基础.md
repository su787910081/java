
- ## 安装
    - > 单机安装
    - > 伪分布式
        >> 利用一个节点来模拟集群环境(意义不大)
    - > 全分布式

    
- ## ZooKeeper 特点
    - > 树状结构(Znode 树)
    - > 每一个节点称之为是一个Znode 节点
    - > 根节点是 `/`
    - > 所有的路径都必须以根节点为起点进行计算(即：无相对路径)
    - > 每一个Znode 节点都必须存储数据(可以是空数据，不能是null)
    - > 每一个路径都是唯一的
    - > Znode 树维系在内存以及磁盘上
        >> - 在内存中，目的是为了快速查询
        >> - 在磁盘上，是以Snapshot -- 快照形式存在的
    - > 理论上可以利用ZooKeeper 来存储大量的数据构建一个缓存机制，实际上很少这么做。
        >> 1. 每一个节点虽然可以存储数据，但是数据量有限(好像最多1M)
        >> 2. ZooKeeper 本身是用于做分布式的协调服务的框架
        >>> - 如果存储大量数据占用大量内存，则会降低协调服务的效率
    - > 在ZooKeeper 中，会对每一次的写操作(创建、更新与删除)分配一个全局递增的编号，称之为事务ID: Zxid(cZxid、mZxid、pZxid)
        >> - 事务ID 是一个64位二进制数，高32 位是EPOCHID，低32位才是实际的事务ID
    - > 临时节点不能挂载子节点


- ## ZooKeeper 伪分布式
    - > ZooKeeper 的伪分布式不需要任务的配置，只需要将 ${ZooKeeper}/conf/zoo_sample.cfg 复制为一个名为zoo.cfg 的文件即可。
        > - 或者你也可以对里面的一些配置进行修改
        > - 一般我们就只会修改一个dataDir

- ## 查看服务是否正常运行
    - > `jps `
        >>      6626 QuorumPeerMain
    - > `sh zkServer.sh status`
        >>      ZooKeeper JMX enabled by default
        >>      Using config: /root/software/zookeeper-3.4.8/bin/../conf/zoo.cfg
        >>      Mode: standalone
    - > 集群模式时，使用`sh zkServer.sh status` 查看状态时，Mode: 有两个结果(`follower` 或者`leader`)

- ## 远程连接到ZooKeeper 服务器
    - > `zkCli.sh -server 192.168.220.133:2181`

- ## 配置
    - > 修改`dataDir=/home/software/zookeeper-3.4.8/tmp`

- ## 命令
    - > zk: `create /picture 'picture servers'`
        >> - 根节点下创建一个子节点picture, 存储的数据是 picture servers
        >> - 创建成功，如下
        >>>      Created /picture
        >> - `ls /picture` 这时查看这个节点下是空的
        >>>       []
        >> - 创建一个不存储数据的节点
        >>> - `create /video ''`
        >> - `create -e /tmp ''` 临时节点
        >>> - 创建一个临时节点，当你退客户端之后此类型的节点将会丢失
        >> - `create -s /picture/phost ''`
        >>> - 创建持久顺序节点
        >> - `create -s -e /picture/tmp ''`
        >>> - 创建临时顺序节点
    - > zk: `ls /`
        >> - 查看指定节点下的数据
        >>>     [zookeeper]
    - > zd: `delete /video`
        >> - 删除一个节点
        >> - 节点必须为空节点，没有子节点。
    - > zk: `rmr /video`
        >> - 删除一个节点
        >> - 递归删除，可以将其下的子节点一起删除
    - > zk: `get /picture`
        >> - 获取picture 节点的数据以及节点信息
    - > zk: `set /picture 'hello picture'`
        >> - 更新节点数据

- ## 节点信息详细说明
    > - 节点数据
    >> - `picture servers`
    > - 节点信息: 
    >> - `cZxid = 0x2`  创建事务ID
    >> - `ctime = Sun May 12 18:55:30 PDT 2019`  创建时间
    >> - `mZxid = 0x2`  修改事务ID
    >> - `mtime = Sun May 12 18:55:30 PDT 2019` 修改时间
    >> - `pZxid = 0x2`  [子节点最新事务ID] 子节点的增删事务ID (更新时此值不会变化)
    >> - `cversion = 0` 记录子节点增删次数
    >> - `dataVersion = 0`  当前节点更新次数(即：使用set 命令的次数)
    >> - `aclVersion = 0`  记录节点的权限改变次数
    >> - `ephemeralOwner = 0x0`   节点类型持久节点，此值为：`0x0` 临时节点，此值为: sessionid
    >> - `dataLength = 15`    数据的字节个数
    >> - `numChildren = 0`    子节点个数


- ## 节点类型

    > |           | 持久                      | 临时                          |
    > |  ----     | ---                       | ----                          |
    > | 非顺序    | Persistent                |  Ephemeral_Sequential         |
    > | 顺序      | Persistent_Sequential     | Ephemeral                     |

    - > 临时节点
        > - 临时节点的生命周期和客户端会话绑定在一起，客户端会话失效，则这个节点就会被自动清除。


- ##  总结 - 特性
    - > 数据一致性：原子广播
    - > 顺序性：队列
    - > 实时性
    - > 可靠性：同步
    - > 原子性：一个请求在ZooKeeper要么执行要么不执行
    - > 过半性: 过半选举，过兴存活，过半服务

- ## 扩展：ZooKeeper 的危害



















