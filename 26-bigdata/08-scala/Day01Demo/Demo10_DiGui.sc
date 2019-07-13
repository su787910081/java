object Demo10_DiGui {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  // 用递归函数判断 斐波那契 数列第N项的数字是什么
  
  // scala 写递归函数必须显示声明函数的返回值类型
  // 递归函数，结束条件的返回值必须加return 关键字返回
  def f1(n: Int): Int = {
    if (n == 0) return 2
    if (n == 1) return 3
    else f1(n-1) + f1(n-2)
  }                                               //> f1: (n: Int)Int
  
  f1(4)                                           //> res0: Int = 13
  
  def f2(n: Int): Int = {
    if (n == 1) return 2
    if (n == 2) return 3
    
    val v = f2(n - 2)
    v * v
  }                                               //> f2: (n: Int)Int
  
  for (i <- 1 to 8) {
    println(f2(i))                                //> 2
                                                  //| 3
                                                  //| 4
                                                  //| 9
                                                  //| 16
                                                  //| 81
                                                  //| 256
                                                  //| 6561
  }
  
  def f3(n: Int): Int = {
    if (n == 0) return 2
    if (n == 1) return 3
    
    if (n % 2 == 0) {
      return 2 * f3(n - 2)
    }
    
    3 * f3(n - 2)
  }                                               //> f3: (n: Int)Int
  
  for (i <- 0 to 9) {
    println(f3(i))                                //> 2
                                                  //| 3
                                                  //| 4
                                                  //| 9
                                                  //| 8
                                                  //| 27
                                                  //| 16
                                                  //| 81
                                                  //| 32
                                                  //| 243
                                                  
                                                  
                                                  
                                                  
  }
  
  
  
  
  
  
}