package cn.tedu.vector

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext


object Driver {
  
    def main(args: Array[String]): Unit = {
        
        
    }
    
    // 密集型向量，后面建模常用
    def demoVector01() = {
        //--创建向量，要求传入单位数据是Double
        val v1=Vectors.dense(1.2,3.1,4.5)
        
        //--如果传入整型，底层会自动转成Double
        val v2=Vectors.dense(1,2,5)
        
        //--传入Array[Double]来创建向量
        val v3=Vectors.dense(Array(1.5,2.3,3.4))
        
        
        //--处理vectors.txt文件
        //--RDD[String] -> RDD[Vector]
        val conf=new SparkConf().setMaster("local").setAppName("vector")
        val sc=new SparkContext(conf)
        val data=sc.textFile("f://data/ml/vectors.txt")
        
        //--RDD[String]-RDD[Array[String]]->RDD[Array[Double]]->RDD[Vector]
        val r1=data.map { line =>line.split(" ").map { num => num.toDouble }}
                .map { arr => Vectors.dense(arr) }
        
        r1.foreach{println}           
    }

    // 稀疏型向量
    def demoVector02() = {
        //--创建稀疏型向量
        //--①参:指定向量长度
        //--②参:指定向量下标
        //--③参:向量下标对应的数值
        //--为什么是稀疏向量:因为只有指定的下标有数据，其他下标未指定都是0
        //--即向量中很多都是0,所以是稀疏向量
        val v1=Vectors.sparse(7,Array[Int](0,2,5),Array[Double](1.1,3.4,8.7))
        
        println(v1(0))
        println(v1(1))
        println(v1(2))
        println(v1(3))
    }

    // 向量标签类型
    def demoVector03() = {
        val v1=Vectors.dense(2,1,3)
        
        //--创建向量标签
        //--①参:标签值(因变量值Y),要求的数据类型是Double,如果是Int,底层自动转Double
        //--②参:向量
        val lb1=LabeledPoint(10,v1)
        
        println(lb1)
        //--获取标签值
        println(lb1.label)
        //--获取特征(向量)值
        println(lb1.features)
        
        //--处理labeled.txt文件。RDD[String]->RDD[LabeledPoint]
        val conf=new SparkConf().setMaster("local").setAppName("label")
        val sc=new SparkContext(conf)
        
        val data=sc.textFile("f://data/ml/labeled.txt")
        //--RDD[String]-RDD[Array[Double]]->RDD[LabeledPoint]
        val r1=data.map { line =>line.split(" ").map { num => num.toDouble } }
                .map { arr =>LabeledPoint(arr.last,Vectors.dense(arr.take(2))) }
                
        r1.foreach{println}
    }
}