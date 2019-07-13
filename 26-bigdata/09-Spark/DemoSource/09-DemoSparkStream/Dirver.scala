package cn.tedu.stream

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds

object Driver {
    def main(args: Array[String]): Unit = {
    }

    def driverDemo01() = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("driver01")
        
        val sc = new SparkContext(conf)
        
        // 创建SparkStreamimg 上下文对象，用于接收数据源，并生成DStream(离散的数据流)
        // SparkStreaming 的批大小是时间单位
        val ssc = new StreamingContext(sc, Seconds(5))
        
        // 指定监听的数据源，常用的数据源
        // 1. 本地数据源
        // 2. HDFS 数据源
        // 3. Kafka 数据源
        // 4. Socket 数据源
        val data = ssc.textFileStream("hdfs://HBaseAlone:9000/stream")
        
        val result = data.flatMap { _.split(" ") }.map { (_, 1) }.reduceByKey{ _ + _ }
        
        result.print
        
        ssc.start()
        
        // 保持SparkStreaming 一直开启，直到用户手动中断
        ssc.awaitTermination()
    }

    // 学习SparkStreming 对历史数据的累加处理
    def driverDemo02()= {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("driver02")
        
        val sc = new SparkContext(conf)
        
        val ssc = new StreamingContext(sc, Seconds(5))
        
        // 为了实现对历史批次数据的累加，需要指定一个目录来存储历史数据
        // 这个路径可以是本地路径，也可以是HDFS 
        ssc.checkpoint("F://data/streamData")
        
        val data = ssc.textFileStream("hdfs://HBaseAlone:9000/stream")
        
        val r1 = data.flatMap { _.split(" ") }.map { (_, 1) }
        
        // 参数： 一个函数 (seq: Seq[Int], op: Option[?]) ⇒ Option[?]
        // 这个函数的参数：
        //      seq: 历史结果的一个集合
        //      op:  一个Option 对象，但是需要自己指定其泛型的类型
        val r2 = r1.updateStateByKey{ (seq, op:Option[Int]) => Some(seq.sum + op.getOrElse(0)) }
        
        r2.print
        ssc.start()
        
        ssc.awaitTermination()
    }

    // 学习SparkStreaming 的滑动窗口机制
    // 应用场景：每隔一段时间(滑动区间)计算下一个段时间(窗口长度)的数据
    // 注意: 窗口长度和滑动区间必须是batch size (批大小)的整数位
    def driverDemo03() = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("driver03")
        
        val sc = new SparkContext(conf)
        
        val ssc = new StreamingContext(sc, Seconds(5))
        
        // 窗口机制也是对历史数据进行累加，所以也需要指定目录
        ssc.checkpoint("F://data/windowdata")
        
        val data = ssc.textFileStream("hdfs://HBaseAlone:9000/stream")
        
        val r1 = data.flatMap { _.split(" ") }.map { (_, 1) }

        // 参数1：匿名函数，指定key 的Value 如何计算
        // 参数2：滑动区间
        // 参数3：窗口长度
        val r2 = r1.reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(10), Seconds(10))
        
        r2.print
        
        ssc.start
        
        ssc.awaitTermination()
    }
}