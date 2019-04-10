package proxy.suyh.part01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestProxy01 {
	public static void main(String[] args) {
		// 1. 创建一个目标类对象
		IMsgService msgService = new MsgServiceImpl();
		// 2. 实现代理
		ClassLoader loader = msgService.getClass().getClassLoader();
		Class<?>[] interfaces = msgService.getClass().getInterfaces();
		InvocationHandler handler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("start trancation");
				Object result = method.invoke(msgService, args);
				System.out.println("end trancation");
				return result;
			}
		};
		IMsgService proxy = (IMsgService) Proxy.newProxyInstance(loader, interfaces, handler);
		// 3. 通过代理类对象实现目标类的扩展功能
		proxy.sendMsg("hello proxy");
	}
}
