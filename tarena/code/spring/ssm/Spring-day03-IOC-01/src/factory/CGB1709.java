package factory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 运行时有效，一般都是这个。另一个就是编译时有效，我们基本做不到。
/**
 * 注解: 
 * 1. 是Java 中的一种元数据，用于描述Java 对象
 * 2. 本质上也是一个类，
 * @Retention 表示自己定义的注解何时有效
 * @Target 表示自己定义的注解应用在哪里
 * @author suyh
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.TYPE)
public @interface CGB1709 {	// CGB1709.class

}
