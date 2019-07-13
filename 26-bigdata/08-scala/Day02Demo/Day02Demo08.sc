object Day02Demo08 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val a1 = Array("hello", "1812", "hadoop", "spark")
                                                  //> a1  : Array[String] = Array(hello, 1812, hadoop, spark)
  
  val a2 = a1.map { word => (word, 1) }           //> a2  : Array[(String, Int)] = Array((hello,1), (1812,1), (hadoop,1), (spark,1
                                                  //| ))
  
  val a3 = a2.map( x => x._1 )                    //> a3  : Array[String] = Array(hello, 1812, hadoop, spark)
  
  val l1 = List(("tom", 30), ("rose", 18), ("jim", 22), ("jary", 12))
                                                  //> l1  : List[(String, Int)] = List((tom,30), (rose,18), (jim,22), (jary,12))
  
  // 年龄大于18 元素
  val l2 = l1.filter( { x => x._2 > 18 } )        //> l2  : List[(String, Int)] = List((tom,30), (jim,22))
  
  l2.map({ x => x._1 })                           //> res0: List[String] = List(tom, jim)
  
  // 按年龄降序排序
  l1.sortBy({ x => -x._2 })                       //> res1: List[(String, Int)] = List((tom,30), (jim,22), (rose,18), (jary,12))
  
  // 年龄最大的那个人的姓名
  l1.maxBy( { x => -x._2 } )._1                   //> res2: String = jary
  
  // 年龄之和
  l1.map({ x => x._2}).sum                        //> res3: Int = 82
  
  val m1 = Map("tom" -> 23, "rose" -> 35, "jary" -> 18)
                                                  //> m1  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 35
                                                  //| , jary -> 18)
  m1.filter{ case(k, v) => v > 18 }               //> res4: scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 35
                                                  //| )
  m1.filter{ x => x._2 > 18 }                     //> res5: scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 35
                                                  //| )
                                                  
  val l4 = List("hello world", "hello 1812", "hello spark")
                                                  //> l4  : List[String] = List(hello world, hello 1812, hello spark)
  val l5 = l4.map{ x => x.split(" ")}             //> l5  : List[Array[String]] = List(Array(hello, world), Array(hello, 1812), Ar
                                                  //| ray(hello, spark))
  // 扁平化map 方法，元素的形式和个数都会发生变化
  val l6 = l4.flatMap { x => x.split(" ") }       //> l6  : List[String] = List(hello, world, hello, 1812, hello, spark)
  
  val l7 = List(("bj", 1), ("sh", 2), ("bj", 3), ("sh", 4))
                                                  //> l7  : List[(String, Int)] = List((bj,1), (sh,2), (bj,3), (sh,4))
  
  // 按地区分组
  // 按指定的规则分组，结果返回一个Map(Key[分组字段]->Value[分组的集合])
  l7.groupBy { x => x._1 }                        //> res6: scala.collection.immutable.Map[String,List[(String, Int)]] = Map(bj ->
                                                  //|  List((bj,1), (bj,3)), sh -> List((sh,2), (sh,4)))
  
  val l8 = List("hello", "hello", "1812", "hello", "1812")
                                                  //> l8  : List[String] = List(hello, hello, 1812, hello, 1812)
  
  // 统计单词频次
  val l9 = l8.groupBy { x => x }                  //> l9  : scala.collection.immutable.Map[String,List[String]] = Map(1812 -> Lis
                                                  //| t(1812, 1812), hello -> List(hello, hello, hello))
  l9.map { case(k, v) => (k, v.size) }            //> res7: scala.collection.immutable.Map[String,Int] = Map(1812 -> 2, hello -> 
                                                  //| 3)

  val l99 = l9.mapValues { x => x.size }          //> l99  : scala.collection.immutable.Map[String,Int] = Map(1812 -> 2, hello ->
                                                  //|  3)
  
  val l10 = List("hello 1812", "hello world", "hello 1812")
                                                  //> l10  : List[String] = List(hello 1812, hello world, hello 1812)
  val l11 = l10.flatMap{ x => x.split(" ") }      //> l11  : List[String] = List(hello, 1812, hello, world, hello, 1812)
  val l12 = l11.groupBy( { x => x })              //> l12  : scala.collection.immutable.Map[String,List[String]] = Map(world -> L
                                                  //| ist(world), 1812 -> List(1812, 1812), hello -> List(hello, hello, hello))
  l12.mapValues { x => x.size }                   //> res8: scala.collection.immutable.Map[String,Int] = Map(world -> 1, 1812 -> 
                                                  //| 2, hello -> 3)
  
  val m2 = l10.flatMap{ x => x.split(" ") }
    .map { word => (word, 1) }
    .groupBy { x => x._1 }                        //> m2  : scala.collection.immutable.Map[String,List[(String, Int)]] = Map(worl
                                                  //| d -> List((world,1)), 1812 -> List((1812,1), (1812,1)), hello -> List((hell
                                                  //| o,1), (hello,1), (hello,1)))
  m2.mapValues { x => x.head._2 }                 //> res9: scala.collection.immutable.Map[String,Int] = Map(world -> 1, 1812 -> 
                                                  //| 1, hello -> 1)
  





}