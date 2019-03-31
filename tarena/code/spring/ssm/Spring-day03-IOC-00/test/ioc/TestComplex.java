package ioc;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestComplex {
	private static ClassPathXmlApplicationContext ctx;
	
	static void init() {
		ctx  = new ClassPathXmlApplicationContext("complex.xml");
	}
	
	static void close() {
		ctx.close();
	}
	
	public static void main(String[] args) {
		init();
		
		ComplexObject cobj = ctx.getBean("c", ComplexObject.class);
		String[] hobbys = cobj.getHobby();
		List<String> addr = cobj.getAddress();
		
		System.out.println(Arrays.toString(hobbys));
		System.out.println(addr);
		
		Properties p = cobj.getConfigs();
		System.out.println(p);
		
		close();
	}
}
