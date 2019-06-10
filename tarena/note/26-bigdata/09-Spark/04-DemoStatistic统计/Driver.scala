package cn.tedu.statistic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics

object Driver {
  
    def main(args: Array[String]): Unit = {

        
    }

    def demoStatistic01() = {
        // Spark MLlib 有个工具类: Statistic, 统计一组数据的各项基本指标
        // 要求数据类型: RDD[Vector]

        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("f1")

        val sc = new SparkContext(conf)

        // RDD[Int]
        val r1 = sc.makeRDD(List(1, 2, 3, 4, 5))
        val r2 = r1.map { num => Vectors.dense(num) }

        //--通过工具类,统计一组数据的基本指标
        val result = Statistics.colStats(r2)

        println(result.max)
        println(result.min)
        println(result.mean)
        println(result.variance) // 方差
        println(result.count)
        println(result.numNonzeros) // 返回数据集不为0 的个数
        println(result.normL1) // 返回 曼哈顿距离
        println(result.normL2) // 返回 欧式距离(两点之间的直线距离)  衡量数据之间的相似度
        // 衡量数据 之间的相似度：相关系数、向量之间的夹角余弦、欧式距离、曼哈顿距离、切比雪夫距离
        // 重点：向量之间的夹角余弦、欧式距离
    }

    // 通过代码计算两点之间的欧式距离
    def demoStatistic02() = {
        val a1=Array(3,1,4)
        val a2=Array(2,5,6)
        
        //--要求根据欧式距离的公式，算出距离值
        //--提示:①zip 拉链  ②Math.sqrt
        
        val a1a2=a1 zip a2
        
        val result=Math.sqrt(a1a2.map{x=>(x._1-x._2)*(x._1-x._2)}.sum)
        
        //--计算a1 a2两个向量的夹角余弦
        //(3*2+1*5+4*6)/(a1向量模  * a2向量模)
        
        val a1a2Fenzi=a1a2.map{x=>x._1*x._2}.sum
        val a1Fenmu=Math.sqrt(a1.map { x => x*x }.sum)
        val a2Fenmu=Math.sqrt(a2.map { x => x*x }.sum)
        
        val a1a2Cos=a1a2Fenzi/(a1Fenmu*a2Fenmu)
        println(a1a2Cos)
        
        //--向量之间的夹角余弦，绝对值越趋近于1,相关性越强
        //--欧式距离,距离越短，相关性越强，距离=0时，说明完全相关
    }
}