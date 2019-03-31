package project.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.project.service.UserService;

public class TestUserService {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

		UserService us = ctx.getBean("userServiceImpl", UserService.class);
		us.saveUser("suyh");
		
		ctx.close();
	}
}
