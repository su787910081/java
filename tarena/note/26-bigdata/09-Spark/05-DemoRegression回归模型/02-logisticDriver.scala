package cn.tedu.logistic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.classification.LogisticRegressionWithSGD
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS

/**
 * 本例是通过逻辑回归，判断一个人是否出现过交通事故(0 or 1) 离散型
 * 知识点：
 * 		1. 逻辑回归模型的目录函数是Sigmoid 函数
 * 		2. Sigmoid 函数的作用: 将连续型数据离散化 0 or 1
 * 		3. 所以逻辑回归，如果单从回归来看，是预测数据，但是逻辑回归解决的是二分类问题(不是预测)
 * 		4. 解逻辑回归模型的系数，可以用随机梯度下降法来解，也可以用拟牛顿法来解
 * 		5. 当步长不好选取时，可以考虑用拟牛顿法来处理
 */
object Driver {
    
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("log")
        
        val sc = new SparkContext(conf)
        val data = sc.textFile("F://data/logistic.txt")
        
        // 为了满足Spark建模要求,RDD[String]->RDD[LabeledPoint]
        val r1 = data.map { line =>
        val info = line.split("\t")
        // 因变量
        val Y = info.last.toDouble
        // 获取自变量数组
        val arr = info.dropRight(1).map { _.toDouble }
        LabeledPoint(Y, Vectors.dense(arr))
        }
        
        // r1.foreach { println }
        
        // 建立逻辑回归模型，底层用梯度下降法来求解系数
        // val model = LogisticRegressionWithSGD.train(r1, 20, 0.05)
        // val model = LogisticRegressionWithSGD.train(r1, 50, 0.01)
        // val model = LogisticRegressionWithSGD.train(r1, 50, 0.03)
        
        // 梯度下降法总是结果不对。换一种方法
        // 建立逻辑回归模型，底层用的是拟牛顿法来求解系数
        // 拟牛顿法也是一种迭代算法。通过数值解方法逼近真实解
        // 优点: 用很少的迭代次数就可以收敛，而且不需要选取步长。所以针对调节步长有困难的场景，可以用这个算法
        // 缺点: 计算代价更高
        val model = new LogisticRegressionWithLBFGS().run(r1)
        
        // 获取模型的系数
        val coef = model.weights
        
        // 获取模型的截距项系数。没有，结果是0.0
        val intercept = model.intercept
        
        // println(coef)
        // println(intercept)
        
        val predictResult = model.predict(r1.map { lp => lp.features })
        // predictResult.foreach { println }
        
        // age: 48  vision: 1 driver: 1
        val vec = Vectors.dense(15, 0.0, 0.0)
        val testRDD = sc.makeRDD(List(vec))
        val predictResult2 = model.predict(testRDD)
        predictResult2.foreach { println }
        
    }
}