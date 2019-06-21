package cn.tedu.ssort

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Driver {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("secondSort")

    val sc = new SparkContext(conf)

    val data = sc.textFile("F://data/ssort.txt", 1)
    val rs1 = data.map { line => { 
        val arr = line.split(" "); 
        (new SecondSort(arr(0), arr(1).toInt), line) 
      } 
    }
    
    // 按Key(SecondSort) 进行排序
    val rs2 = rs1.sortByKey(true)
    val rs3 = rs2.map{ _._2 }

    rs3.foreach { println }
  }
}