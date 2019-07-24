

# 博客地址
- > 如下:
    > - `https://blog.csdn.net/lblblblblzdx/article/details/79760959`
    > - 博客主题: `"hive 的几种启动方式"`


## 将hive 作为一个服务器启动，使得我们可以使用hive 客户端从别的计算连接到这个hive 服务器
### Hive server
- > 配置详情，查看`Hive配置/hive-site-server.xml`
    > - 另外还需要在Hadoop 的`core-site.xml` 中添加一些配置
    >>      <property>
    >>          <name>hadoop.proxyuser.root.hosts</name>
    >>          <value>*</value>
    >>      </property>
    >>      <property>
    >>          <name>hadoop.proxyuser.root.groups</name>
    >>          <value>*</value>
    >>      </property>
    > - 此处这样设置的意思是，root用户提交的任务可以在任意机器上以任意组的所有用户的身份执行。
    > - 如果不设置，后续连接时会报错
    >>      org.apache.hadoop.ipc.RemoteException   >>      (org.apache.hadoop.security.authorize. >>     AuthorizationException):
    >>      User: root is not allowed to impersonate anonymous 


- > 启动命令
    > - hiveserver 端口号默认是10000
    > - 服务器启动: `hive --service hiveserver2`
    > - 连接客户端: `beeline -u jdbc:hive2://localhost:10000`
    > - hiveserver2会同时启动一个webui，端口号默认为10002，可以通过`http://localhost:10002/`访问








