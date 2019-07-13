


object Day02Demo01 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 懒值，是在真正调用时才会被加载
  // 懒值，只能修饰常量，不能修饰变量
  lazy val v2 = 100                               //> v2: => Int
  
  println(v2)                                     //> 100
  
  
  // scala 底层支持柯里化技术，允许将接收多个参数的函数转变为接收单一参数的函数
  def f1(a: Int, b: Int) = { a + b }              //> f1: (a: Int, b: Int)Int
  
  def f11(a: Int)(b: Int) = { a + b }             //> f11: (a: Int)(b: Int)Int
  
  f1(2, 3)                                        //> res0: Int = 5
  f11(2)(3)                                       //> res1: Int = 5
  
  def f2(a: Int, b: Int, c: Int) = { a + b + c }  //> f2: (a: Int, b: Int, c: Int)Int
  
  def f21(a: Int)(b: Int)(c: Int) = { a + b + c } //> f21: (a: Int)(b: Int)(c: Int)Int
  def f22(a: Int, b: Int)(c: Int) = { a + b + c } //> f22: (a: Int, b: Int)(c: Int)Int
  def f23(a: Int)(b: Int, c: Int) = { a + b + c } //> f23: (a: Int)(b: Int, c: Int)Int
  
  f2(1,2,3)                                       //> res2: Int = 6
  f21(1)(2)(3)                                    //> res3: Int = 6
  
  def f3(a: Int, b: Int, f:(Int, Int) => Int) { f(a,b) }
                                                  //> f3: (a: Int, b: Int, f: (Int, Int) => Int)Unit
  
  // 普通参数 + 匿名函数 = 自建的控制结构
  def f31(a: Int, b: Int)(f:(Int, Int) => Int) { f(a,b) }
                                                  //> f31: (a: Int, b: Int)(f: (Int, Int) => Int)Unit
  
  
  
  
  
  
  
  
}