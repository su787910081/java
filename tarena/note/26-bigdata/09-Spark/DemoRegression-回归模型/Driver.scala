package day04.cn.tedu.regression

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.regression.LinearRegression

object Driver {
  def main(args: Array[String]): Unit = {
    f1()
  }

  // 建立回归模型，预测商品的需求量
  // y(因变量) = a1x1(自变量) + a2x2(自变量) + a0(截距)
  // y = -6.497089660950071 * x1 + 0.01631790530613304 * x2 + 106.36879716405959
  // 目标求解系数，而且要满足使得误差的平方和最小
  // 可以通过Spark MLlib 库提供的模型来解决
  def f1() {
    val conf = new SparkConf
    conf.setMaster("local")
    conf.setAppName("f1")

    val sc = new SparkContext(conf)
    // 创建SparkSql 模块的上下文对象，可以通过此对象将RDD 转成DataFrame
    val sqc = new SQLContext(sc)

    val data = sc.textFile("f://data/ml/lritem.txt")

    // 为了满足接下来的建模需求，RDD[String] -> RDD[(x1, x2, Y)] -> DataFrame 数据块
    // RDD 的元素必须是元组类型，且元素是Double 类型
    val r1 = data.map { line =>
      val info = line.split("\\|")
      val Y = info(0).toDouble
      val temp = info(1).split(" ")
      val x1 = temp(0).toDouble
      val x2 = temp(1).toDouble

      (x1, x2, Y)
    }

    // r1.foreach { println }

    // 将RDD 转成DataFrame 并制定列名。列数与r1 元组的个数一致 
    val df1 = sqc.createDataFrame(r1).toDF("x1", "x2", "Y")
    // df1.show

    // 创建向量转换工具类，可以将DataFrame 中自变量数据封装为Vector
    // setInputCols: 指定自变量的列名("x1", "x2")(哪些列是自变量列)
    // setOutputCol: 为所有的自变量列起别名
    val ass = new VectorAssembler().setInputCols(Array("x1", "x2"))
      .setOutputCol("features")

    // 通过工具类指定转换，以满足下面的建模需求
    val df1Vectors = ass.transform(df1)

    // setFeaturesCol: 指定自变量列
    // setLabelCol: 指定因变量列
    // setFitIntercept true- 计算截距项系数，false-不计算
    // fit: 带入数据建模
    val model = new LinearRegression().setFeaturesCol("features")
      .setLabelCol("Y").setFitIntercept(true).fit(df1Vectors)

    // 获取模型方程的自变量系数 对应上面方程中的a1和a2
    val coef = model.coefficients
    // 获取截距项系数 对应上面方程中的a0
    val intercept = model.intercept

    // println(coef)
    // println(intercept)

    // 获取模型的多元R方值。最大值为1，这是拟合性优良指标。越趋近1，说明模型对于数据的拟合越好
    // 在生产环境，0.55 以上都可以接受
    val R2 = model.summary.r2

    // println(R2)
    
    // 通过模型实现预测
    // transform 方法是模型的预测方法，要求的数据类型DataFrame(Vectors)
    val predictSample = model.transform(df1Vectors)
    // predictSample.show


    // 给定一组数据: x1 = 10, x2 = 500, 要求通过模型预测对应Y 值是多少？
    // 这里的预测，跟Y 值没有关系
    // RDD[(x1, x2, Y)]->DataFrame->DataFrame(Vectors) -> 代入模型
    val testRDD = sc.makeRDD(Array((10.0, 500.0, 0.0)))
    // val df1 = sqc.createDataFrame(r1).toDF("x1", "x2", "Y")
    val testDF = sqc.createDataFrame(testRDD).toDF("x1", "x2", "Y")
    val testDFVectors = ass.transform(testDF)
    val predictTest = model.transform(testDFVectors)
    predictTest.show
  }
}