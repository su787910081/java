package day04.cn.tedu.statistic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.SparkConf

object Driver {
  def main(args: Array[String]): Unit = {
    f3
  }

  // 计算两个向量的夹角余弦
  def f3() = {
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

  // 通过代码计算两点之间的欧式距离
  def f2() = {
    val a1 = Array(3, 1, 4)
    val a2 = Array(2, 5, 6)

    val a3 = a1 zip a2
    val a4 = a3.map { x => (x._1 - x._2) * (x._1 - x._2) }
    val a5 = a4.sum
    val r1 = Math.sqrt(a5)
    println(r1)
  }

  def f1() = {
    // Spark MLlib 有个工具类: Statistic, 统计一组数据的各项基本指标
    // 要求数据类型: RDD[Vector]

    val conf = new SparkConf
    conf.setMaster("local")
    conf.setAppName("f1")

    val sc = new SparkContext(conf)

    // RDD[Int]
    val r1 = sc.makeRDD(List(1, 2, 3, 4, 5))
    val r2 = r1.map { num => Vectors.dense(num) }

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
}