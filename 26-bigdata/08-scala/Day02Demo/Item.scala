
/**
 * 通过case 关键字，将一个类变成样例类
 * case class 必须显示声明一个主构造器
 * case class 会隐式构造一个空的辅助构造器
 * case class 不需要new 就可以创建对象
 * case class 默认混入了序列化物质，Serializable
 */
case class Item(v1: String, v2: Int) extends Serializable {
  var title = v1
  var price = v2
  
  
}