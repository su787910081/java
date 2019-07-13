object Demo09_Func_h {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  def f1(a: Int, b: Int) = { a + b }              //> f1: (a: Int, b: Int)Int
  
  // 匿名函数：可以当做参数被赋值或者传递
  val f2 = (a: Int, b: Int) => { a + b }          //> f2  : (Int, Int) => Int = <function2>
  
  f2(2, 3)                                        //> res0: Int = 5
  
  
  // 把匿名函数当做参数进行传递
  // 高阶函数允许将函数当做参数进行传递，这是函数式编程的特点
  def f3(a: Int, b: Int, f:(Int, Int) => Int) = {
    f(a, b)
  }                                               //> f3: (a: Int, b: Int, f: (Int, Int) => Int)Int
  
  f3(2, 3, (a: Int, b: Int) => { a * b } )        //> res1: Int = 6
  // 匿名函数化简：如果参数类型可以通过函数体推测，则参数类型可以省略
  f3(2, 3, (a, b) => a * b )                      //> res2: Int = 6
  // 通过占位符(_) 进行化简
  f3(2, 3, _ * _)                                 //> res3: Int = 6

  def f4(a: String, b: String, f: (String, String) => String) = {
    f(a, b)
  }                                               //> f4: (a: String, b: String, f: (String, String) => String)String
  
  f4("hello", "1812", (a: String, b: String) => { a + b })
                                                  //> res4: String = hello1812
                                                  
  def f5(a: String, b: String, f: (String, String) => Array[String]) = {
    f(a, b)
  }                                               //> f5: (a: String, b: String, f: (String, String) => Array[String])Array[String
                                                  //| ]
  
  f5("hello,1812", ",", (a: String, b: String) => { a.split(b) } )
                                                  //> res5: Array[String] = Array(hello, 1812)
  
  
  val a1 = Array(1, 2, 3, 4)                      //> a1  : Array[Int] = Array(1, 2, 3, 4)
  a1.filter { (x: Int) => { x > 2 } }             //> res6: Array[Int] = Array(3, 4)
  // 化简：参数只一个则括号可以省略
  a1.filter { x: Int => { x > 2 } }               //> res7: Array[Int] = Array(3, 4)
  // 化简
  a1.filter { _ > 2 }                             //> res8: Array[Int] = Array(3, 4)
  
  a1.foreach { (x: Int) => { println(x) }  }      //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4

  // 终级化简：可以通过(_) 占位符

}