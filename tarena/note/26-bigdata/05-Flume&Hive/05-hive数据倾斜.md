- ## 数据倾斜
    - > group by 产生的数据倾斜问题
        > - 调优参数(临时生效)，它会为其生产两个JOB 任务
        >       set hive.groupby.skewindata=true;
        > - 永久生效(conf/hive-site.xml)
        >>      <property>
        >>          <name>javax.jdo.option.ConnectionPassword</name>
        >>          <value>root</value>
        >>      </property>     
    - > join 产生数据倾斜问题
        > - 使用hive 做join 时，要求小表在前面(左)。
        > - `hive.mapjoin.smalltable.filessize` 默认是25MB，如果小于这个值则自动启用mapjoin
        >  - 手动启用join 数据倾斜调优
        >>       set hive.auto.convert.join = true;
    - > count、distinct 产生数据倾斜问题
        > - 优化前
        >>       select count (distinct id) from tablename;
        > - 优化后(产生了两个MR JOB)
        > - 第一个JOB 处理去重(通过分区会有多个Reduce )，第二个JOB 做计数(计数只能有一个reduce)。
        >>       select count(*) from (select distinct id from tablename) tmp;
        > - 默认情况下reduce 的个数是只有一个，但是可以通过参数的形式设置reduce 的个数
        > - 设置reduce 的个数，但是如果是count 的话，只能是一个
        >>       set mapred.reduce.task = 2;
    - > 调整切片数(map 任务)
        > - hive 底层自动将小文件合成一个切片来处理。
        > - 你可以将每个切片的大小调小来添加切片的数量。下面的这个参数的单位是字节(Byte)
        >>       set mapred.max.split.size = xxx
        > - 对于切片数(MapTask) 的调整，要根据实际业务来定,比如一个100MB 的文件，有1000W条数据，此时可以调整为10个MapTask。这样处理起来会更快一些。
    - > JVM 重用
        > - 参数(每个JVM 处理指定数量的任务再退出)
        >>       set mapred.job.reuse.jvm.num.tasks = 20(默认是1个)
    - > 启用严格模式
        > - 参数
        >>       set hive.mapred.mode=strict
    - > 关闭推测执行机制
        > - 参数
        >>       set mapreduce.map.speculative=false;
        >>       set mapreduce.reduce.speculative = false;
        >>       set hive.mapred.reduce.tasks.speculative.execution=false;
