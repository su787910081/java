package cn.tedu.kryo

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object Driver {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local").setAppName("kryo")
        // 指定底层的序列化实现类，这个参数的设置是固定的。
        // 只能是下面这一行代码。不能乱写
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
        // 指定自定义的Kryo注册器类
        // 这个是我们自己实现的那个注册器类
        conf.set("spark.kryo.registrator", "cn.tedu.kryo.MyKryoRegister")
        
        val sc = new SparkContext(conf)
        
        val p1 = new Person("tom", 23)
        val p2 = new Person("rose", 30)
        
        val rdd1 = sc.makeRDD(List(p1, p2))
        
        // 将其序列化到缓存中，只要不报错就可证明我们这个序列化是成功的
        rdd1.persist(StorageLevel.MEMORY_ONLY_SER)
        
        rdd1.unpersist()
    }
}


