package cn.tedu

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka.KafkaUtils
import _root_.kafka.serializer.StringDecoder

object DemoDriver {
    def main(args: Array[String]): Unit = {
    }

    // 高阶API 的普通用法，这个有丢失数据的风险
    def demo01() = {
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

    // 高阶API Receiver 模式
    // 这个模式有丢失数据的风险，所以为了解决这个问题需要引入WAL 预写日志机制。
    // 将kafka 数据备份到HDFS 中一份
    // 开启WAL 机制 conf.set("spark.streaming.receiver.writeAheadLog.enable", "true")
    // 写前日志必须要设置checkpoint 否则没有写的地方
    // 另外我们需要将缓存级别降低 StorageLevel.MEMORY_AND_DISK
    def demo02() = {
        // 1.创建sc
        val conf = new SparkConf();
        conf.setAppName("Spark_Kafka_Demo")
        conf.setMaster("local[5]")
        conf.set("spark.streaming.receiver.writeAheadLog.enable", "true")
        val sc = new SparkContext(conf);

        // 2.创建ssc
        val ssc = new StreamingContext(sc,Seconds(3));
        ssc.checkpoint("hdfs://tarena01:9000/wal/checkpoint")   // WAL 机制

        val zk = "teach01:2181,teach02:2181,teach03:2181"
        val groupName = "gx1"
        val topics = Map("fluxtopic"->1)

        // 3.消费kafka中的数据
        // createStream(StreamingContext ssc, String zkHost, String groupName, Map topic)
        // 参数 topic, Key: 主题名; Value: 消费线程数  这里是MAP 所以可以多个
        // 默认的存储级别为：MEMORY_AND_DISK_SER_2
        val dStream = KafkaUtils.createStream(ssc, zk, groupName, topics, StorageLevel.MEMORY_AND_DISK)

        // 4.处理数据 createStream 返回的数据是一个二元Tuple (null, "hello spark") 我们只需要获取到第二个单元数据就可以了。
        dStream.map( t => t._2 ).print()

        // 5.启动SparkStreaming
        ssc.start();
        ssc.awaitTermination();
    }

    // kafka + sparkstreaming direct 模式
    def demo06() = {
        val conf = new SparkConf()
        conf.setAppName("demo06")
        // conf.setMaster("local[*]")

        val sc = new SparkContext(conf)
        val ssc = new StreamingContext(sc, Seconds(3))

        // 这里可以不设置checkpoint
        // 如果不设置则在只在内存中管理一份offset，但是一般都要设置
        ssc.checkpoint("hdfs://ns/WordCheckPoint")

        // Kafka 集群参数
        // val kafkaParameters = Map("metadata.broker.list" -> "tarena01:9092,tarena02:9092,tarena03:9092");
        val kafkaParameters = Map("metadata.broker.list" -> "tarena03:9092");
        val topics = Set("words")

        // [] 中的四个分别是Key-Value 的类型，以及解码类型
        // 返回的是一个DStream 类型，且里面的结构是一个Tuple(String, String)
        val ds:InputDStream[(String, String)]
        = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
        ssc, kafkaParameters, topics)

        val wordCount = ds.map{ _._2 }.flatMap(_.split(" ")).map((_, 1))

        wordCount.print()

        ssc.start()
        ssc.awaitTermination()
    }
}



