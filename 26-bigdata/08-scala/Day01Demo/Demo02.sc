
/**
知识点：
1. scala 底层有一种隐式转换机制。比如针对String 类型，底层会隐式转换成scala 的StringOps 类型
    take, drop 等方法都来自于StringOps类型
2. scala 的通用化简规则：如果在调用方法时，方法参数列表只有一个，则方法的括号可以省略
*/
object Demo02 {
  val s1 = "hello,world"                          //> s1  : String = hello,world
  val r1 = s1.split(",")                          //> r1  : Array[String] = Array(hello, world)
  
  // 取出前N个字符
  val r2 = s1.take(5)                             //> r2  : String = hello
  val r21 = s1 take 5                             //> r21  : String = hello
  // 取出后N 个数据
  val r3 = s1.takeRight(5)                        //> r3  : String = world
  // 删除前N个元素，并返回剩余元素
  val r4 = s1.drop(5)                             //> r4  : String = ,world
  // 删除后N个元素，并返回剩余元素
  val r5 = s1.dropRight(5)                        //> r5  : String = hello,
  // 将String 重复n 次
  val r6 = s1* 2                                  //> r6  : String = hello,worldhello,world
  // 去重
  val v7 = s1.distinct                            //> v7  : String = helo,wrd
}