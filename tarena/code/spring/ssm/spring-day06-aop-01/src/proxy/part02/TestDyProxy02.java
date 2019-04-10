package proxy.part02;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDyProxy02 {
	static class ServiceHandler implements InvocationHandler {
		private Object target;	// 目标对象
		
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
			System.out.println("begin transaction");
			Object result = method.invoke(target, args);	// 核心业务
			System.out.println("end transaction");
			return result;
		}
	}
	
	
	public static void main(String[] args) {
		// 目标对象
		UserService userService = new UserServiceImpl();
		
//		System.out.println(UserServiceImpl.class == userService.getClass());
		
		// 为这俱目标对象创建一个代理对象
//		ClassLoader loader = UserServiceImpl.class.getClassLoader();
		ClassLoader loader = userService.getClass().getClassLoader();
		Class<?>[] interfaces = userService.getClass().getInterfaces();
		InvocationHandler handler = new ServiceHandler(userService);
		UserService proxy = (UserService) Proxy.newProxyInstance(
				loader,	// 目标对象类加载器 (目标对象必须有实现某些接口)
				interfaces, // 目标对象实现了哪些接口
				handler);	// 处理器: 业务处理器
		
		proxy.saveUser("湖塘");
	}
}
