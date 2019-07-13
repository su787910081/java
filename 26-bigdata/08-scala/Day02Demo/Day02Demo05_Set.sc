object Day02Demo05_Set {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 定长
  val s1 = Set(1, 1, 2, 2, 3)                     //> s1  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)
  
  // 变长
  val s2 = scala.collection.mutable.Set(1, 2, 3, 4)
                                                  //> s2  : scala.collection.mutable.Set[Int] = Set(1, 2, 3, 4)

  val s3 = Set(1, 2, 3)                           //> s3  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)
  val s4 = Set(3, 4, 5)                           //> s4  : scala.collection.immutable.Set[Int] = Set(3, 4, 5)
  
  // 取交集，两种方法
  s3 & s4                                         //> res0: scala.collection.immutable.Set[Int] = Set(3)
  s3.intersect(s4)                                //> res1: scala.collection.immutable.Set[Int] = Set(3)
  
  // 取差集
  s3 &~ s4                                        //> res2: scala.collection.immutable.Set[Int] = Set(1, 2)
  s3.diff(s4)                                     //> res3: scala.collection.immutable.Set[Int] = Set(1, 2)
  
  // 并集
  s3 ++ s4                                        //> res4: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)
  s3.union(s4)                                    //> res5: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)
}