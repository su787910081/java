


- ## MYCAT 逻辑库
    - > ### 什么是逻辑 库？
        >> 逻辑库是在mycat 中的被识别的数据库，它将一些被分片成多个实际数据库合成一个库。它将对外提供这一个数据库的访问，这个数据库我们把它认为是一个逻辑库。

- ## 逻辑表
    - > ### 什么是逻辑表?
        >> 与逻辑库类似，它也是将多个实际表合成一个表来对外提供访问的这样一个数据库表。
    - > ### 分片表
        >> 分片表就是逻辑表的多个分片
    - > ### 非分片表
        >> 就是在实际数据库中的完整表，没有被分片
    - > ### ER 表
        >> 分布式存储,数据切分的数据库集群结构中,当需要关联查询时,如果表格设计非常复杂,父子表格关系很多,会导致,mycat大量执行跨库操作.
        >>> <details>
        >>> <summary><mark><font color=darkred>ER 表图示</font></mark></summary>
        >>> 
        >>> ![](./img/er_table.jpg)
        >>> </details>




- ## mycat 配置
    - > Mycat 中间件
        >> <details>
        >> <summary><mark><font color=darkred>高可用mycat 中间件图示</font></mark></summary>
        >> 
        >> ![](./img/mycat.jpg)
        >> </details>
    
    - > mycat 有两个配置文件(`server.xml` 和`schema.xml`)
    - ### `server.xml` 配置文件
        > - 在mycat 目录下有一个 `conf/server.xml` 配置文件。
        > - 这个配置文件与服务与系统配置有关的xml表格.设定当前启动在系统中的mycat进程对系统资源配置占用的内容,例如端口,例如cpu使用率.还可以指定登录mycat的用户名密码,连接数等等.
        > - 在这个配置文件里面，我们主要配置user 标签就可以了。
        >>     <user name="root">
        >>         <property name="password">rootpassword</property>
        >>         <property name="schemas">mycatStudy</property>
        >>     </user>
        >> - 这个标签可以有多个，具体多个是什么意思，暂时还不清楚。
        >> - `<user name="root">` 连接的数据库的用户名
        >> - `<property name="password">` 登录密码
        >> - `<property name="schemas">` 
        >>> - 当前用户登录后可以使用的逻辑库,多个逻辑库用","隔开
        >>> - 如果想要被客户端使用,必须在schema文件中对应相同名称设定一个schema标签的name 属性,否则无效.

    - ### `schema.xml`
        > ---
        > - ### schema 标签
        > - `<schema name="mycatStudy" checkSQLschema="true" sqlMaxLimit="100">`
        >> - `schema 标签:` 对逻辑库的设置
        >> - `name` 属性: 与server.xml 中的`<property name="schemas">`  对应
        >> - `chechSQLschema` 属性: true/false
        >>> - mycat在sql语句执行,进行拦截拼接. 
        >>> - 如果为true,执行的语句"select * from user"不需要拼接逻辑库名称;
        >>> - 如果为false,需要在执行时,手动拼写schema,db.user.
        >>> - 一般它的值为true
        >> - `sqlMaxLimit` 属性: 拼接limit语句，默认为100
        >>> - 如: select * from user 将被翻译成 select * from user limit 100;
        > ---
        > - ### table 标签
        > - `<schema><table name="user" dataNode="dn1,dn2" rule="auto-sharding-long" /></schema>`
        >> - 在schema 标签下面有一个table 标签，这个标签就是mycat 的一个逻辑表，用于分片
        >> - 这条标签的意义就是user 表分在"dn1"和"dn2"两个dataNode 中，按"auto-sharding-long" 的规则进行分片存储
        >> - `name="user"` 定义逻辑表的名称，这个保持与实际数据库中的表一致即可。
        >> - `dataNode="dn1,dn2"` 数据节点名称，这个与后面定义的`dataNode` 标签中的name 值相对应
        >> - `rule="auto-sharding-long"` 分片规则
        >>> - `auto-sharding-long` 它对应配置文件中的`autopartition-long.txt`
        >>> - 其他的自行学习了再来补充, 分片规则有接口，可以自定义
        > ---
        > - ### dataNode 标签
        > - `<dataNode name="dn1" dataHost="master" database="mycatStudy" />`
        >> - `dataNode标签:` 数据节点的信息获取
        >> - 这条标签的意义就是名为"dn1" 的数据节点，它连接名为"master" 的数据主机，连接的真实数据库为"mycatStudy"
        >> - 那么通过前面的table 标签加上这里指定的数据库，可以确定一张数据库表
        >> - 一个数据节点可以来自于不同的数据库,利用dataHost标签完成包装的. 它在这里起着承上启下的一个作用。
        >> - `name="dn1"` 当前data节点的代号在schema标签,table 标签中的属性可以定义指定连接的dataNode的名称. 承上
        >> - `dataHost="master"` 指向节点主机(虚拟的概念,一个节点主机可以维护多个真实数据库),对应dataHost标签中的name属性,名字. 启下
        >> - `database="mycatStudy"` 
        > ---
        > - ### dataHost 标签
        > - `<dataHost name="master" maxCon="1000" minCon="10" balance="1" writeType="0" dbType="mysql" dbDriver="native" switchType="1"  slaveThreshold="100">`
        >> - 这是一个数据主机，在它里面我们可以实现双机热备的数据库。
        >> - `writeType="0"` 和`switchType="1"` 现在基本定型
        >>> - 它们的主要作用就是进行故障切换。
        >>> - 可以利用这种高可用替换的机制，配置双机热备模式，一个数据分片保证可靠性。
        >> - `balance="1"`  balance: 0,1,2,3分别代表负载均衡,读写分离的规则;
        >>> - 0: 表示不开启读写分离,所有的读写操作都在当前可用的writeHost上
        >>> - 1: 全部的readHost和备用writeHost都参与读数据的负载均衡;在读数据的请求过多时,负责写的writeHost也能分担一小部分
        >>> - 2: 所有的读操作,都随机的在所有的writeHost和readHost上分配
        >>> - 3: 所有的读操作,都到writeHost对应的readHost上(启动作用的writeHost)
        >> - `name="master"` 提供给前面的dataNode 标签使用
        >> - `maxCon="1000"` `minCon="10"` 最在连接与最小连接数
        >> - `dbDriver="native"` 自动匹配




























