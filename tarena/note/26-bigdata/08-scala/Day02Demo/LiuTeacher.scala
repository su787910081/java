
/**
 * 混入特质用 with 关键字
 * 单继承，多混入
 * scala 要求有而且仅有一个extends 关键字
 */
class LiuTeacher extends Teacher with Dance with Drive {
  def makeNote(data: String): String = {
    ""
  }

  def teach(): Unit = {
    
  }
  
  override def sleep() = {
    
  }

  def BaLei(): String = {
    "BaLei"
  }

  def Floor(): Int = {
    1
  }

  def start(): Unit = {
    
  }
}