package cn.tedu.checkpoint

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
    def main(args: Array[String]): Unit = {

    }

    def demoCheckPoint() = {
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
        r2.cache()
        // 将此RDD 做检查点存储，存储到checkpointDir 目录下 
        // 如果目录生成了即可确定checkpoint 生效了
        r2.checkpoint()  
        val r3 = r2.reduceByKey { _ + _ }
        
        r3.foreach { println }
        
        r2.unpersist()  // 释放缓存资源
    }

    def func() = {
        val user1FilmSource=Map("m1"->2,"m2"->3,"m3"->1,"m4"->0,"m5"->1)
        
        val user2FilmSource=Map("m1"->1,"m2"->2,"m3"->2,"m4"->1,"m5"->4)
        
        val user3FilmSource=Map("m1"->2,"m2"->1,"m3"->0,"m4"->1,"m5"->4)
        
        val user4FilmSource=Map("m1"->3,"m2"->2,"m3"->0,"m4"->5,"m5"->3)
    
        val user5FilmSource=Map("m1"->5,"m2"->3,"m3"->1,"m4"->1,"m5"->2)
        
        println(cosMap(user1FilmSource, user2FilmSource))
        println(cosMap(user1FilmSource, user3FilmSource))
        println(cosMap(user1FilmSource, user4FilmSource))
        println(cosMap(user1FilmSource, user5FilmSource))
    }

    // Map 之间的余弦夹角
    def cosMap(m1:Map[String,Int],m2:Map[String,Int])={
        
        val u1u2=m1 zip m2
        
        val u1u2Fenzi=u1u2.map{x=>x._1._2*x._2._2}.sum
        
        val u1Fenmu=Math.sqrt(m1.map{case(k,v)=>v*v}.sum)
        val u2Fenmu=Math.sqrt(m2.map{case(k,v)=>v*v}.sum)
        
        val u1u2Cos=u1u2Fenzi/(u1Fenmu*u2Fenmu)
        
        u1u2Cos
    }

    // 计算两个向量的夹角余弦
    def cosFunc() = {
        val a1 = Array(3, 1, 4)
        val a2 = Array(2, 5, 6)

        // (3 * 2 + 1 * 5 + 4 * 6)
        // (a1(0) * a2(0) + a1(1) * a2(1) + a1(2) * a2(2)) / (a1向量模 * a2向量模)
        val a3 = a1 zip a2
        val fenzi = a3.map { x => x._1 * x._2 }.sum
        val a1Fenmu = Math.sqrt(a1.map { x => x * x }.sum)
        val a2Fenmu = Math.sqrt(a2.map { x => x * x }.sum)
        val res = fenzi / (a1Fenmu * a2Fenmu)
        println(res)
        // 向量之间的夹角余弦，绝对值 越趋近于1，相关性越强
        // 欧式距离，距离越短，相关性越强，距离=0时，说明完全相关
    }
}