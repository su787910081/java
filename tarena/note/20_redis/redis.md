


## 雪崩 与 缓存击穿(数据大量未命中)

- 海量数据的访问请求,一旦发起,将会涌入系统,如果缓存数据可命中率高,数据库压力减小,可以提供对外正常的数据处理的能力.当缓存由于各种原因,造成大量数据未命中,海量数据请求涌入数据库;造成数据库宕机--恢复重启--海量请求未消失--宕机--重启;








- 安装redis 

    > - tar -xf redis-3.2.11.tar.gz
    > - cd redis-3.2.11
    > - make && make install 

- 启动redis
    > - 默认启动
    >> - redis-server


- 使用redis
    > - redis-cli

    > - 退出
    >> - quit  |  exit
    > - 在客户端上停止服务器
    >> - shutdown


- redis 中的数据类型
    > - String:字符串类型;{"id":5,"age":25}
    > - Hash:具有对象结构的value类型 hincrby age +5
    > - List:链表集合;有头有尾有中间;
    > - Set:集合;
    > - Zset:有序集合;

- String 类型命令
    > - `keys * `
    >> - 表示查询当前存储空间中，所有存在的key;
    > - `set \<key\> \<value\>`
    > - `get \<key\> \<value\>`
    > - `select \<整数\>`
    >> - 默认情况下整数的取值区间[0, 15] ，表示当前的redis 服务取用哪个数据分库；功能不常用了。
    > - `exists \<key\>`
    >> - 表示当前内存中是否有你要的key 值存在
    >> - get 也可以判断 key 值的存在。
    >> - redis 默认情况 下value 的数据可以支持到512M。
    >> - 如果使用get 来判断是否有数据的话，它会先读取数据，如果 这个数据太大的话，那么就会造成浪费。
    > - `del \<key\>`
    >> - 将key 值的数据从内存删除
    >> - 主动删除数据时有人工成本；redis 有数据过时剔除的逻辑；
    >>> 1. 超时
    >>> 2. 自带的删除数据逻辑(LRU): lasted recent unused

- 辅助命令
    > - `type`
    >> - 查看当前数据类型
    > - `help`
    >> - 查看命令的使用，一般我们不用这个命令。要查看命令我们到管网就可以。
    > - `save`
    >> - 将当前服务的所有数据保存到持久化文件(dump.rdb)中
    >> - 这个dump.rdb 文件在安装程序目录中(使用 make && make install 的那个目录下面)
    > - `flushall`
    >> - 将当前服务的所有数据清空(包括持久化文件中的数据)，包括所有分库中的数据全部被清空。
    > - `flushdb`
    >> - 只清空当前分库的数据
    > - `incr \<num\>`  |  `decr \<num\>`
    >> - 自增与自减，步数是1
    > - `incrby \<key\> \<num\>`  |   `decrby \<key\> \<num\>`
    >> - 指定步数的自增与自减
    > - `append \<key\> \<value\>`
    >> - 在存在的key 对应的value 数据中拼接传递的内容
    > - `expire \<key\> 秒`
    >> - 设定当前key 对应的数据超时时间(单位: 秒)
    >> - 超时将会被删除
    > - `ttl \<key\>`
    >> - 查看倒计时，超时之后，ttl 的结果是什么？ 删除数据，key 的超时时间变成 -2
    >> - -1 表示永久数据
    > - `pexpire \<key\> 毫秒`
    >> - 精确超时，单位: 毫秒


- 批量操作
    > - `mset` | `mget`
    >> - 只能在本机节点执行，不支持分布式
    >> - 同时获取多个key 的value 或者同时设置多个数据对

- hash 数据类型
    > - 面向对象数据类型
    > - `hset user id 1`  |  `hget user id`
    >> - hash 数据类型的设定和读取
    > - `hexists user id`
    >> - 判断属性是否存在
    > - `hdel`
    >> - 删除属性
    > - `hkeys` | `hvals`
    >> - 只获取hash 数据的key 值对应的hashkey 或者 hashvalue
    > - `hincrby`   ||  没有自减命令
    >> - 对某个key 的value 进行自增
    > - `hlen`
    >> - 判断当前hash 类型数据的属性个数

- List 数据集合链
    > - ## 操作命令分左右，数据查看是上下
    > - `lpush`
    >> - 往一个链结构中添加数据
    > - `lrange`
    >> - 查看一个范围内的数据
    >>> - `lrange 0 3` 查看从0 到3 4 个数据
    >>> - `lrange 0 -1` 查看所有数据
    > - `rpush`
    >> - 从下插入数据链
    > - `lset`
    >> - 设定list中对应下标的元素值;
    > - `lrem`
    >> - 从key 对应的list 中删除count 个相同value 数据的元素，count 可以大于0， 小于0， 等于0 
    >> - 大于0 表示从上到下删除count 个元素
    >> - 小于0 表示从下到上删除count 个元素
    >> - 等于0 删除所有相同的元素
    >>> - `lrem mylist 0 two`
    > - `lpop`
    >> - 从头部删除一个元素，并返回
    > - `rpop` 
    >> - 从尾部删除一个元素，并返回
    > - `rpoplpush`
    >> - 从第一个list 的尾部移除元素，添加到第二个list 的头部
    >>> - `rpoplpush mylist mylist1`






