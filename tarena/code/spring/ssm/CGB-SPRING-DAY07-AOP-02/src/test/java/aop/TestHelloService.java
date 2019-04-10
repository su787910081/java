package aop;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.aspect.LoggingAspect;
import com.project.service.HelloService;
import com.project.service.impl.HelloServiceImpl;

public class TestHelloService {
	// 初始化spring 容器
	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void testSayHello() {
		// helloService 指向哪个对象？	B)
		// A) HelloServiceImpl
		// B) HelloService
		// C) LogginAspect
		HelloService helloService = ctx.getBean("helloServiceImpl", HelloService.class);
		helloService.sayHello("suyh");
		
		System.out.println(helloService instanceof HelloServiceImpl);
		System.out.println(helloService instanceof LoggingAspect);
		
	}
	
	@After
	public void destroy() {
		ctx.close();
	}
}
