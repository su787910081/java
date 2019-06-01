
/**
 *
 * Array List 通用方法
 * 1. take 2. takeRight 3. drop 4. dropRight 5. head 6. last
 * 7. mkString 8. intersect 9. unio 10. diff 11. exists
 * 12. count 13. filter 14. sortBy 15. map 16. max 17. min
 * 18. sum 19. reduce 20. reverse 21.
 * 重点掌握：1. filter 2. sortBy 3. map 4. reduce 5. distinct, 6. flatMap, 7.groupBy
 * 掌握类型转换的技巧: 比如List 没有而Array 有，则可以转换成Array 再来操作
 */
import scala.collection.mutable._

object Day02Demo04_List {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 定长
  val l1 = List(1, 2, 3, 4)                       //> l1  : List[Int] = List(1, 2, 3, 4)
  
  // 变长
  val l2 = scala.collection.mutable.ListBuffer(1, 2, 3, 4)
                                                  //> l2  : scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2, 3, 4)

  // 通过下标操作
  l1(0)                                           //> res0: Int = 1
  
  // 在一个List 的左侧或者右侧添加一个元素，放到一个新的List 中
  val l4 = 0 +: l1                                //> l4  : List[Int] = List(0, 1, 2, 3, 4)
  val l5 = l1 :+ 5                                //> l5  : List[Int] = List(1, 2, 3, 4, 5)
 
  // 类型转换
  val a1 = l1.toArray                             //> a1  : Array[Int] = Array(1, 2, 3, 4)
  
  val i1 = 100                                    //> i1  : Int = 100
  val s1 = i1.toString()                          //> s1  : String = 100
  
  val l6 = List(1, 2, 3)                          //> l6  : List[Int] = List(1, 2, 3)
  val l7 = List(3, 4, 5)                          //> l7  : List[Int] = List(3, 4, 5)
  
  l6.toArray.intersect(l7).toList                 //> res1: List[Int] = List(3)
  
  
  
  
  
  
  
  
  
  
}