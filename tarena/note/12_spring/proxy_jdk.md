# java jdk 中的代理

- Proxy 的使用
    > 目标：为一个类实现一个代理，代理类需要为这个类添加一些前置以及后置功能

    > 有一个接口 `IUserService`

        public interface IUserService {
            void helloUser(String user);
        }

    > 有一个类实现了这个接口 `UserServiceImpl`

        public class UserServiceImpl implements IUserService {
            @Override
            public void saveUser(String user) {
                System.out.println("Hello: " + user);
            }
        }

    > 现在，使用JDK 中的`Proxy` 来实现此类的代理<br>
    > `Proxy` 中有一个static 方法`newProxyInstance`<br>

        import java.lang.reflect.InvocationHandler;
        import java.lang.reflect.Method;
        import java.lang.reflect.Proxy;

        public class TestProxy {
            public static void main(String[] args) {
                // 目标类对象
                IUserService userService = new UserServiceImpl();

                // 为代理的实现准备参数
                // 参数1. 目标对象类加载器
                ClassLoader loader = userService.getClass().getClassLoader();
                // 或者通过类直接获取加载器也是一样的
                // ClassLoader loader = UserServiceImpl.class.getClassLoader();
                // 参数2. 目标类对象实现了哪些接口
                Class<?>[] interfaces = userService.getClass().getInterfaces();
                // 参数3. 处理器
                // 处理器需要我们实现一个接口的类
                // 所以这个地方我们需要来实现一个类，这个类实现一个接口 InvocationHandler
                // 这个类(ServiceHandler)的实现我把它放到下面
                InvocationHandler handler = new ServiceHandler(userService);

                // 前面已经把三个参数准备好了，下面我们就可以借助Proxy 来创建一个代理类对象
                // 这里返回一个代理类，这个代理类将会与目标类实现相同的接口。
                // 最终我们就可以通过它来调用目标类的一切接口
                IUserService proxy = (IUserService)Proxy.newProxyInstance(
                    loader,	// 目标对象类加载器 (目标对象必须有实现某些接口)
                    interfaces, // 目标对象实现了哪些接口
                    handler);	// 处理器: 业务处理器

                // 这里实际调用的是处理器类中的invoke() 方法
                // 在invoke() 方法中调用目标类的helloUser() 方法
                // 从而实现为目标类添加一个前置以及后置功能。当然你也可以添加其他功能。
                proxy.helloUser("suyh");
            }
        }
        
    > 接下来我们为这个类实现一个处理器类: `ServiceHandler` 也就是`Proxy.newProxyInstance()` 的第三个参数所需要用到的<br>
    >> 我们在这个处理器类，为目标类添加一个前置与后置功能<br>

        class ServiceHandler implements InvocationHandler {
            private Object target;	// 目标对象: 也就是上面的UserServiceImpl 的实例
            
            public ServiceHandler(Object target) {
                this.target = target;
            }
            
            @Override
            /**
            * @param proxy 指向代理对象
            * @param method 指向接口中的方法对象
            * @param args 指向method 对象执行时需要的实际参数
            */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin transaction");    // 前置功能
                Object result = method.invoke(target, args);	// 核心业务
                System.out.println("end transaction");  // 后置功能
                return result;  // 将结果返回
            }
        }

- 小结：
    > 目标类，**<span style="color:red">必须实现了某些接口</span>**；<br>
    > 代理类为这些接口实现功能的扩展；<br>
    >> 如果需要为未实现接口的类实现代理，则可以使用第三方库：`CGLIB`
