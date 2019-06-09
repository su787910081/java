

# 将Hive 的元数据保存到Mysql 数据库中

- # hive 的安装使用
    - > 直接下载安装文件解压即可
        > - `apache-hive-1.2.0-bin.tar.gz`
    - > 启动
        > - hive 依赖hadoop ，所以在运行前必须要有hadoop 服务
        > - 在 bin 目录 下面
        >> - `sh hive`

- # hive 元数据存到mysql 数据库中

    - > 元数据存MYSQL
        > - <mark>必须要先在mysql 中创建一个新的数据库给hive 使用</mark>
        > - <mark>必须是latin1 字符集</mark>
        >>     mysql> CREATE DATABASE hive CHARACTER SET latin1;
    - ## 将mysql 驱动添加到 ${HIVE}/lib/ 目录中
        > - `mysql-connector-java-5.1.34-bin.jar`
    - > 添加一个配置文件`vim ${HIVE}/conf/hive-site.conf`(新文件)
        > <details>
        > <summary><mark>详细信息</mark></summary>
        >
        >>     <configuration>
        >>         <property>
        >>             <name>javax.jdo.option.ConnectionURL</name>
        >>             <value>jdbc:mysql://hadoop01:3306/hive?createDatabaseIfNotExist=true</value>
        >>         </property>
        >>         <property>
        >>             <name>javax.jdo.option.ConnectionDriverName</name>
        >>             <value>com.mysql.jdbc.Driver</value>
        >>         </property>
        >>         <property>
        >>             <name>javax.jdo.option.ConnectionUserName</name> 
        >>             <value>root</value> 
        >>         </property>
        >>         <property>
        >>             <name>javax.jdo.option.ConnectionPassword</name>
        >>             <value>root</value>
        >>         </property>
        >>     </configuration>
        > </details>
        >












create table nums(num array<int>) row format delimited collection items terminated by ',';

create table arrs(num array<int>, name array<string>) row format delimited fields terminated by '\t' collection items terminated by ',';

create table person (info map<string, int>) row format delimited map keys terminated by ',';

select info['tom'] from person where info['tom'] is not null;


// 从words 表中查询 数据，将每一行数据以空格进行切分，切分 出来的每一条数据插入到临时表ws 中
// 从ws 表中查询 ，查单词出现的次数，并以单词作为单位分组
select w, count(w) from (select explode(split(word, ' ')) w from words) ws group by w;



