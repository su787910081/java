# Java CGLIB 库实现代理

> CGLIB 可以为实现接口的类创建代理，同时它也可以为没有实现接口的类创建代理。<br>
> CGLIB 引入JAR 包: 
>> ant-1.9.6.jar<br>
>> asm-5.2.jar<br>
>> cglib-3.2.5.jar<br>

- CGLIB 创建代理
    > 以未实现接口的类创建代理为例.<br>
    > 首先我们有一个类`MsgService`，未实现任何接口.<br>

        public class MsgService {
            public void sendMsg(String msg) {
                System.out.println("send: " + msg);
            }
        }

    > 在main 方法中使用

        import java.lang.reflect.Method;

        import net.sf.cglib.proxy.Enhancer;
        import net.sf.cglib.proxy.InvocationHandler;

        public static void main(String[] args) {
            // 1. 创建目标对象
            MsgService msgService = new MsgService();
            
            // 2. 创建代理对象
            // 2.1 创建一个代理增强器(负责创建代理)
            Enhancer e = new Enhancer();
            // 2.2 类加载器
            e.setClassLoader(msgService.getClass().getClassLoader());
            // 2.3 指定一个父类
            e.setSuperclass(msgService.getClass());
            // e.setInterfaces(msgService.getClass().getInterfaces());
            // 2.4 实现一个回调方法(类似JDK 中的处理器)
            e.setCallback(new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("begin");
                    Object result = method.invoke(msgService, args);
                    System.out.println("end");
                    return result;
                }
            });
            
            // 3. 创建这个代理类对象
            MsgService proxy = (MsgService)e.create();
            // 如果是实现接口的代理则需要转换为接口
            // IMsgService proxy = (IMsgService)e.create();
            
            // 4. 通过代理类对象执行目标业务
            proxy.sendMsg("hello cglib proxy");
        }
    >> 如果是一个实现了接口的类，则需要将`e.setSuperclass()` 方法换成`e.setInterfaces()` 方法<br>
    >> 同时在创建代理对象的时候，返回的类型也必须是接口类型<br>

- 小结: 
    > 与JDK 中的Proxy 相对，完全兼容其功能，另外扩展了一个对普通类的代理功能。
