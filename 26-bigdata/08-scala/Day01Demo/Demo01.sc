
/**
知识点
1.scala worksheet是scala的交互式编译模式，左侧写代码,右侧显示结果。
这种模式一般用于练习或测试
2.scala用var声明变量,声明后可以修改。 用val声明常量，一经声明，不允许更改
3.scala是行语言，不需要加;  但是如果要在一行中写多条语句，需要用;隔开
4.scala可以根据结果自动推断出类型， 不需要显示的声明
5.scala也可以显示的指定数据类型，形式:val v7:String="hello"
**/
object Demo01 {
  // var: 声明变量, val: 声明常量
  var v1 = 100                                    //> v1  : Int = 100
  v1.+(3).*(5)                                    //> res0: Int = 515
}