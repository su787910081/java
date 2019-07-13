

object Day02Demo03 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // immutable: 定长数组  长度不可变，元素可更改
  val a1 = Array(1, 2, 3, 4)                      //> a1  : Array[Int] = Array(1, 2, 3, 4)
  
  val a2: Array[Int] = Array(1, 2, 3, 4)          //> a2  : Array[Int] = Array(1, 2, 3, 4)
  
  val a3 = new Array[Int](3)                      //> a3  : Array[Int] = Array(0, 0, 0)
  
  // mutable: 变长数组(ArrayBuffer)
  val a4 = scala.collection.mutable.ArrayBuffer(1, 2, 3, 4)
                                                  //> a4  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
  
  // 下标操作数组
  a1.apply(0)                                     //> res0: Int = 1
  // 等价
  a1(0)                                           //> res1: Int = 1
  
  // 通过下标赋值
  a1(0)=10
  a1                                              //> res2: Array[Int] = Array(10, 2, 3, 4)
  
  // 取出前(后)N 项元素，并返回到新数组
  val r1 = a1.take(2)                             //> r1  : Array[Int] = Array(10, 2)
  val r2 = a1.takeRight(2)                        //> r2  : Array[Int] = Array(3, 4)
  
  // 去掉前(后)N 项元素，并将剩余元素返回到新数组
  val r3 = a1.drop(2)                             //> r3  : Array[Int] = Array(3, 4)
  val r4 = a1.dropRight(2)                        //> r4  : Array[Int] = Array(10, 2)
  
  // 取出数组头元素，有别于take(1) 一个是元素类型一个是数组类型
  val r5 = a1.head                                //> r5  : Int = 10
  val r6 = a1.take(1)                             //> r6  : Array[Int] = Array(10)
  
  val r7 = a1.last                                //> r7  : Int = 4
  val r8 = a1.takeRight(1)                        //> r8  : Array[Int] = Array(4)
  
  a1.max                                          //> res3: Int = 10
  a1.min                                          //> res4: Int = 2
  a1.sum                                          //> res5: Int = 19
  
  // 将数组拼接成一个字符串以指定字符串进行分隔
  val r9 = a1.mkString("|")                       //> r9  : String = 10|2|3|4
  
  val a5 = Array(1, 2, 3)                         //> a5  : Array[Int] = Array(1, 2, 3)
  val a6 = Array(3, 4, 5)                         //> a6  : Array[Int] = Array(3, 4, 5)
  val a7 = Array(3, 6, 7)                         //> a7  : Array[Int] = Array(3, 6, 7)
  
  // 取两个集合的交集, 应用场景：比较两个文件数据的相同部分
  val r10 = a5.intersect(a6).intersect(a7)        //> r10  : Array[Int] = Array(3)
  
  // 取并集
  val r11 = a5.union(a6)                          //> r11  : Array[Int] = Array(1, 2, 3, 3, 4, 5)
  // 取差集  a5 对a6 取差集：a5 中有而a6 中没有的
  // 应用场景：比较文件数据之间相异的部分
  val r12 = a5.diff(a6)                           //> r12  : Array[Int] = Array(1, 2)
  val r13 = a6.diff(a5)                           //> r13  : Array[Int] = Array(4, 5)
  
  // 根据指定规则判断是否存在满足条件的元素
  a5.exists { x => x % 2 == 0 && x > 4 }          //> res6: Boolean = false
  // 根据指定规则计数
  a5.count { x => x % 2 == 0 }                    //> res7: Int = 1
  a5.count { _ % 2 == 0 }                         //> res8: Int = 1
  
  a5.filter { x => x > 2 }                        //> res9: Array[Int] = Array(3)
  a5.filter { _ > 2 }                             //> res10: Array[Int] = Array(3)
  
  val a8 = Array("tom, 23, M", "rose, 18, F", "jary, 30, M")
                                                  //> a8  : Array[String] = Array(tom, 23, M, rose, 18, F, jary, 30, M)
  
  a8.filter { x => x.split(",")(2).equals("M") }  //> res11: Array[String] = Array()
  a8.filter { _.split(",")(2).equals("M") }       //> res12: Array[String] = Array()
  
  // 将元素从一个形式映射(转换)到另外一个形式
  // 无论怎么转换，元素的个数不会变化
  val a9 = a8.map { x => x.split(",") }           //> a9  : Array[Array[String]] = Array(Array(tom, " 23", " M"), Array(rose, " 1
                                                  //| 8", " F"), Array(jary, " 30", " M"))
  a8.map { _.split(",") }                         //> res13: Array[Array[String]] = Array(Array(tom, " 23", " M"), Array(rose, " 
                                                  //| 18", " F"), Array(jary, " 30", " M"))
  
  a9.map { x => x(0) }                            //> res14: Array[String] = Array(tom, rose, jary)
  
  // 指定规则，升序排序
  val a10 = Array(4, 1, 2, 3, 5)                  //> a10  : Array[Int] = Array(4, 1, 2, 3, 5)
  val a11 = a10.sortBy { x => x }                 //> a11  : Array[Int] = Array(1, 2, 3, 4, 5)
  
  // 以升序规则实现降序
  a10.sortBy { x => -x }                          //> res15: Array[Int] = Array(5, 4, 3, 2, 1)
  
  a10.foreach { x => println(x) }                 //> 4
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
  a10.foreach { println(_) }                      //> 4
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
                                                  
  // 迭代方法
  a10.reduce{ (a,b) => a + b }                    //> res16: Int = 15
  // 4! = 4*3*2*1
  val a13 = Array(1, 2, 3, 4)                     //> a13  : Array[Int] = Array(1, 2, 3, 4)
  a13.reduce{ (a,b) => a * b }                    //> res17: Int = 24
  a13.reduce{ _ * _ }                             //> res18: Int = 24
}