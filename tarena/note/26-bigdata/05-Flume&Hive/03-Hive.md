create table like stu;

mysql> grant all privileges on *.* to 'root'@'hadoopalone' identified by 'root' with grant option;

mysql> create database hive character set latin1;





<configuration>
    <property>
        <name>javax.jdo.option.ConnectionURL</name>
        <value>jdbc:mysql://hadoop01:3306/hive?createDatabaseIfNotExist=true</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionDriverName</name>
        <value>com.mysql.jdbc.Driver</value>
    </property>
    <property>
        <name>javax.jdo.option.ConnectionUserName</name> 
        <value>root</value> 
    </property>
    <property>
        <name>javax.jdo.option.ConnectionPassword</name>
        <value>root</value>
    </property>
</configuration>


grant all privileges on *.* to 'root'@'HiveHadoopAlone' identified by 'root' with grant option;


vim /etc/my.cnf
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
character_set_server=utf8
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES



create table nums(num array<int>) row format delimited collection items terminated by ',';

create table arrs(num array<int>, name array<string>) row format delimited fields terminated by '\t' collection items terminated by ',';

create table person (info map<string, int>) row format delimited map keys terminated by ',';

select info['tom'] from person where info['tom'] is not null;


// 从words 表中查询 数据，将每一行数据以空格进行切分，切分 出来的每一条数据插入到临时表ws 中
// 从ws 表中查询 ，查单词出现的次数，并以单词作为单位分组
select w, count(w) from (select explode(split(word, ' ')) w from words) ws group by w;



