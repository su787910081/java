
- # hive - mysql 
    - > 搜索安装的MYSQL
        >>     rpm -qa | grep Percona
        >>     rpm -qa | grep -i mysql
        >>     rpm -qa | grep MySql
    - > 卸载
        >>     rpm -ev --nodeps Percona-Server-56-debuginfo-5.6.24-rel72.2.el6.x86_64
    - > 删除历史数据
        >>     rm -rf /var/lib/mysql/
        >>     rm -rf  /etc/my.cnf.d/
    - > 安装mysql
        >>     rpm -ivh
    - > 密码
        >>     初始密码: /root/.mysql_secret
        >>     使用初始密码修改密码为root： mysqladmin -u root -p password root
    - > mysql 默认配置(`vim /etc/my.cnf`)
        >>
        >>       [client]
        >>       default-character-set=utf8
        >>       [mysql]
        >>       default-character-set=utf8
        >>       [mysqld]
        >>       character_set_server=utf8
        >>       sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
        >> 
    - > 添加用户权限
        >>     mysql> grant all privileges on *.* to 'root'@'hadoopalone' identified by 'root' with grant option;
        >>     mysql> grant all on *.* to 'root'@'%' identified by 'root';
        >>     mysql> flush privileges;
    - > <mark>在Mysql 中创建一个供Hive 使用的专用数据库，字符集必须是 latin1</mark>
        >>     mysql> create database hive character set latin1;



- # hive 元数据存到mysql 数据库中
    - ## <mark>在使用hive 连接mysql 数据库的时候，必须要先在mysql 中创建一个新的数据库给hive 使用。并且必须是latin1 字符集。</mark>
    - ## 将mysql 驱动添加到 ${HIVE}/lib/ 目录中
        > - `mysql-connector-java-5.1.34-bin.jar`
    - > 添加一个配置文件`vim ${HIVE}/conf/hive-site.conf`(新文件)
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












create table nums(num array<int>) row format delimited collection items terminated by ',';

create table arrs(num array<int>, name array<string>) row format delimited fields terminated by '\t' collection items terminated by ',';

create table person (info map<string, int>) row format delimited map keys terminated by ',';

select info['tom'] from person where info['tom'] is not null;


// 从words 表中查询 数据，将每一行数据以空格进行切分，切分 出来的每一条数据插入到临时表ws 中
// 从ws 表中查询 ，查单词出现的次数，并以单词作为单位分组
select w, count(w) from (select explode(split(word, ' ')) w from words) ws group by w;



