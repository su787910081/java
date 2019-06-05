package cn.tedu.kryo

import org.apache.spark.serializer.KryoRegistrator
import com.esotericsoftware.kryo.Kryo

// 自定义Kroyo 注册器类，可以通过此类将指定的class 的序列化类型变为Kryo
class MyKryoRegister extends KryoRegistrator {
  def registerClasses(kryo: Kryo): Unit = {
    // 将指定类的序列化变为Kryo
    kryo.register(classOf[Person])
    // 若还有其他类可以继续注册
    // kryo.register(classOf[Student])
  }
}