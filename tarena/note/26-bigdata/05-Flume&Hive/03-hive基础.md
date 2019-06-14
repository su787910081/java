

- ## 基础
    - > hive 就是一个工具，并不是服务
    - > hive 的运行绝对依赖hadoop ，所以必须要有hadoop

- ## 遇到的问题
    - > 忘记在hive 中创建数据库，直接创建外部表失败。
        > - 我们在使用hive 的时候，元数据是存放在mysql 数据库中的，但是我们想要用HADOOP 中的数据时，需要在hive 中创建我们自己的数据库

- ## 内部表、外部表
    - > 内部表
        > - 内部表在HDFS 的'/user' 下面
        > - 需要先创建一个内部表
        >>      CREATE TABLE stu2(id INT, name STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ';
        > - 加载本地文件到hive 数据库表中
        >>      LOAD DATA LOCAL INPATH '/home/data.txt' INTO TABLE stu;
        > - 加载本地文件到表中，指定分区字段
        >>      LOAD DATA LOCAL INPATH '/home/cn.txt' OVERWRITE INTO TABLE book PARTITION(title='cn');
        >>> - title 是指定创建的分区
        > - 按其个已存在的表来创建一个新表，表结构相同，没有数据
        >>      CREATE TABLE stu2 LIKE SU;
        > - 将查询的结果插入到指定的表中
        > - 这个会转成MapReduce 来处理
        >>      INSERT OVERWRITE TABLE stu3 SELECT * FROM stu2;
        > - 将查询的结果存储到本地，并且可以指定存储到本时的分隔符
        >>      INSERT OVERWRITE LOCAL DIRECTORY '/home/stu' ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' SELECT * FROM s1;
        > - 将查询的结果插入到HDFS（只是少了一个local）
        >>      INSERT OVERWRITE DIRECTORY '/stu' ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' SELECT * FROM s1;

    - > 外部表
        > - HDFS上的目录（location '/score'）下面可以有多个文件，但是这些文件的格式必须一样。
        >>       CREATE EXTERNAL TABLE s1(id INT, name STRING, score INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' LOCATION '/score';

- ## 分区表
    - > 内部表和外部表都可以是分区表
    - > 分区表的最大作用就是避免全表查询
    - > 内部分区表
        > - 一个分区会被认为是一个表的字段
        > - 下面的示例中使用了 overwrite 关键字会覆盖之前的数据
        >>       CREATE TABLE book(id INT, name STRING) PARTITIONED BY (title STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ';
        >>
        >>       LOAD DATA LOCAL INPATH '/home/cn.txt' OVERWRITE INTO TABLE book PARTITION(title='cn');
        >>
        >>       SELECT * FROM book WHERE title = 'cn';
    - > 内部表相同分区插入会被覆盖(overwrite 关键字)
    - > 在已存在分区表中添加一个相同目录结构的分区数据，将这个目录添加到这个分区表中进行管理。
        > - 显示分区
        >>      SHOW PARTITIONS book;
        > - 添加分区
        >> - 方式一
        >>>      ALTER TABLE book ADD PARTITION(title='jp') LOCATION '/user/hive/warehouse/zebra.db/book/title=jp';
        >> - 方式二
        >>>       MSCK REPAIR TABLE book;
        >>> - 自动查找相关表下面的相关分区目录，符合分区结构的目录文件名就会被 添加到分区表中
        > - 删除分区
        >>       ALTER TABLE book DROP PARTITION(title='jp');
        > - 修改分区名
        >>       ALTER TABLE book PARTITION(title='ko') RENAME TO PARTITION(title='om');
    - > 外部分区表
        > - HDFS 目录结构   `/weblog/reporttime=2018-08-26`
        > - 创建分区表通过HDFS 的目录结构
        >>       CREATE EXTERNAL TABLE w1(id INT, name STRING) PARTITIONED BY(reporttime STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ' ' LOCATION '/weblog';
        > - 添加分区(两种方式，短的方式有时候不管用，那就只能用长的方式)
        >>        MSCK REPAIR TABLE w1;

- ## 常用字符串操作函数
    - > REGEXP_REPLACE(string A, string B, string C)
        > - 将字符串A中的符合java正则表达式B的部分替换为C。
        > - 注意，在有些情况下要使用转义字符,对需要转义的字符，用\[\]，比如\[*\]，类似oracle中的regexp_replace函数。
        > - 示例：将'foobar' 中的'oo|ar' 换成 ''
        >>      hive> SELECT REGEXP_REPLACE('foobar', 'oo|ar', '')；
        >>      OK
        >>      fb
    - > REGEXP_EXTRACT(string subject, string pattern, int index)
        > - 提取字符
        > - 第二个参数是正则表达式
        >> - 这个正则表达式中，如果用`()`括起来的，那么就作为项被提取
        > - 第三个参数是第几个`()` 中的内容，如果指定为0 则是整个匹配的字符串
        > - `(.*?)` 表示非贪婪的，只取到最短路径。如果不加`?`则匹配到的就是 `A:京东,裙子`
        >>      hive> SELECT REGEXP_EXTRACT('{A:京东,裙子:1}', '[{](.*?):(.*),(.*):([0-9]*)', 4);
        >>      OK
        >>      1
        > - 直接作用于表字段
        >>     SELECT REGEXP_EXTRACT(info, '[{](.*?):(.*),(.*):([0-9]*)', 4) FROM item;


- ## 复杂类型
    - > array
        > - 创建一个外部表，位置在HDFS /a1 目录下面，指定元素之间的分隔符为' ' 数组 之间的分隔符为','
        > - 案例文件：array2.txt
        >>       CREATE EXTERNAL TABLE a1(info ARRAY<INT>, info2 ARRAY<STRING>) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' COLLECTION ITEMS TERMINATED BY ',' LOCATION '/a1';
        >>
        >>       SELECT SIZE(info) FROM a1;
        >>
        >>       SELECT info[0] FROM a1;
    - > map
        > - map 的元素之间的分隔符必须为'\t'否则可能 会有问题
        >>       CREATE EXTERNAL TABLE m1(info MAP<STRING, INT>) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\T' MAP KEYS TERMINATED BY ',' LOCATION '/m1';
        > - 使用  
        >>       SELECT info['tom'] FROM m1 WHERE info['tom'] IS NOT NULL;
        > - 去重(生成MR JOB 任务) `distinct`
        >>       SELECT DISTINCT(info['tom']) FROM m2 WHERE info['tom'] IS NOT NULL;
    - > struct
        > - 创建
        >>       CREATE EXTERNAL TABLE s1(info STRUCT<ID:INT, NAME:STRING>) ROW FORMAT DELIMITED COLLECTION ITEMS TERMINATED BY ' ' LOCATION '/struct';
        >>
        >>       SELECT info.name FROM s1;



- ## 平时练习的一些SQL
    - > 小数与整数一起匹配
        > - `SELECT REGEXP_EXTRACT('{A:京东,裙子:1}', ':([0-9]+[\.]{0,1}[0-9]*)', 1)`
    - > 按一个列进行分区(分组)，然后在这个组里面按某个字段进行排序，并添加一个列排序的序号
        > - PARTITION BY 指定按某个字段进行分区(分组)
        > - ORDER BY 按指定规则排序
        > - ROW_NUMBER 生成一个序号，但是序号是连接的，相等的也连接。  ==>  " 1 2 3 4 5 6"
        >> - `SELECT category, name, amount, ROW_NUMBER() OVER(PARTITION BY category ORDER BY amount) AS rn FROM item02;`
        > - RANK 与ROW_NUMBER 一样都是生成一个序号，但是相同的序号会同相同的值，下一个序号会被跳过。 ==>  "1 2 2 4 4 6" 
        >> `SELECT category, name, amount, RANK() OVER(PARTITION BY category ORDER BY amount) AS rn FROM item02;`
