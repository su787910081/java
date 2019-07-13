
/**
 *
 * Array List 通用方法
 1. take  取前N 项元素
 2. takeRight 取右N 项元素
 3. drop  删除前N 项元素，返回剩余元素
 4. dropRight  删除右N 项元素，返回剩余元素
 5. head 取出第一个元素
 6. last 取出最后一个元素
 7. mkString 生成一个字符串，并提供一个分隔符
 8. intersect 交集
 9. unio 并集
 10. diff 差集
 11. exists 是否存在指定规则的元素
 12. count 满足规则的元素的个数
 13. filter 过滤指定条件的元素
 14. sortBy 按指定规则排序
 15. map 将每一个元素按规则映射成另外一种元素类型，集合的类型是不会改变的。
 16. max 最大的一个元素
 17. min 最小的一个元素
 18. sum 所有元素求和
 19. reduce 每前两个相邻元素按指定规则进行处理，处理的结果与后一个元素继续按此规则处理，返回最终结果
 20. reverse 元素反转
 21. distinct 去重
 * 重点掌握：1. filter 2. sortBy 3. map 4. reduce 5. distinct, 6. flatMap, 7.groupBy
 *      filter 过滤指定条件的元素
 *      sortBy 按指定规则排序
 *      map 将每一个元素按规则映射成另外一种元素类型，集合的类型是不会改变的。
 *      reduce 每前两个相邻元素按指定规则进行处理，处理的结果与后一个元素继续按此规则处理，返回最终结果
 *      flatMap 扁平化map，按指定规则进行映射，映射的结果元素将会是一个单元素集合 List[String]
 *      groupBy 按指定规则做聚合，最后将结果返回到一个map映射里。
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