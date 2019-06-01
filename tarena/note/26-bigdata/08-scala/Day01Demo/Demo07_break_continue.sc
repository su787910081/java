import util.control.Breaks._

object Demo07_break_continue {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // for (i <- 1 to 10) {
  //   if (i == 8) {
  //     // 使用break 需要导入包(import util.control.Breaks._)
  //     break
  //   }
  //   println(i)
  // }
  
  // 习惯上，在实现break 效果时，用breakable 将for 包裹进去
  // breakable 在for 外部是break 效果
  breakable(
    for (i <- 1 to 10) {
      if (i == 8) {
        break
      } else {
        println(i)
      }
    }
  )                                               //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7

  // breakable 在循环内是continue 效果
  for (i <- 1 to 10) {
    breakable(if (i == 8) {
      break
    } else {
      println(i)
    })                                            //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 9
                                                  //| 10
  }
}