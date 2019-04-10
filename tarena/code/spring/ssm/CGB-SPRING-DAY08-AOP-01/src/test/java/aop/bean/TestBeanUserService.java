package aop.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.service.UserService;

public class TestBeanUserService {
	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void testSaveUser() {
		UserService userService = ctx.getBean("userServiceImpl", UserService.class);
		userService.saveUser("test save user method");
	}
	
	@Test
	public void testUpdateUser() {
		UserService userService = ctx.getBean("userServiceImpl", UserService.class);
		int result = userService.updateUser("test update user method");
		System.out.println("result: " + result);
	}
	
	@Test
	public void testFindUser() {
		UserService userService = ctx.getBean("userServiceImpl", UserService.class);
		userService.findUser((int)11);
	}
	
	@After
	public void destroy() {
		ctx.close();
	}
	
	
}
