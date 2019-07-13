

/**
 *
 * if else 是有返回值的。
 * scala 的Unit 类型类比于JAVA 的void，空类型
 * 打印函数：print println，它们的返回值的类型就是Unit
 * scala 的Any 类型，类比于JAVA 的Object类型
 * if else 返回的类型不一致，返回值就是Any 类型
 */

object Demo04 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val v1 = 5                                      //> v1  : Int = 5

  // 接收if else 的返回值
  val r1 = if (v1 > 5) {
    println("big")
  } else {
    println("small")
  }                                               //> small
                                                  //| r1  : Unit = ()

  val a1 = Array(1, 2, 3, 4)                      //> a1  : Array[Int] = Array(1, 2, 3, 4)
  var index = 0                                   //> index  : Int = 0
  while (index < a1.length) {
    println(a1(index))
    index += 1
  }                                               //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
}