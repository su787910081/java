# 概述
- ## 介绍
    - > SparkSql 是将RDD 封装成一个DataFrame 对象，这个对象就类似于关系型数据库中的表。
- ## DataFrame
    - > DataFrame 相当于数据库中的一个表对象
    - > RDD中的每一个元素作为一个Tuple ，Tuple 中的每一个元素作为一个数据列
    - > 对RDD 中的每一列添加列名，以创建一个DataFrame 对象
    - > 一个DataFrame 对象通过方法 registerTempTable 来指定一个表名
- ## DataFrame 与RDD 之间的转换
    - > RDD rdd;  rdd.toDF()
    - > DataFrame df; df.toJavaRDD()


# 处理txt 源文件
> <details>
> 
>       scala> val r1 = sc.makeRDD(List(1,2,3,4), 2)
>       r1: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[0] at makeRDD at <console>:24
>       
>       // -- 将一个RDD 转换成一个表结构(DataFrame)，表结构的列名为: "id"
>       scala> val t1 = r1.toDF("id")
>       19/06/21 15:30:27 WARN ObjectStore: Version information not found in metastore. >   hive.metastore.schema.verification is not enabled so recording the schema version 1.2.0
>       19/06/21 15:30:28 WARN ObjectStore: Failed to get database default, returning NoSuchObjectException
>       t1: org.apache.spark.sql.DataFrame = [id: int]
>       
>       scala> t1.show
>       +---+
>       | id|
>       +---+
>       |  1|
>       |  2|
>       |  3|
>       |  4|
>       +---+
>       
>       
>       // -- 多列的表结构
>       // -- 多列组成一个元组，元组的每一个元素为一列的数据
>       scala> val r2 = sc.makeRDD(List((1, "rose"), (2, "tom"), (3, "jim"), (4, "jary")))
>       r2: org.apache.spark.rdd.RDD[(Int, String)] = ParallelCollectionRDD[5] at makeRDD at <console>:24
>       
>       // -- 为每一列依次定义一个列名
>       scala> val t2 = r2.toDF("id", "name")
>       t2: org.apache.spark.sql.DataFrame = [id: int, name: string]
>       
>       scala> t2.show
>       +---+----+
>       | id|name|
>       +---+----+
>       |  1|rose|
>       |  2| tom|
>       |  3| jim|
>       |  4|jary|
>       +---+----+
> </details>

# 处理json 源文件
> <details>
>
>       scala> import org.apache.spark.sql.SQLContext
>       import org.apache.spark.sql.SQLContext
>       
>       scala> val sqc = new SQLContext(sc)
>       warning: there was one deprecation warning; re-run with -deprecation for details
>       sqc: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@22587721
>       
>       scala> val tb4 = sqc.read.json("file:///home/people.json")
>       tb4: org.apache.spark.sql.DataFrame = [age: bigint, id: bigint ... 1 more field]
>       
>       scala> tb4.show
>       +---+---+-----+
>       |age| id| name|
>       +---+---+-----+
>       | 18|  1|  leo|
>       | 19|  2| jack|
>       | 17|  3|marry|
>       +---+---+-----+
> </details>

# 可以处理parquest 格式的文件(了解)

# 从数据库中获取数据
- > 将mysql 的驱动jar上传到spark的jars目录下
- > 重启spark服务
- > 进入spark客户端
- > 执行代码，比如在Mysql数据库下，有一个test库，在test库下有一张表为tabx
    > <details>
    >
    >       scala> import org.apache.spark.sql.SQLContext
    >       import org.apache.spark.sql.SQLContext
    >       
    >       scala> val sqc = new SQLContext(sc);
    >       warning: there was one deprecation warning; re-run with -deprecation for details
    >       sqc: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@55adcf9e
    >       
    >       scala>  val prop = new java.util.Properties
    >       prop: java.util.Properties = {}
    >       
    >       //-- 指定数据库的用户名
    >       scala> prop.put("user","root")
    >       res0: Object = null
    >       
    >       //-- 指定数据库的密码
    >       scala>  prop.put("password","root")
    >       res1: Object = null
    >       
    >       //-- 连接到数据库中的表(tb_cart)
    >       scala> val tb_cart = sqc.read.jdbc("jdbc:mysql://hadoop01:3306/jtdb","tb_cart",prop)
    >       tb_cart: org.apache.spark.sql.DataFrame = [id: bigint, user_id: bigint ... 7 more fields]
    >       
    >       scala> tb_cart.show
    >       +---+-------+----------+--------------------+--------------------+----------+---+--------------------+--------------------+
    >       | id|user_id|   item_id|          item_title|          item_image|item_price|num|             created|             updated|
    >       +---+-------+----------+--------------------+--------------------+----------+---+--------------------+--------------------+
    >       | 57|      7|    562379|三星 W999 黑色 电信3G手机...|http://image.jt.c...|   4299000|  4|2017-03-24 16:07:...|2017-03-24 16:45:...|
    >       | 58|      7|1474391948|华为(HUAWEI) Mate9 ...|http://image.jt.c...|    394800|  3|2017-03-30 18:12:...|2017-03-30 18:12:...|
    >       +---+-------+----------+--------------------+--------------------+----------+---+--------------------+--------------------+
    >
    > </details>


# SQL
> <details>
>
>       scala> val r1 = sc.makeRDD(List((2,"rose"), (3,"tom"), (1,"jim"), (4,"jary")))
>       r1: org.apache.spark.rdd.RDD[(Int, String)] = ParallelCollectionRDD[0] at makeRDD at <console>:24
>
>       scala> val t1 = r1.toDF("id", "name")
>       t1: org.apache.spark.sql.DataFrame = [id: int, name: string]
>
>       scala> t1.select("id").show
>       +---+
>       | id|
>       +---+
>       |  2|
>       |  3|
>       |  1|
>       |  4|
>       +---+
>
>
>       scala> t1.select("id", "name").show
>       +---+----+
>       | id|name|
>       +---+----+
>       |  2|rose|
>       |  3| tom|
>       |  1| jim|
>       |  4|jary|
>       +---+----+
>
>
>       scala> t1.select("id","name").where($"name"==="tom").show
>       +---+----+
>       | id|name|
>       +---+----+
>       |  3| tom|
>       +---+----+
>
>       scala> t1.select("id","name").orderBy($"id".desc).show
>       +---+----+
>       | id|name|
>       +---+----+
>       |  4|jary|
>       |  3| tom|
>       |  2|rose|
>       |  1| jim|
>       +---+----+
> </details>

# 聚合函数
> <details>
>
>       scala> val r2 = sc.makeRDD(List((1,350), (2,200), (1,100), (2,150), (1,500), (2,300)))
>       r2: org.apache.spark.rdd.RDD[(Int, Int)] = ParallelCollectionRDD[15] at makeRDD at <console>:24
>
>       scala> val t2 = r2.toDF("month", "profit")
>       t2: org.apache.spark.sql.DataFrame = [month: int, profit: int]
>
>       scala> t2.show
>       +-----+------+
>       |month|profit|
>       +-----+------+
>       |    1|   350|
>       |    2|   200|
>       |    1|   100|
>       |    2|   150|
>       |    1|   500|
>       |    2|   300|
>       +-----+------+
>
>
>       //-- 聚合函数里面对列做操作，需要对列名添加一个 "$" 前缀
>       scala> t2.select("month","profit").orderBy($"month",$"profit").show
>       +-----+------+
>       |month|profit|
>       +-----+------+
>       |    1|   100|
>       |    1|   350|
>       |    1|   500|
>       |    2|   150|
>       |    2|   200|
>       |    2|   300|
>       +-----+------+
>
>
>       scala> t2.groupBy("month").max("profit").show
>       +-----+-----------+                                                             
>       |month|max(profit)|
>       +-----+-----------+
>       |    1|        500|
>       |    2|        300|
>       +-----+-----------+
>
>
>       scala> t2.groupBy("month").count.show
>       +-----+-----+                                                                   
>       |month|count|
>       +-----+-----+
>       |    1|    3|
>       |    2|    3|
>       +-----+-----+
>
>       //-- 使用agg() 可以同时处理多个聚合函数
>       scala> t2.groupBy("month").agg(max("profit"), min("profit"), count("*")).show
>       +-----+-----------+-----------+--------+
>       |month|max(profit)|min(profit)|count(1)|
>       +-----+-----------+-----------+--------+
>       |    1|        500|        100|       3|
>       |    2|        300|        150|       3|
>       +-----+-----------+-----------+--------+
> </details>

# 连接查询
> <details>
>
>       scala> val dept=sc.parallelize(List((100,"caiwubu"),(200,"yanfabu"))).toDF("deptid","deptname")
>       dept: org.apache.spark.sql.DataFrame = [deptid: int, deptname: string]
>       
>       scala> val emp=sc.parallelize(List((1,100,"zhang"),(2,200,"li"),(3,300,"wang"))).toDF("id","did","name")
>       emp: org.apache.spark.sql.DataFrame = [id: int, did: int ... 1 more field]
>       
>       scala> dept.join(emp,$"deptid" === $"did").show
>       +------+--------+---+---+-----+                                                 
>       |deptid|deptname| id|did| name|
>       +------+--------+---+---+-----+
>       |   100| caiwubu|  1|100|zhang|
>       |   200| yanfabu|  2|200|   li|
>       +------+--------+---+---+-----+
>       
>       
>       //-- 左链接
>       scala> dept.join(emp,$"deptid" === $"did","left").show
>       +------+--------+---+---+-----+                                                 
>       |deptid|deptname| id|did| name|
>       +------+--------+---+---+-----+
>       |   100| caiwubu|  1|100|zhang|
>       |   200| yanfabu|  2|200|   li|
>       +------+--------+---+---+-----+
>       
>       //-- 右连接
>       scala> dept.join(emp,$"deptid" === $"did","right").show
>       +------+--------+---+---+-----+                                                 
>       |deptid|deptname| id|did| name|
>       +------+--------+---+---+-----+
>       |  null|    null|  3|300| wang|
>       |   100| caiwubu|  1|100|zhang|
>       |   200| yanfabu|  2|200|   li|
>       +------+--------+---+---+-----+
> </details>

# 数组类型
> <details>
>
>       scala> val r3 = sc.makeRDD(List(("zhang",Array("bj","sh")),("li",Array("sz","gz"))))
>       r3: org.apache.spark.rdd.RDD[(String, Array[String])] = ParallelCollectionRDD[82] at makeRDD at <console>:24
>
>       scala> val t3 = r3.toDF("name","addrs")
>       t3: org.apache.spark.sql.DataFrame = [name: string, addrs: array<string>]
>
>       scala> t3.show
>       +-----+--------+
>       | name|   addrs|
>       +-----+--------+
>       |zhang|[bj, sh]|
>       |   li|[sz, gz]|
>       +-----+--------+
>
>       //-- 使用 selectExpr
>       scala> t3.selectExpr("name","addrs[0]").show
>       +-----+--------+
>       | name|addrs[0]|
>       +-----+--------+
>       |zhang|      bj|
>       |   li|      sz|
>       +-----+--------+
> </details>

# 结构体类型

> <details>
>
>       [root@Hadoop01 home]$ cat users.json
>       {"name":"陈晨","address":{"city":"西安","street":"南二环甲字1号"}}
>       {"name":"娜娜","address":{"city":"西安","street":"南二环甲字2号"}}
>
>
>
>       scala> import org.apache.spark.sql.SQLContext
>       import org.apache.spark.sql.SQLContext
>
>       scala> val sqc = new SQLContext(sc)
>       warning: there was one deprecation warning; re-run with -deprecation for details
>       sqc: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@16eef58a
>
>       scala> val t5 = sqc.read.json("file:///home/users.json")
>       t5: org.apache.spark.sql.DataFrame = [address: struct<city: string, street: string>, name: string]
>
>       scala> t5.select("name","address.street").show
>       +----+-------+
>       |name| street|
>       +----+-------+
>       |  陈晨|南二环甲字1号|
>       |  娜娜|南二环甲字2号|
>       +----+-------+
>
>
>       scala> t5.count
>       res16: Long = 2
>
>       scala> t5.first
>       res17: org.apache.spark.sql.Row = [[西安,南二环甲字1号],陈晨]
>
>       scala> t5.take(2)
>       res18: Array[org.apache.spark.sql.Row] = Array([[西安,南二环甲字1号],陈晨], [[西安,南二环甲字2号],娜娜])
> </details>

# 类SQL 操作
- > 完全跟SQL 语言一样的操作
    >>      scala> val r2 = sc.makeRDD(List((1,350), (2,200), (1,100), (2,150), (1,500), (2,300)))
    >>      r2: org.apache.spark.rdd.RDD[(Int, Int)] = ParallelCollectionRDD[0] at makeRDD at <console>:24
    >> 
    >>      scala> val t2 = r2.toDF("month", "profit")
    >>      t2: org.apache.spark.sql.DataFrame = [month: int, profit: int]
    >> 
    >>      scala> t2.show
    >>      +-----+------+
    >>      |month|profit|
    >>      +-----+------+
    >>      |    1|   350|
    >>      |    2|   200|
    >>      |    1|   100|
    >>      |    2|   150|
    >>      |    1|   500|
    >>      |    2|   300|
    >>      +-----+------+
    >> 
    >> 
    >>      scala> t2.registerTempTable("tab2")
    >>      warning: there was one deprecation warning; re-run with -deprecation for details
    >> 
    >>      scala> import org.apache.spark.sql.SQLContext
    >>      import org.apache.spark.sql.SQLContext
    >> 
    >>      scala> val sqc = new SQLContext(sc)
    >>      warning: there was one deprecation warning; re-run with -deprecation for details
    >>      sqc: org.apache.spark.sql.SQLContext = org.apache.spark.sql.SQLContext@55f0ba66
    >> 
    >> 
    >>      scala> sqc.sql("SELECT * FROM tab2")
    >>      res4: org.apache.spark.sql.DataFrame = [month: int, profit: int]
    >> 
    >>      scala> sqc.sql("SELECT * FROM tab2").show
    >>      +-----+------+
    >>      |month|profit|
    >>      +-----+------+
    >>      |    1|   350|
    >>      |    2|   200|
    >>      |    1|   100|
    >>      |    2|   150|
    >>      |    1|   500|
    >>      |    2|   300|
    >>      +-----+------+


类似hive方式的操作
scala>val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
scala>hiveContext.sql("create table if not exists zzz (key int, value string) row format delimited fields terminated by '|'")
scala>hiveContext.sql("load data local inpath 'file:///home/software/hdata.txt' into table zzz")
scala>hiveContext.sql("select key,value from zzz").show







