package cn.tedu.als

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.mllib.recommendation.ALS

// 交替最小二乘法(算法)
object Driver {
  
    def main(args: Array[String]): Unit = {
        

    }

    def DemoBase() = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("als")
        
        val sc = new SparkContext(conf)
        
        val data = sc.textFile("F://data/als.txt")
        
        // 为了满足Spark 建模要求，RDD[String] -> RDD[Rating(userId[Int], itemId[Int], score[Double])]
        val Ratings = data.map { line => 
        val info = line.split(" ")
        val userId = info(0).toInt
        val itemId = info(1).toInt
        val score = info(2).toDouble
        
        Rating(userId, itemId, score)
        }
        
        // Ratings.foreach { println }
        
        // 参数1: 数据集
        // 参数2: 隐藏因子数 k(小于用户数或物品数)
        // 参数3: 最大迭代次数
        // 参数4: 可要可不要。正则化参数，防止模型做拟合
        // 对于隐藏因子k 的选择，在生产环境，用户数和物品数都很多，k 的选取范围: 50~200
        // k 的取值不宜过多，避免带来过大的计算代价
        // 迭代次数，根据服务器的配置情况尽量多一些。保证算法最后收敛
        val model = ALS.train(Ratings, 3, 10, 0.01)
        
        // 为5号用户推荐2 个商品
        val u5Result = model.recommendProducts(5, 2)
        // u5Result.foreach { println }
        // 为12号商品，推荐1 个用户
        val item12Result = model.recommendUsers(12, 1)

        // 预测3号用户对14号商品的评分
        val u3PredictItem14 = model.predict(3, 14)
    }

    /**
    * 对u.data 文件数据建立 推荐系统模型
    * 然后编号789 的用户推荐10 部电影
    * 隐藏因子数 k = 50, lambda = 0.01
    */
    def DemoAlsMoive() = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("alsmovie")
        
        val sc = new SparkContext(conf)
        val data = sc.textFile("F://data/u.data")
        
        // 获取电影信息文件数据
        val movieData = sc.textFile("F://data/u.item", 4)
        // RDD[String] -> RDD[(id, name)] -> collectAsMap -> Map(id, name)
        val movieMap = movieData.map { line => 
        val info = line.split("\\|")
        val movieId = info(0).toInt
        val movieName = info(1)
        
        (movieId, movieName)
        }.collectAsMap
        
        val ratings = data.map { line =>  
        val arr = line.split("\t")
        val userId = arr(0).toInt
        val movieId = arr(1).toInt
        val score = arr(2).toDouble
        
        Rating(userId, movieId, score)
        }
        
        // r1.foreach { println }
        
        val model = ALS.train(ratings, 50, 10, 0.01)
        // Rating(789,1170,5.374560424112586)
        val u789ResultTemp = model.recommendProducts(789, 10)
        
        // u789ResultTemp.foreach { println }
        
        // 推测喜欢的10部电影
        val u789Result10 = u789ResultTemp.map { x => 
        val userId = x.user
        val movieId = x.product
        val score = x.rating
        val movieName = movieMap(movieId)
        (userId, movieName, score)
        }
        u789Result10.foreach { println }
        println("##############################################")
        
        // 接下来检测模型的准确性
        // 用模型推荐的结果和用户本身的喜好相对比，看是否准确
        // 本例中的实现思路: 
        // 1. 先获取789号用户看过的所有电影
        // 2. 找出789号用户最喜欢的前10部电影
        // 3. 用推荐的10部和他喜爱的10部做比对，看是否有类似的题材的电影
        
        
        // keyBy 作用: 在操作数据集时，指定以什么属性为Key.这里表示以用户id属性为Key
        // keyBy 后会跟一个lookup(固定用法)，作用: 具体找哪个key
        // 获取789号用户看过的所有电影
        val u789Movies = ratings.keyBy { x => x.user }.lookup { 789 }
        // u789Movies.foreach { println }
        
        val u789LikeTop10 = u789Movies.sortBy { x => -x.rating }.take(10).map { x => 
        val userId = x.user
        val movieId = x.product
        val score = x.rating
        val movieName = movieMap(movieId)
        
        (userId, movieName, score)
        }
        u789LikeTop10.foreach { println }
        
        
        // 模型存储，目录路径可以是本地文件系统，也可以是HDFS
        model.save(sc, "F://data/recResult")
    }

    // 加载存储的模型
    def DemoAlsLoad() = {
        val conf = new SparkConf
        conf.setMaster("local")
        conf.setAppName("alsmovie")

        val sc = new SparkContext(conf)

        // 加载推荐系统模型
        val model = MatrixFactorizationModel.load(sc, "F://data/recResult")

        // Spark 提供的ALS 算法建立的推荐系统模型存在一个缺点: 
        // 只有基于用户推荐的方法，没有基于物品推荐的方法
        // 基于物品推荐的概念: 基于物品来推荐物品
        // 所以这一部分需要用户自己来实现

        // 基于物品推荐物品的实现思路: 
        // 1. 先定好一个物品，比如物品编号123 号，比如推荐10 个商品
        // 2. 要计算出其他物品和123 号物品的相似度(通过夹角余弦来计算)
        // 3. 根据相似度做降序排序，取出前10 个商品

        // 获取的是用户因子矩阵
        val userFactors = model.userFeatures

        // 获取的是物品因子矩阵。可以用于计算物品与物品之间的相似度
        // RDD[(Int, Array[Double])]  <==> [(物品ID), [这个物品对应的那一列的所有因子数值] 个数对应的是k 的值(50)
        val itemFactors = model.productFeatures

        // itemFactors.map{ case(id, arr) => (id, arr.mkString(","))}.foreach { println }

        // 找到123号电影的因子值
        val item123Factor = itemFactors.keyBy { x => x._1 }.lookup(123).head._2

        val cos123 = itemFactors.map {
        case (id, factor) =>
            // 计算出当前电影和123 号电影的夹角余弦
            // 本质上就是计算Array[Double] 和Array[Double] 之间的夹角余弦
            val cos = cosArray(item123Factor, factor)
            (id, cos) // 返回当前电影的id和123 号电影的夹角余弦
        }
        // cos123.foreach{ println }

        val item123Top10 = cos123.sortBy { case (id, cos) => -cos }.take(10)

        item123Top10.foreach { println }
    }
}