package service;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import project.service.UserService;

public class TestCglib {
	private static Logger log = Logger.getLogger("TestCglib");
	
	public static void main(String[] args) {
		
		UserService userService = new UserService();
		
		Enhancer e = new Enhancer();
		e.setClassLoader(userService.getClass().getClassLoader());
		e.setSuperclass(userService.getClass());
		
		e.setCallback(new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				log.info("start");
				Object result = method.invoke(userService, args);
				log.info("end");
				return result;
			}
		});
		
		UserService proxy = (UserService)e.create();
		proxy.saveUser("suyh");
		proxy.updateUser("suyh");
		proxy.deleteUser("suyh");
	}
}
