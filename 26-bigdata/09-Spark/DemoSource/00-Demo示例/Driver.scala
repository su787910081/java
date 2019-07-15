


object Driver {
    def main(args: Array[String]): Unit = {
        
    }

    // 以tcp 的形式接收数据并处理
    // 可以使用 Linux 命令模拟发送TCP 数据用于测试
    // Linux 命令: nc -lk 9999
    def demo01() = {
        val conf = new SparkConf()
        conf.setAppName("DriverSocketSpark")
        val sc = new SparkContext(conf)
        val ssc = new StreamingContext(sc, Seconds(3))  // 每3 秒一个批次
        ssc.checkpoint("hdfs://ns/WordCheckPoint")  // 设置检测点，保存历史数据

        val ds = ssc.socketTextStream("tarena03", 9999);
        val wordCount = ds.flatMap{ _.split(" ") }.map{ (_, 1) }
            // 对历史数据进行累计处理
            .updateStateByKey{ (seq, op:Option[Int]) => Some(seq.sum + op.getOrElse(0)) }

        wordCount.print()

        ssc.start()
        ssc.awaitTermination()
        ssc.stop()
    }

    // 对整个目录中的文件数据进行读取
    // 读取出来的数据是按文件名以及文件内容的形式存储的数据结构
    // groupByKey 处理的数据必须是(Key, Value) 结构的数据，然后按Key 进行分组。最终形成一个(Key, Array(Value))
    def demo02() = {
        // 读取整个目录中的所有文件
        val conf = new SparkConf
        conf.setmMaster("local[*]")
        conf.setAppName("driver")

        val sc = new SparckContext(conf)

        // 读的是一个目录，那么data 中的数据每一个元素是一个文件中的数据
        // 所以每一次将是处理一个文件中的内容，以及这个文件的文件名
        val data = sc.wholeTextFiles("d://data/inverted/*")

        // 拆行: split("\r\n")
        // 这里得到的每一个单元数据以: 文件名, 文件内容  的形式组成的
        val resultData = data.flatMap{ case(filename, filetext) =>
            filetext.split("\r\n").flatMap { x => x.split(" ") }.map { x => (x,filename) }
        }
        
        val result = resultData.groupByKey.map{
            // 这里是按每个Key 对Array Value 进行处理，
            // 去重然后每个Value 之间添加一个 ","
            case(word, buffer) => (word, buffer.toList.distinct.mkString(","))
        }
        
        result.foreach(println)
    }
}


