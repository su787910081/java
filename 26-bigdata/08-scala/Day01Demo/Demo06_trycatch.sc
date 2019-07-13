
/*
 * try catch
 * 知识点
 * scala 在catch 中，捕获异常用的是case 来匹配
 * scala 的match 机制类比 于java 的switch case
 * scala 的match 是有返回值的，可以接收
 */


object Demo06_trycatch {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  try {
    // throw new NullPointerException
    throw new RuntimeException
  
  } catch {
    case t: NullPointerException => {
      println("null error")
    }
    case t:Exception => {
      println("other error")
    }
  } finally {
    println("end")
  }                                               //> other error
                                                  //| end
  val s1 = "hello"                                //> s1  : String = hello
  
  var r1 = s1 match {
    case "hello" => {
      println("1")
    }
    case "world" => {
      println("2")
    }
  }                                               //> 1
                                                  //| r1  : Unit = ()
}