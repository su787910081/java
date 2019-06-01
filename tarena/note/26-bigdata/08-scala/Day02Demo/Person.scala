
// scala 的类有一个主构造器，以及多个辅助构造器
// 主构造器在类上声明
// 辅助构造器在类中声明，通过 def this(参数) {}
// 辅助构造器中必须调用本类的其他(主)构造器
class Person(v1: String, v2: Int) {
  def say() = {
    println("hello")  
    
    // 本地函数：定义在函数内的函数
    // 本地函数无法通过对象直接调用，只能在它所属的外层函数中调用
    def speak() = {
      println("say world")
    }
  }
  
  
  
  private var name = v1
  private var age = v2
  
  // 辅助构造器
  def this(v1: String) {
    this(v1: String, 0)
  }
  
  def this() {
    this("", 0)
  }
  
  def this(v2: Int) {
    this("", v2)
  }
  
  def getName() = {
    this.name
  }
  
  def setName(name: String) = {
    this.name = name    
  }
  
  def getAge() = {
    this.age
  }
  
  def setAge(age: Int) = {
    this.age = age
  }
  
  
  def eat() = {
    println("eat food")
  }
}

// 为一个类提供静态变量或方法的规则 ：将object 和class 写在一个文件中，并且名字一致
// 如果 满足以上的要求
// object 是class 的伴生对象
// class 是object 的伴生类
// scala 通过这种object 机制，为class 提供了静态机制
// 如果要编写和运行main 方法，必须 要写在单例对象的类中
object Person {
  // 为Person 类提供了speak 静态方法
  def speak() = {
    
  }
  
  def main(args: Array[String]): Unit = {
    val a1 = Array(1, 2, 3, 4)
    a1.foreach { x => println(x) }
  }
}


