package beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeans {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		
		Calendar c1 = ctx.getBean("c1", Calendar.class);
		System.out.println(c1);
		
		Calendar c2 = ctx.getBean("c2", Calendar.class);
		System.out.println(c2);
		
		Date d2 = c2.getTime();
		d2 = ctx.getBean("date2", Date.class);
		System.out.println(d2);
		
		// 两次获取HelloService 只构造了一次HelloService 对象
		HelloService hello1 = ctx.getBean("helloService", HelloService.class);
		HelloService hello2 = ctx.getBean("helloService", HelloService.class);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf.format(new Date()));
		
		SimpleDateFormat sdf = ctx.getBean("sdf", SimpleDateFormat.class);
		System.out.println(sdf.format(new Date()));
		
		ctx.close();
		
	}
}
