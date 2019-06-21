package cn.tedu.sgd

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LinearRegressionWithSGD

// 通过梯度下降法，求解回归方程的系数
// 使用时注意的问题: 
// 1. 步长的选取，过小或者过大都不好。
// 如果过小，则需要迭代很多次才能收敛于极值
// 如果过大，可能会发生围绕最优解来回震荡而不收敛
// 根据经验，步长的选取一般: 0.01~0.5之间。(不绝对)
// 2. 梯度下降法，如果解的函数是非凸函数，因为参数的初始位置是随机的，
//  所以得到的最优解是局部最优解，而非全局最优解。
//  解决方法：多做几次，从中选出最优的(通过比较误差最小来决定)
// 3. 梯度下降法，如果解的函数是凸函数，得到的解一定是全局最优解
// 4. 梯度下降法的种类有三种: 
//  1). 批量梯度下降法。特点是-> 每次更新系数时，所有的样本都需要参与计算
//    优点: 需要很少的迭代次数就可以收敛
//    缺点: 不适用于大样本的情况。如果样本数很多，则更新一次的时间会非常长
//  2). 随机梯度下降法(SGD)。特点是 -> 每次更新系数时，是从所有样本中随机选取一个样本参与计算
//    优点: 适用于大样本的场景，更新一次的时间非常短(因为只选取一个样本)
//    缺点: 需要很多次迭代才能收敛
//    目前在生产环境下，都是用的随机梯度下降法
//  3). 小批量梯度下降法
//    相当于综合了以上两种算法，既不是所有样本参与计算，也不是只选取一个样本。
object Driver {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("sgd")
        
        val sc = new SparkContext(conf)
        val data = sc.textFile("F://data/testSGD.txt")
        
        // 为了满足Spark的建模要求，RDD[String]->RDD[LabeledPoint]
        val r1 = data.map { line =>
        val info = line.split(",")
        val Y = info(0).toDouble  // 因变量
        val arr = info(1).split(" ").map { _.toDouble }  // 自变量数组
        LabeledPoint(Y, Vectors.dense(arr))      
        }
        
        // r1.foreach { println }
        
        // 参数1：输入数据
        // 参数2：迭代次数
        // 参数3：步长
        val model = LinearRegressionWithSGD.train(r1, 20, 1)
    
        // 获取模型系数
        // Y = b1X1 + b2X2
        // Y = 0.99X1 + 1.002X2
        val coef = model.weights
        
        // println(coef)
        
        // 通过模型实现预测，要求传入的数据类型：RDD[Vector(X1, X2, ..., Xn)] 由自变量形成的向量
        val predictResult = model.predict(r1.map { lp => lp.features })
        predictResult.foreach { println }
    }
}