package cn.tedu.kryo

// 必须 混入Serializable
class Person(v1: String, v2: Int) extends Serializable {
    val name = v1
    val age = v2
}