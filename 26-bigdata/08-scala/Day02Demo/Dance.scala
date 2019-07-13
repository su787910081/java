
/**
 * scala 的trait(特质) 类比于JAVA 的接口
 * trait 中可以定义抽象方法 
 * 混入特质用 with 关键字，多个混入用多个with 
 * 可以定义普通方法
 * 
 */
trait Dance {
  def BaLei(): String
  
  def Floor(): Int
  
  // 普通方法
  def tiTa() = {
    
  }
}