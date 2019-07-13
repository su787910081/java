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
        // createStream(StreamingContext ssc, String zkHost, String groupName, Map topic)
        // 参数 topic, Key: 主题名; Value: 消费线程数  这里是MAP 所以可以多个
        val groupName = "gx1"
        val topics = Map("fluxtopic"->1) 
        val stream = KafkaUtils.createStream(ssc, zk, groupName, topics)
        // 4.处理数据 createStream 返回的数据是一个二元Tuple (null, "hello spark") 我们只需要获取到第二个单元数据就可以了。
        stream.map( t => t._2 ).print()
        // 5.启动SparkStreaming
        ssc.start();
        ssc.awaitTermination();
    }
}



