

object Day02Demo07_Tuple {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val t1 = (1, 2, 3, 4)                           //> t1  : (Int, Int, Int, Int) = (1,2,3,4)
  val t2 = ("hello", 100, Array(1, 2, 3), List(4, 5, 6))
                                                  //> t2  : (String, Int, Array[Int], List[Int]) = (hello,100,Array(1, 2, 3),List(
                                                  //| 4, 5, 6))

  // 取值, 下标从1 开始
  t2._1                                           //> res0: String = hello
  t2._2                                           //> res1: Int = 100
  t2._3                                           //> res2: Array[Int] = Array(1, 2, 3)
  
  t2._3.apply(2)                                  //> res3: Int = 3
  t2._3(2)                                        //> res4: Int = 3
  
  val t3 = (1, 2, (1, 2, 3), (4, 5, 6))           //> t3  : (Int, Int, (Int, Int, Int), (Int, Int, Int)) = (1,2,(1,2,3),(4,5,6))
  
  
  t3._4._3                                        //> res5: Int = 6
}