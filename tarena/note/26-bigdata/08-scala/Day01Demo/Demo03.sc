
/*
 * 1. scala 中没有基本变量，都是对象和方法
 * 2. scala 的Int，表面上是JAVA 的Int, 但是底层通过隐式转换，转成了scala 的RichInt 类型
 * 3. 其他的类型：RichFloat RichDouble RichChar RichBy ...
 * 4. 手册地址：https://www.scala-lang.org/api/2.10.4/index.html#package
 */

object Demo03 {
  println("hello world")                          //> hello world
  
  val i1 = 1                                      //> i1  : Int = 1
  // to 方法用于生成一个区间，一般用于循环时生成遍历的范围
  val r1 = i1.to(5)                               //> r1  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
  // 生成区间，并指定步长
  val r2 = i1.to(5, 2)                            //> r2  : scala.collection.immutable.Range.Inclusive = Range(1, 3, 5)
  val r3 = 1.to(5)                                //> r3  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
  val r4 = 1 to 5                                 //> r4  : scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4, 5)
  // 生成区间，并指定步长的简化语法
  val r5 = 1 to 5 by 2                            //> r5  : scala.collection.immutable.Range = Range(1, 3, 5)
  
  // 生成区间，左闭右开
  val r6 = 1.until(5)                             //> r6  : scala.collection.immutable.Range = Range(1, 2, 3, 4)
  val r7 = 1.until(5, 2)                          //> r7  : scala.collection.immutable.Range = Range(1, 3)
  
  
  val r8 = 1.0                                    //> r8  : Double = 1.0

  // (1 + 2) * 3 以方法的调用顺序来计算
  val r10 = 1.+(2).*(3)                           //> r10  : Int = 9
  
  // 前缀操作符前面需要有空格
  val r11 = +2                                    //> r11  : Int = 2
  val r12 = -2                                    //> r12  : Int = -2
  val r13 = !true                                 //> r13  : Boolean = false
  val r14 = ~0XFF                                 //> r14  : Int = -256
  
  // 前缀操作符避免歧义的语法
  val r15 = 2.unary_+                             //> r15  : Int = 2
  val r16 = 2.unary_-                             //> r16  : Int = -2
  val r17 = true.unary_!                          //> r17  : Boolean = false
  val r18 = 0XFF.unary_~                          //> r18  : Int = -256
}