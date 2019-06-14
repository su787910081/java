package cn.tedu

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils

object DemoDriver {
    def main(args: Array[String]): Unit = {
        val zk = "teach01:2181,teach02:2181,teach03:2181"
        // 1.创建sc
        val conf = new SparkConf();
        conf.setAppName("Spark_Kafka_Demo")
        conf.setMaster("local[5]")
        val sc = new SparkContext(conf);
        // 2.创建ssc
        val ssc = new StreamingContext(sc,Seconds(3));
        // 3.消费kafka中的数据
        val stream = KafkaUtils.createStream(ssc, zk, "gx1", Map("fluxtopic"->1))
        // 4.处理数据
        stream.map( t => t._2 ).print()
        // 5.启动SparkStreaming
        ssc.start();
        ssc.awaitTermination();
    }
}



