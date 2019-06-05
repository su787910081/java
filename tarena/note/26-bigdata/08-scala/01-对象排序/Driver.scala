package day02.cn.tedu.ssort

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf
    conf.setMaster("local")
    conf.setAppName("ssort")
    
    val sc = new SparkContext(conf)
    
    val data = sc.textFile("D:/temp/spark/data/ssort.txt", 2)
    
    val r1 = data.map { line => 
      val r1 = line.split(" ")
      val col1 = r1(0)
      val col2 = r1(1).toInt
      (new Ssort(col1, col2), line)
    }
    

    val r2 = r1.sortByKey(true).map { _._2 }
    r2.foreach { println }
  }
}