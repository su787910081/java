package project.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.project.service.MessageService;

public class TestMessage {
	private static ClassPathXmlApplicationContext ctx;
	public static void init() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
	public static void destroy() {
		ctx.close();
		ctx = null;
	}
	public static void main(String[] args) {
		init();
		
		MessageService ms = ctx.getBean("messageServiceImpl", MessageService.class);
		ms.saveMsg("Hello gsd1709");
		
		destroy();
	}
}
