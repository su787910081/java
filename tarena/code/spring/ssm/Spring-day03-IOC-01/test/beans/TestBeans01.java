package beans;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestBeans01 {
	private static ClassPathXmlApplicationContext ctx;

	static void init() {
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}

	static void close() {
		ctx.close();
	}

	public static void main(String[] args) {
		init();

		Object obj = ctx.getBean("userService");
		System.out.println(obj);
		
		close();
	}
}
