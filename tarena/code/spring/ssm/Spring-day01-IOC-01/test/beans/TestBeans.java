package beans;

import java.util.Date;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeans {
	public static void main(String[] args) {
		// 1. 初始化Spring 容器对象
		// ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println(ctx);
		// 2. 获取我们需要的对象
		Date date1 = (Date)ctx.getBean("date1");
		System.out.println(date1);
		
		Date date2 = ctx.getBean("date1", Date.class);
		Map map = ctx.getBean("map1", Map.class);
		System.out.println(map);
		
		// HelloService 没有实现无参构造时，不能这样处理
		 HelloService hs = ctx.getBean("helloService", HelloService.class);
		 System.out.println(hs);
		
		// 3. 释放资源
		ctx.close();
	}
}
