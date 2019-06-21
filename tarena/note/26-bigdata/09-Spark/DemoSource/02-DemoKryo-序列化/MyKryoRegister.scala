package cn.tedu.kryo

import org.apache.spark.serializer.KryoRegistrator
import com.esotericsoftware.kryo.Kryo

// 自定义Kroyo 注册器类，可以通过此类将指定的class 的序列化类型变为Kryo
class MyKryoRegister extends KryoRegistrator {
    def registerClasses(kryo: Kryo): Unit = {
        // 将Person 类做注册，在序列化Person 对象时，使用kryo
        kryo.register(classOf[Person])
        // 可以注册多个类，在后面继续注册就可以了。如下：
        // kryo.register(classOf[Student])
    }
}

