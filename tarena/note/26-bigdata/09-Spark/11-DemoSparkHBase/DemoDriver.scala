package cn.tedu

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.RowFilter
import org.apache.hadoop.hbase.filter.RegexStringComparator
import org.apache.hadoop.hbase.util.Base64
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.protobuf.ProtobufUtil


object DemoDriver {
    val zkPath = "Hadoop01,Hadoop02,Hadoop03"
    val zkPort = "2181"

    def main(args: Array[String]): Unit = {
        demoScanHbase()
    }
    
    // 需要预先创建一个表: <create 'tabx1', 'cf1', 'cf2'>
    def demoWriteHbase() = {
        // 0.配置序列化器
        System.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        // 1.创建sc
        val conf = new SparkConf();
        conf.setAppName("spark_hbase_write")
        conf.setMaster("local[*]")
        // conf.setMaster("local[*]")
        val sc = new SparkContext(conf);
        // 2.准备配置信息
        val hbaseConf = HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum", zkPath)
        hbaseConf.set("hbase.zookeeper.property.clientPort", zkPort)
        val jobConf = new JobConf(hbaseConf)
        jobConf.setOutputFormat(classOf[TableOutputFormat])
        jobConf.set(TableOutputFormat.OUTPUT_TABLE, "tabx1")
        // 3.准备数据
        val p1 = new Put("rk1".getBytes);  // 行键："rk1"
        p1.add("cf1".getBytes, "c1".getBytes, "v1111".getBytes)  // 列族："cf1", 列名："c1", 值："v111"
        p1.add("cf1".getBytes, "c2".getBytes, "v1121".getBytes)
        val p2 = new Put("rk2".getBytes)
        p2.add("cf1".getBytes, "c1".getBytes, "v2111".getBytes)
        p2.add("cf1".getBytes, "c2".getBytes, "v2121".getBytes)
        var rdd = sc.makeRDD(Array(
            (new ImmutableBytesWritable, p1),
            (new ImmutableBytesWritable, p2)))
        // 4.写入hbase
        rdd.saveAsHadoopDataset(jobConf);
    }

    def demoReadHbase() = {
        // 1.创建sc
        val conf = new SparkConf()
        conf.setAppName("Spark_Hbase_Demo")
        conf.setMaster("local[5]")
        val sc = new SparkContext(conf)
        // 2.创建配置对象
        val hbaseConf = HBaseConfiguration.create();
        hbaseConf.set("hbase.zookeeper.quorum", zkPath)
        hbaseConf.set("hbase.zookeeper.property.clientPort", zkPort)
        hbaseConf.set(TableInputFormat.INPUT_TABLE, "tabx1")
        // 3.从hbase中消费数据
        val rdd = sc.newAPIHadoopRDD(hbaseConf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
        // 4.获取数据
        rdd.foreach(t=>{
        val result = t._2;
        val rk = new String(result.getRow)
        val v = new String(result.getValue("cf1".getBytes, "c1".getBytes))
        println(rk+"~"+v)
        })
    }


    def demoScanHbase() = {
        //1.创建sc
        val conf = new SparkConf()
        conf.setAppName("spark_hbase_scan")
        conf.setMaster("local[5]")
        val sc = new SparkContext(conf);
        //2.创建配置对象
        val hbaseConf = HBaseConfiguration.create()
        hbaseConf.set("hbase.zookeeper.quorum", zkPath)
        hbaseConf.set("hbase.zookeeper.property.clientPort", zkPort)
        hbaseConf.set(TableInputFormat.INPUT_TABLE, "tabx1");
        //3.创建scan
        val scan = new Scan()
        scan.setStartRow("rk2".getBytes)
        scan.setStopRow("rk5".getBytes)
        scan.setFilter(new RowFilter(CompareOp.EQUAL,new RegexStringComparator("^[^x]*$")))
        hbaseConf.set(TableInputFormat.SCAN, Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray()));    
        //4.查询hbase
        val rdd = sc.newAPIHadoopRDD(hbaseConf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
        //5.遍历结果
        rdd.foreach(t=>{
        val result = t._2
        val rk = new String(result.getRow)
        val v = new String(result.getValue("cf1".getBytes, "c1".getBytes))
        println(rk+"~"+v)
        })
    }




}




