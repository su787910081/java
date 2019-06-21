


object Driver {
    def main(args: Array[String]): Unit = {
        // 读取整个目录中的所有文件
        val conf = new SparkConf
        conf.setmMaster("local[*]")
        conf.setAppName("driver")

        val sc = new SparckContext(conf)

        // 读的是一个目录，那么data 中的数据每一个元素是一个文件中的数据
        // 所以每一次将是处理一个文件中的内容，以及这个文件的文件名
        val data = sc.wholeTextFiles("d://data/inverted/*")

        // 拆行: split("\r\n")
        // 
    }
}