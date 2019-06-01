object Day02Demo05_map {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 定长
  val m1 = Map("tom"->23, "rose"->30, "jim"->18)  //> m1  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 30
                                                  //| , jim -> 18)
  
  // 变长
  val m2 = scala.collection.mutable.Map("tom"->23, "rose"->30, "jim"->18)
                                                  //> m2  : scala.collection.mutable.Map[String,Int] = Map(jim -> 18, rose -> 30, 
                                                  //| tom -> 23)

  // 通过Key 取值，若key 不存在则会抛异常
  m1.apply("tom")                                 //> res0: Int = 23
  m1("tom")                                       //> res1: Int = 23
  // 通过Key 取值，并将值封装到Some 类型里
  // getOrElse(0) 如果指定的Key 不存在，则返回一个默认值(0)
  m1.get("tom").getOrElse(0)                      //> res2: Int = 23
  m1.get("John").getOrElse(0)                     //> res3: Int = 0

  // 获取map 的所有Key,并保存到一个迭代器
  m1.keys.foreach{ println(_) }                   //> tom
                                                  //| rose
                                                  //| jim


  // 获取map 中所有的value，返回到一个迭代器中
  val iter1 = m1.values                           //> iter1  : Iterable[Int] = MapLike(23, 30, 18)

  // 在(变长)map 中添加一个元素
  m2 += ("jary" -> 35)                            //> res4: Day02Demo05_map.m2.type = Map(jary -> 35, jim -> 18, rose -> 30, tom -
                                                  //| > 23)

  val m3 = m1.filter{ case(k,v) => v > 18 }       //> m3  : scala.collection.immutable.Map[String,Int] = Map(tom -> 23, rose -> 30
                                                  //| )

  val m4 = m1.map{ case(k, v) => (k, v + 10) }    //> m4  : scala.collection.immutable.Map[String,Int] = Map(tom -> 33, rose -> 40
                                                  //| , jim -> 28)

  val b1 = m1.exists{ case(k, v) => v < 18 }      //> b1  : Boolean = false

  val m5 = m1.mapValues { x => x + 10 }           //> m5  : scala.collection.immutable.Map[String,Int] = Map(tom -> 33, rose -> 40
                                                  //| , jim -> 28)

}