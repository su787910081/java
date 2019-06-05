package day02.cn.tedu.ssort

// 用于封装文件中的两列数据
// 对象作为一个Key 进行排序，需要继承自一个Ordered 以及混入序列化特质
class Ssort(val col1: String, val col2: Int) extends Ordered[Ssort] with Serializable {
  // 完成二次排序的比较规则 
  def compare(that: Ssort): Int = {
    // 按第一列升序排序
    val result = this.col1.compareTo(that.col1)

    if (result == 0) {
      // 按第二列做降序排序
      that.col2.compareTo(this.col2)
    } else {
      result
    }
  }
}