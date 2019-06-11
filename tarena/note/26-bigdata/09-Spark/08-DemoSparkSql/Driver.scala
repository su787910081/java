package cn.tedu.sql

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

/**
 * 知识点：
 * 1. Spark 框架为结构化数据查询引入了SparkSql模块，通过此模块可以实现SQL 的交互式查询
 * 2. SparkSql 模块引入了DataFrame(数据块)数据结构，但底层仍然是RDD
 * 3. 对于DataFrame 的操作底层是对于RDD的操作
 */
object Driver {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("sql")
        
        val sc = new SparkContext(conf)
        
        // 创建SparkSql 的上下文对象，用于创建DataFrame, 以及完成对DataFrame 的sql 查询 
        val sqc = new SQLContext(sc)
        val r1 = sc.makeRDD(List(("tom", 20 ), ("rose", 18), ("jim", 30)))
        
        // 把RDD 转变为DataFrame
        // val df1 = sqc.createDataFrame(r1).toDF("name", "age")
        
        // 导入一个(SparkSql 上下文)对象的隐式转换包，作用是省略(sqc.createDataFrame(r1))
        import sqc.implicits._ 
        
        val df1 = r1.toDF("name", "age")
        // df1.show
        
        df1.registerTempTable("tb1")
        
        val result = sqc.sql("SELECT * FROM tb1 WHERE age > 18")
        // result.show()
        
        // 类型转换: DataFrame --> RDD
        val resultRDD = result.toJavaRDD
        
        // 将结果存到指定目录
        resultRDD.saveAsTextFile("F://data/sqlresult")
    }
}