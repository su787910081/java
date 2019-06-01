
// 抽象类
// 抽象类中可以定义抽象方法和普通方法
abstract class Teacher {
  
  // 抽象方法：没有方法体
  def makeNote(data: String): String
  
  def teach(): Unit
  
  // 普通方法
  def sleep() = {
    
  }
}