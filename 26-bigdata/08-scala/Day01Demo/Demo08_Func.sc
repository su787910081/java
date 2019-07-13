/**
 * 关键字：def
 * 形式：def 函数名(参数列表): 返回值类型 = { 方法体 }
 */


object Demo08_Func {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def f1(): String = {
    "hello 1812"
    
  }                                               //> f1: ()String
  
  def f2(): Int = {
    100
  }                                               //> f2: ()Int
  
  def f3() = {
    100
  }                                               //> f3: ()Int
  
  def f4() = {
    Array(1, 2, 3, 4)
  }                                               //> f4: ()Array[Int]
  
  // 不加 '=' 返回值一律为 Unit
  def f5() {
    100
  }                                               //> f5: ()Unit
  
  def f6(a: Int, b: Int) = {
    a + b
  }                                               //> f6: (a: Int, b: Int)Int
  
  def f7(a: String, b: String): Array[String] = {
    a.split(b)
  }                                               //> f7: (a: String, b: String)Array[String]
  
  f7("a,b,c,d,f", ",")                            //> res0: Array[String] = Array(a, b, c, d, f)
  
  // 可变参数 ：  Int*
  // 可变参数必须 位于参数列表的最后
  def f9(a: Int*) = {
    for (i <- a) {
      println(i)
    }
  }                                               //> f9: (a: Int*)Unit
  
  f9(1,2,3,4)                                     //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  
                                                  
  def devide(n:Int):Double=1.0/n                  //> devide: (n: Int)Double
  devide(2)                                       //> res1: Double = 0.5

  val p1 = new Person                             //> p1  : Person = Person@7f690630
  p1.say()                                        //> hello
}