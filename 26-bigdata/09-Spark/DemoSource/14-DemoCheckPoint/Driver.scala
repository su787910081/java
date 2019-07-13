package cn.tedu.checkpoint

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("checkpoint")
        
        val sc = new SparkContext(conf)
        
        // 设定检查点目录路径，一般是指定到HDFS 上。也可以写到本地
        // 当缓存数据丢失时，从此目录的文件中恢复数据
        sc.setCheckpointDir("D://temp/spark/data/checkpoint")
        
        val data = sc.textFile("D://temp/spark/data/topk.txt", 2)
        data.cache()
        
        val r1 = data.flatMap { _.split(" ") }
        val r2 = r1.map { (_, 1) }
        // 对于CheckPoint 来说cache 不是必须的
        // 但是如果有cache 的话，Spark 机制会优先去缓存中取数据
        // 如果缓存中没有数据，就会去CheckPoint 找
        r2.cache()  
        // 将此RDD 做检查点存储，存储到checkpointDir 目录下 
        // 如果目录生成了即可确定checkpoint 生效了
        r2.checkpoint()  
        val r3 = r2.reduceByKey { _ + _ }
        
        r3.foreach { println }
        
        r2.unpersist()  // 释放缓存资源
    }
}