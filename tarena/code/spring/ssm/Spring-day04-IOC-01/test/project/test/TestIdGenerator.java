package project.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.project.util.IdGenerator;

public class TestIdGenerator {
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
		
//		IdGenerator ig = ctx.getBean("idg", IdGenerator.class);
//		System.out.println(ig);
		
		destroy();
	}
}
