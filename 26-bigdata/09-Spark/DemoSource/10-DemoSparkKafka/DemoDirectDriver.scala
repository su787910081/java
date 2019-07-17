



// Kafka + SparkStreaming
// 当Driver 崩溃然后重启之后，会从hdfs 的checkpoint 中拉取之前的数据
// 这些数据包括: 配置信息、DStream 操作逻辑、job 的执行进度、offset
// 因为这里面包含了DStream 的操作逻辑，所以失败恢复之后。我们要使用我们新的逻辑的话，将不会起作用。
// 它会从HDFS 中获得原来的逻辑继续执行。
// 所以这个地方不太好


// 这个是处理direct 模式，同时从HDFS 中恢复offset 的信息
object DemoDirectDriver {
    def demo07 = {

        val ssc = StreamingContext.getOrCreate("checkpoint", createStreamingContext _)

        ssc.start()
        ssc.awaitTermination()

    }

    def createStreamingContext(): StreamingContext = {
        val args = Array[String]{"checkpoint"}
        val conf = new SparkConf
        //StreamingContext,里面包含SparkContext
        val ssc = new StreamingContext(conf, Seconds(args(0).trim.toInt))
        //设置checkpoint,保存运行数据
        ssc.checkpoint(args(1).trim)

        //kafka连接参数
        val kafkaParams = Map("metadata.broker.list" -> args(2).trim, "group.id" -> args(3).trim)
        //指定要读取的topics
        val topics = Set(args(4).trim)


        //创建directStream从kafka读取数据
        val data = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics)

        //迭代处理数据
        data.foreachRDD(rdd => {
            rdd.foreachPartition(p => {
                val conf = HBaseConfiguration.create()
                //连接Connection
                val hConnection = ConnectionFactory.createConnection(conf)
                //获取table
                val click = hConnection.getTable(TableName.valueOf(Constants.HISTORY_CLICK))
                val statistic = hConnection.getTable(TableName.valueOf(Constants.RESULT_STATISTIC))

                try {
                    while (p.hasNext) {
                        val tuple = p.next()
                        val logType = tuple._1
                        val logVal = tuple._2
                        println(logType + "\t" + logVal)
                        logType match {
                            case "click" => {
                                val clickObj = new Click(logVal)
                                if (HBaseUtil.isExists(click, clickObj.getRowKey)) {
                                    clickObj.doRepeat(statistic)
                                } else {
                                    clickObj.doNoRepeat(click, statistic)
                                }
                            }
                            case _ => {
                                logger.info("msg:" + logVal)
                            }
                        }
                    }
                } catch {
                    case ex: Exception => {
                        logger.error("error :", ex)
                    }
                } finally {
                    click.close()
                    statistic.close()
                    hConnection.close()
                }
            })
        })

        ssc
    }
}