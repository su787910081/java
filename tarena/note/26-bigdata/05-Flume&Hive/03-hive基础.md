

- ## 基础



- ## 内部表、外部表
    - > 内部表
        > - 内部表在HDFS 的'/user' 下面
        > - 需要先创建一个内部表
        >>      create table stu2(id int, name string) row format delimited fields terminated by ' ';
        > - 加载本地文件到hive 数据库表中
        >>      load data local inpath '/home/data.txt' into table stu;
        > - 加载本地文件到表中，指定分区字段
        >> - title 是指定创建的分区
        >>      load data local inpath '/home/cn.txt' overwrite into table book partition(title='cn');
        > - 按其个已存在的表来创建一个新表，表结构相同，没有数据
        >>      create table stu2 like su;
        > - 将查询的结果插入到指定的表中
        > - 这个会转成MapReduce 来处理
        >>      insert overwrite table stu3 select * from stu2;
        > - 将查询的结果存储到本地，并且可以指定存储到本时的分隔符
        >>      insert overwrite local directory '/home/stu' row format delimited fields terminated by ' ' select * from s1;
        > - 将查询的结果插入到HDFS（只是少了一个local）
        >>      insert overwrite directory '/stu' row format delimited fields terminated by ' ' select * from s1;


    - > 外部表
        > - HDFS上的目录（location '/score'）下面可以有多个文件，但是这些文件的格式必须一样。
        >>       create external table s1(id int, name string, score int) row format delimited fields terminated by ' ' location '/score';

- ## 分区表
    - > 内部表和外部表都可以是分区表
    - > 分区表的最大作用就是避免全表查询
    - > 内部分区表
        > - 一个分区会被认为是一个表的字段
        > - 下面的示例中使用了 overwrite 关键字会覆盖之前的数据
        >>       create table book(id int, name string) partitioned by (title string) row format delimited fields terminated by ' ';
        >>
        >>       load data local inpath '/home/cn.txt' overwrite into table book partition(title='cn');
        >>
        >>       select * from book where title = 'cn';
    - > 内部表相同分区插入会被覆盖(overwrite 关键字)
    - > 在已存在分区表中添加一个相同目录结构的分区数据，将这个目录添加到这个分区表中进行管理。
        > - 显示分区
        >>      show partitions book;
        > - 添加分区，方式一
        >>       alter table book add partition(title='jp') location '/user/hive/warehouse/zebra.db/book/title=jp';
        > - 添加分区，方式二
        > - 自动查找相关表下面的相关分区目录，符合分区结构的目录文件名就会被 添加到分区表中
        >>       msck repair table book;
        > - 删除分区
        >>       alter table book drop partition(title='jp');
        > - 修改分区名
        >>       alter table book partition(title='ko') rename to partition(title='om');
    - > 外部分区表
        > - HDFS 目录结构   `/weblog/reporttime=2018-08-26`
        > - 创建分区表通过HDFS 的目录结构
        >>       create external table w1(id int, name string) partitioned by(reporttime string) row format delimited fields terminated by ' ' location '/weblog';
        > - 添加分区(两种方式，短的方式有时候不管用，那就只能用长的方式)
        >>        msck repair table w1;

- ## 复杂类型
    - > array
        > - 创建一个外部表，位置在HDFS /a1 目录下面，指定元素之间的分隔符为' ' 数组 之间的分隔符为','
        > - 案例文件：array2.txt
        >>       create external table a1(info array<int>, info2 array<string>) row format delimited fields terminated by '\t' collection items terminated by ',' location '/a1';
        >>
        >>       select size(info) from a1;
        >>
        >>       select info[0] from a1;
    - > map
        > - map 的元素之间的分隔符必须为'\t'否则可能 会有问题
        >>       create external table m1(info map<string, int>) row format delimited fields terminated by '\t' map keys terminated by ',' location '/m1';
        > - 使用  
        >>       select info['tom'] from m1 where info['tom'] is not null;
        > - 去重(生成MR JOB 任务) `distinct`
        >>       select distinct(info['tom']) from m2 where info['tom'] is not null;
    - > struct
        > - 创建
        >>       create external table s1(info struct<id:int, name:string>) row format delimited collection items terminated by ' ' location '/struct';
        >>
        >>       select info.name from s1;




